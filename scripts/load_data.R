library(dplyr)
library(stringr)
library(tidyr)

experience_levels <- c("< 3 Monate", 
                       ">= 3 Monate && < 6 Monate", 
                       ">= 6 Monate && < 1 Jahr", 
                       ">= 1 Jahr && < 3 Jahre",
                       ">= 3 Jahre"
                       )

experience_labels <- c("< 3 months",
                       ">= 3 months && < 6 months",
                       ">= 6 months && < 1 year",
                       ">= 1 year && < 3 years",
                       ">= 3 years")

tee <- function(.data, f) {
  f()
  .data
}

rename_to_opponent <- function(df) {
  rename_with(df, function(c) {
    paste("opponent", c, sep = "_")
    })
}

rename_to_author <- function(df) {
  rename_with(df, function(c) {
    paste("author", c, sep = "_")
  })
}

normalise_value <- function(l) {
  l <- as.numeric(l)
  tmp <- (l - min(l, na.rm = TRUE)) / (max(l, na.rm = TRUE) - min(l, na.rm = TRUE))
}

load_tests <- function(study) {
  if (!study %in% c("llmvsllm", "userstudy")) {
    stop(paste("study must be llmvsllm or userstudy, was ", study))
  }
  
  readdbtable <- function(filename) {
    read.csv(paste("rawdata", study, "db_tables", filename, sep = "/"), skipNul = TRUE) |>
      rename_with(tolower)
  }
  
  classes <- readdbtable("classes.csv") |>
    select(class_id, name, javafile) |>
    mutate(name = factor(name))
  
  experiment <- readdbtable("experiment.csv") |>
    filter(experiment_name == "study") |>
    select(int_value)
  
  games <- readdbtable("games.csv") |>
    select(id, timestamp) |>
    mutate(
      round = case_when(
        study == "llmvsllm" ~ "LLM vs LLM",
        timestamp == "2026-02-03 08:34:27" ~ "Round 1",
        timestamp == "2026-02-03 08:34:28" ~ "Round 1",
        timestamp == "2026-02-03 12:26:18" ~ "Round 1",
        timestamp == "2026-02-03 09:08:43" ~ "Round 2",
        timestamp == "2026-02-03 13:02:51" ~ "Round 2",
        TRUE ~ NA
      ) |> factor()
    ) |>
    filter(!is.na(round)) |>
    filter(study == "llmvsllm" | id != 345) |>
    filter(study == "userstudy" | id %in% experiment$int_value) |>
    rename(game_start = timestamp)
  
  users <- readdbtable("users.csv") |>
    select(user_id, username) |>
    filter(user_id >= 5) |>
    mutate(
      llm_or_human = factor(ifelse(user_id <= 6, "LLM", "Human"), levels = c("LLM", "Human"))
    )
  
  players <- readdbtable("players.csv") |>
    select(id, user_id, game_id, role) |>
    filter(role %in% c("ATTACKER", "DEFENDER")) |>
    inner_join(users, join_by(user_id)) |>
    rename(player_id = id) |>
    mutate(role = factor(role))
  
  test_smell <- readdbtable("test_smell.csv") |>
    mutate(value = TRUE) |>
    mutate(
      smell_name = smell_name |> 
        tolower() %>%
        gsub(" ", "_", .) %>%
        paste("smell", ., sep = "_") |>
        factor()) |>
    pivot_wider(names_from = smell_name, values_from = value, values_fill = FALSE)

  
  messages <- readdbtable("llm_messages.csv") |>
    select(message_type, conversation_id) |>
    filter(message_type == "AI")
  
  conversations <- readdbtable("llm_conversations.csv") |>
    inner_join(messages, join_by(conversation_id)) |>
    summarise(number_of_messages = n(), .by = test_id)
  
  invalid_tests <- read.csv("rawdata/killmaps/invalid_tests.csv") |>
    mutate(fails_against_cut = TRUE) |>
    rename(test_id = ID)
  
  messages <- readdbtable("llm_messages.csv") |>
    select(message_type, conversation_id, input_tokens, output_tokens) |>
    filter(message_type == "AI")
  
  conversations <- readdbtable("llm_conversations.csv") |>
    filter(user_id == 6) |>
    inner_join(messages, join_by(conversation_id)) |>
    summarise(
      number_of_messages = n(),
      input_tokens = sum(input_tokens),
      output_tokens = sum(output_tokens),
      .by = c(test_id, game_id)
    ) |>
    filter(game_id %in% games$id)
  
  tests <- readdbtable("tests.csv") |>
    select(test_id, game_id, class_id, classfile, javafile, player_id, timestamp, lines_covered, points) |>
    mutate(
      classfile = classfile != "",
      number_lines_covered = str_count(lines_covered, ",") + 1
    ) |>
    rename(
      is_compiled = classfile,
      test_file = javafile,
      author_player_id = player_id
    ) |>
    left_join(classes, join_by(class_id)) |>
    select(!c(javafile, class_id)) |>
    rename(cut = name) |>
    inner_join(games, join_by(game_id == id)) |>
    #select(!class_id) |>
    mutate(
      seconds_since_gamestart = (as.POSIXct(timestamp) - as.POSIXct(game_start)) |>
        as.numeric(units = "secs")
    ) |>
    select(!c(timestamp, game_start)) |>
    inner_join(rename_to_author(players), join_by(author_player_id, game_id == author_game_id)) |>
    inner_join(rename_to_opponent(players), join_by(game_id == opponent_game_id), relationship = "many-to-many") |>
    left_join(conversations, join_by(test_id, game_id)) |>
    filter(author_player_id != opponent_player_id) |>
    left_join(test_smell, join_by(test_id)) |>
    mutate(
      across(starts_with("smell"), function(x){
        ifelse(is.na(x), FALSE, x)
      })
    ) |>
    mutate(
      is_equivalence_test = author_role != "DEFENDER"
    ) |>
    
    
    mutate(
      test_id = paste(substr(study, start = 1, stop = 1), test_id, sep = "")
    ) |>
    left_join(invalid_tests, join_by(test_id)) |>
    mutate(fails_against_cut = !is.na(fails_against_cut))
  
  if (study == "userstudy") {
    tests
  } else {
    tests |> right_join(experiment, join_by(game_id == int_value), keep = FALSE)
  }
}

load_mutants <- function(study) {
  if (!study %in% c("llmvsllm", "userstudy")) {
    stop(paste("study must be llmvsllm or userstudy, was ", study))
  }
  
  readdbtable <- function(filename) {
    read.csv(paste("rawdata", study, "db_tables", filename, sep = "/"), skipNul = TRUE) |>
      rename_with(tolower)
  }
  
  experiment <- readdbtable("experiment.csv") |>
    filter(experiment_name == "study") |>
    select(int_value)
  
  classes <- readdbtable("classes.csv") |>
    select(class_id, name, javafile) |>
    mutate(name = factor(name))
  
  games <- readdbtable("games.csv") |>
    select(id, timestamp) |>
    mutate(
      round = case_when(
        study == "llmvsllm" ~ "LLM vs LLM",
        timestamp == "2026-02-03 08:34:27" ~ "Round 1",
        timestamp == "2026-02-03 08:34:28" ~ "Round 1",
        timestamp == "2026-02-03 12:26:18" ~ "Round 1",
        timestamp == "2026-02-03 09:08:43" ~ "Round 2",
        timestamp == "2026-02-03 13:02:51" ~ "Round 2",
        TRUE ~ NA
      ) |> factor()
    ) |>
    filter(!is.na(round)) |>
    filter(study == "llmvsllm" | id != 345) |>
    filter(study == "userstudy" | id %in% experiment$int_value) |>
    rename(game_start = timestamp)
  
  users <- readdbtable("users.csv") |>
    select(user_id, username) |>
    filter(user_id >= 5) |>
    mutate(llm_or_human = factor(ifelse(user_id <= 6, "LLM", "Human"), levels = c("LLM", "Human")))
  
  players <- readdbtable("players.csv") |>
    select(id, user_id, game_id, role) |>
    filter(role %in% c("ATTACKER", "DEFENDER")) |>
    inner_join(users, join_by(user_id)) |>
    mutate(role = factor(role)) |>
    rename(player_id = id)
  
  equivalences <- readdbtable("equivalences.csv") |>
    select(mutant_id, mutant_points) |>
    rename(points_until_equivalent = mutant_points)
  
  messages <- readdbtable("llm_messages.csv") |>
    select(message_type, conversation_id, input_tokens, output_tokens) |>
    filter(message_type == "AI")
  
  conversations <- readdbtable("llm_conversations.csv") |>
    filter(user_id == 5) |>
    inner_join(messages, join_by(conversation_id)) |>
    summarise(
      number_of_messages = n(),
      input_tokens = sum(input_tokens),
      output_tokens = sum(output_tokens),
      .by = c(mutant_id, game_id)
      ) |>
    filter(game_id %in% games$id)
  
  
  
  mutants <- readdbtable("mutants.csv") |>
    select(mutant_id, javafile, classfile, alive, game_id, class_id, equivalent, player_id, timestamp, points, mutatedlines, killmessage) |>
    rename(
      mutant_file = javafile,
      author_player_id = player_id
      ) |>
    inner_join(games, join_by(game_id == id)) |>
    inner_join(rename_to_author(players), join_by(author_player_id, game_id == author_game_id)) |>
    inner_join(rename_to_opponent(players), join_by(game_id == opponent_game_id), relationship = "many-to-many") |>
    left_join(conversations, join_by(mutant_id, game_id)) |>
    filter(author_player_id != opponent_player_id) |>
    inner_join(classes, join_by(class_id)) |>
    left_join(equivalences, join_by(mutant_id)) |>
    select(!javafile) |>
    mutate(
      seconds_since_gamestart = (as.POSIXct(timestamp) - as.POSIXct(game_start)) |>
        as.numeric(units = "secs"),
      is_stillborn = points == 0,
      alive = as.logical(alive),
      equivalent = equivalent %in% c("DECLARED_YES", "ASSUMED_YES"), 
      classfile = classfile != "",
      number_mutated_lines = str_count(mutatedlines, ",") + 1,
    ) |>
    rename(
      cut = name,
      survived = alive,
      declared_equivalent = equivalent,
      is_compiled = classfile
    ) |>
    
    
    mutate(
      mutant_id = paste(substr(study, start = 1, stop = 1), mutant_id, sep = "")
    )
  
  if (study == "userstudy") {
    mutants
  } else {
    mutants |> right_join(experiment, join_by(game_id == int_value), keep = FALSE)
  }
    
}

killmap_data <- function(tests, mutants) {
  killmap_tests <- tests |>
    filter(!fails_against_cut) |>
    select(test_id, seconds_since_gamestart, game_id, author_llm_or_human) |>
    rename(test_seconds = seconds_since_gamestart, test_game_id = game_id, test_actor = author_llm_or_human)
  
  killmap_mutants <- mutants |>
    select(mutant_id, seconds_since_gamestart, game_id, author_llm_or_human) |>
    rename(mutant_seconds = seconds_since_gamestart, mutant_game_id = game_id, mutant_actor = author_llm_or_human)
  
  canonical_killmaps <- read.csv("rawdata/killmaps/killmaps.csv", header = FALSE, col.names = c("test_id", "mutant_id", "state")) |>
    mutate(state = factor(state))
  test_mapping <- read.csv("rawdata/killmaps/dedup_test_mapping.csv", header = FALSE, col.names = c("dup_test_id", "canonical_id"))
  mutant_mapping <- read.csv("rawdata/killmaps/dedup_mutant_mapping.csv", header = FALSE, col.names = c("dup_mutant_id", "canonical_id"))
  
  full_map <- test_mapping |>
    left_join(canonical_killmaps, join_by(canonical_id == test_id), relationship = "many-to-many") |>
    right_join(mutant_mapping, join_by(mutant_id == canonical_id), relationship = "many-to-many") |>
    select(dup_test_id, state, dup_mutant_id) |>
    rename(test_id = dup_test_id, mutant_id = dup_mutant_id) |>
    right_join(killmap_tests, join_by(test_id)) |>
    right_join(killmap_mutants, join_by(mutant_id))
  
  global_test_map <- full_map |>
    summarise(
      n = n(),
      .by = c(test_id, mutant_actor, state)
    ) |>
    pivot_wider(
      names_from = c(mutant_actor, state),
      values_from = n,
      values_fill = 0
    ) |>
    rename(
      human_mutants_killed = Human_KILLED,
      llm_mutants_killed = LLM_KILLED,
      human_mutants_ignored = Human_SURVIVED,
      llm_mutants_ignored = LLM_SURVIVED
    )
  
  global_mutant_map <- full_map |>
    summarise(
      n = n(),
      .by = c(mutant_id, test_actor, state)
    ) |>
    pivot_wider(
      names_from = c(test_actor, state),
      values_from = n,
      values_fill = 0
    ) |>
    rename(
      human_tests_killed_by = Human_KILLED,
      llm_tests_killed_by = LLM_KILLED,
      human_tests_survived = Human_SURVIVED,
      llm_tests_survived = LLM_SURVIVED
    )
    
    
  game_map <- full_map |>
    filter(test_game_id == mutant_game_id) |>
    rename(game_id = test_game_id) |>
    select(!mutant_game_id)
  
  # Counts how many mutants were killed within the same game,
  # grouped by whether the mutant already existed at test creation
  time_test_map <- game_map |>
    mutate(mutant_is_future = mutant_seconds >= test_seconds) |>
    summarise(
      n = n(),
      .by = c(test_id, mutant_is_future, state)
      ) |>
    pivot_wider(
      names_from = c(mutant_is_future, state),
      values_from = n,
      values_fill = 0
    ) |>
    rename(
      existing_mutants_killed = FALSE_KILLED,
      future_mutants_ignored = TRUE_SURVIVED,
      future_mutants_killed = TRUE_KILLED,
      existing_mutants_ignored = FALSE_SURVIVED
    )
  
  time_mutant_map <- game_map |>
    mutate(mutant_is_future = mutant_seconds >= test_seconds) |>
    summarise(
      n = n(),
      .by = c(mutant_id, mutant_is_future, state)
    ) |>
    pivot_wider(
      names_from = c(mutant_is_future, state),
      values_from = n,
      values_fill = 0
    ) |>
    rename(
      future_tests_killed_by = FALSE_KILLED,
      existing_tests_survived = TRUE_SURVIVED,
      existing_tests_killed_by = TRUE_KILLED,
      future_tests_survived = FALSE_SURVIVED
    )
    
  
  
  fresh_kill_map <- game_map |>
    filter(mutant_seconds < test_seconds) |>
    filter(state == "KILLED") |>
    group_by(mutant_id) |>
    summarise(test_id = min(test_id)) |>
    group_by(test_id) |>
    summarise(fresh_killed_mutants = n())
  
  list(tests = tests |>
    left_join(fresh_kill_map, join_by(test_id)) |>
    left_join(global_test_map, join_by(test_id)) |>
    left_join(time_test_map, join_by(test_id)) |>
    #mutate(
    #  #fresh_killed_mutants = ifelse(is.na(fresh_killed_mutants), 0, fresh_killed_mutants)
    #  
    #  )
    replace_na(
      list(
        fresh_killed_mutants = 0,
        existing_mutants_killed = 0,
        future_mutants_killed = 0,
        existing_mutants_ignored = 0,
        future_mutants_ignored = 0,
        human_mutants_killed = 0,
        llm_mutants_killed = 0,
        human_mutants_ignored = 0,
        llm_mutants_ignored = 0
      )
    ) |> mutate(
      kill_rate = (human_mutants_killed + llm_mutants_killed) 
      / (human_mutants_killed + human_mutants_ignored + llm_mutants_killed + llm_mutants_ignored) |>
        replace_na(0),
      human_kill_rate = (human_mutants_killed / (human_mutants_killed + human_mutants_ignored)) |>
        replace_na(0),
      llm_kill_rate = (llm_mutants_killed / (llm_mutants_killed + llm_mutants_ignored)) |>
        replace_na(0),
      existing_kill_rate = (existing_mutants_killed / (existing_mutants_killed + existing_mutants_ignored)) |>
        replace_na(0),
      future_kill_rate = (future_mutants_killed / (future_mutants_killed + future_mutants_ignored)) |>
        replace_na(0)
    )
    
    
    
    ,
    mutants = mutants |>
      left_join(global_mutant_map, join_by(mutant_id)) |>
      left_join(time_mutant_map, join_by(mutant_id)) |>
      replace_na(
        existing_tests_killed_by = 0,
        future_tests_killed_by = 0,
        existing_tests_surived = 0,
        future_tests_surived = 0,
        human_tests_killed_by = 0,
        llm_tests_killed_by = 0,
        human_tests_surived = 0,
        llm_tests_surived = 0
      ) |>
      mutate(
        has_been_killed = human_tests_killed_by + llm_tests_killed_by > 0,
        evasion_rate = ((human_tests_survived + llm_tests_survived) 
        / (human_tests_survived + human_tests_killed_by + llm_tests_survived + llm_tests_killed_by)) |>
          replace_na(0),
        human_evasion_rate = (human_tests_survived / (human_tests_survived + human_tests_killed_by)) |>
          replace_na(0),
        llm_evasion_rate = (llm_tests_survived / (llm_tests_survived + llm_tests_killed_by)) |>
          replace_na(0),
        existing_evasion_rate = (existing_tests_survived / (existing_tests_survived + existing_tests_killed_by)) |>
          replace_na(0),
        future_evasion_rate = (future_tests_survived / (future_tests_survived + future_tests_killed_by)) |>
          replace_na(0),
      )
    
  )
}

mutant_score_data <- function(study) {
  
}

load_messages <- function(study) {
  if (!study %in% c("llmvsllm", "userstudy")) {
    stop(paste("study must be llmvsllm or userstudy, was ", study))
  }
  
  
  readdbtable <- function(filename) {
    read.csv(paste("rawdata", study, "db_tables", filename, sep = "/"), skipNul = TRUE) |>
      rename_with(tolower)
  }
  
  experiment <- readdbtable("experiment.csv") |>
    filter(experiment_name == "study") |>
    select(int_value)
  
  conversations <- readdbtable("llm_conversations.csv") |>
    filter(study == "llmvsllm" | game_id != 345) |>
    filter(study == "userstudy" | game_id %in% experiment$int_value) |>
    mutate(
      strategy = factor(strategy),
      type = factor(type),
      is_active = as.logical(is_active),
      is_success = as.logical(is_success)
    )
  
  
  messages <- readdbtable("llm_messages.csv") |>
    select(conversation_id, index_in_conversation, message_type, input_tokens, output_tokens, content) |>
    inner_join(conversations, join_by(conversation_id)) |>
    mutate(
      rejection_reason = case_when(
        message_type != "SYSTEM" | index_in_conversation == 0 ~ NA,
        startsWith(content, "Your mutant failed to compile.") ~ "Mutant has compile error",
        startsWith(content, "Your mutant already exists.") ~ "Mutant already exists",
        grepl("Your mutant contains calls to System.*, Random.* or new control structures.", content, fixed = TRUE) ~ "Mutant includes a new system call, or a new control structure",
        grepl("Your mutant contains prohibited operations such as bitshifts, ternary operators, added comments or multiple statments per line.", content, fixed = TRUE) ~ "Mutant has prohibited operations",
        grepl("Your mutant adds a new method or field, or renames an existing one", content, fixed = TRUE) ~ "Mutant adds or renames a field or method",
        grepl("Your mutant is identical to the CUT", content, fixed = TRUE) ~ "Mutant is identical to the CuT",
        grepl("Your mutant contains new logical operations", content, fixed = TRUE) ~ "Mutant contains new logical operations",
        startsWith(content, "Your mutant has violated the following rule:") ~ "Mutant violates a validation rule",
        grepl("It did not pass on the original code for the following reason:", content, fixed = TRUE) ~ "Test fails on original",
        startsWith(content, "Your test did not pass on the original code for") ~ "Test fails on original",
        grepl("It has failed to compile for this reason:", content, fixed = TRUE) ~ "Test does not compile",
        startsWith(content, "Your test failed to compile for this reason:") ~ "Test does not compile",
        grepl("[Test contains a prohibited call", content, fixed = TRUE) ~ "Test has a prohibited call",
        grepl("[Test contains more than 2 assertions", content, fixed = TRUE) ~ "Test has too many assertions",
        grepl("[Test contains an invalid statement:", content, fixed = TRUE) ~ "Test includes an invalid statement",
        grepl("[Test contains an invalid expression", content, fixed = TRUE) ~ "Test includes an invalid statement",
        grepl("Invalid test suite contains more than one method declaration", content, fixed = TRUE) ~ "Test declares additional classes or methods",
        grepl("Invalid test suite contains more than one class declaration", content, fixed = TRUE) ~ "Test declares additional classes or methods",
        grepl("-[]", content, fixed = TRUE) ~ "Tests breaks an unknown rule",
        grepl("It has violated these rules:", content, fixed = TRUE) ~ "Test breaks an unknown rule",
        startsWith(content, "Your test has violated these rules:") ~ "ERROR",
        .default = "ERROR"
      )
    ) |>
    mutate(rejection_reason = factor(rejection_reason)) |>
    mutate(
      mutant_id = ifelse(is.na(mutant_id), NA, paste(substr(study, start = 1, stop = 1), mutant_id, sep = "")),
      test_id = ifelse(is.na(test_id), NA, paste(substr(study, start = 1, stop = 1), test_id, sep = "")),
      conversation_id = ifelse(is.na(conversation_id), NA, paste(substr(study, start = 1, stop = 1), conversation_id, sep = ""))
    )
    
}


round_1_survey <- read.csv("rawdata/questionnaires/CodeDefenders Studie zu LLM-Spielern - Runde 1.csv") |>
  rename(
    username = Benutzername..auf.dem.Zettel.,
    opinion_judged_programming_skill = Wie.schätzt.du.die.Programmierkenntnisse.deines.Gegners.ein.,
    opinion_judged_original = Wie.einfallsreich.hat.sich.dein.Gegner.verhalten.,
    opinion_challenged = Wie.herausfordernd.war.es..gegen.deinen.Gegner.zu.spielen.,
    opinion_fun = Wie.viel.Spaß.hattest.du.an.dem.Spiel.,
    opinion_human_or_ai = Glaubst.du..dein.Gegner.war.ein.Mensch.oder.eine.KI.,
    opinion_human_or_ai_reason = Woran.hast.du.das.festgemacht.
  ) |>
  mutate(
    round = ifelse(Zeitstempel != "2026/02/03 1:34:04 PM MEZ", "Round 1", "Round 2"), #Someone used the same link twice
    username = trimws(username)) |>
  select(!Zeitstempel)

round_2_survey <- read.csv("rawdata/questionnaires/CodeDefenders Studie zu LLM-Spielern - Runde 2.csv") |>
  rename(
    username = Benutzername..auf.dem.Zettel.,
    opinion_judged_programming_skill = Wie.schätzt.du.die.Programmierkenntnisse.deines.Gegners.ein.,
    opinion_judged_original = Wie.einfallsreich.hat.sich.dein.Gegner.verhalten.,
    opinion_challenged = Wie.herausfordernd.war.es..gegen.deinen.Gegner.zu.spielen.,
    opinion_fun = Wie.viel.Spaß.hattest.du.an.dem.Spiel.,
    opinion_human_or_ai = Glaubst.du..dein.Gegner.war.ein.Mensch.oder.eine.KI.,
    opinion_human_or_ai_reason = Woran.hast.du.das.festgemacht.,
    future_interest = Kannst.du.dir.vorstellen..in.Zukunft.auch.von.dir.aus.CodeDefenders.gegen.LLM.Spieler.zu.spielen.,
    future_interest_reason = Warum.Warum.nicht.,
    other_remarks = Hast.du.noch.Anmerkungen.Kritik.Verbesserungsvorschläge.
  ) |>
  select(!Zeitstempel) |>
  mutate(round = "Round 2", username = trimws(username))

survey <- round_1_survey |> bind_rows(round_2_survey) |>
  select(colnames(round_1_survey)) |>
  unique()

questionnaire <- read.csv("rawdata/questionnaires/CodeDefenders_Questionare_LLM_Players.csv") |>
  rename(
    username = Benutzername..auf.dem.Zettel.,
    age = Alter,
    gender = Geschlecht,
    degree = Studiengang,
    semester = Fachsemester,
    java_experience = Erfahrung.mit.Java,
    junit_experience = Erfahrung.mit.JUnit,
    Q1 = Was.ist.die.beste.Methode..um.Gleichheit.zwischen.zwei.Integers.zu.überprüfen.,
    Q2 = Welche.Annotation.hilft.dabei..zwischen.Junit.5.Tests.aufzuräumen.,
    Q3 = Gegeben.sei.der.folgende.Testcode.für.Javas.ArrayList.Klasse..Welche.der.Assertions.wird.durchlaufen.,
    Q4 = Welche.der.Junit.Assertions.wird.im.folgenden.Code.die.Zeile.mit.dem.Kommentar.....Test.me..ausführen.,
    Q5 = Für.den.unten.gegebenen.Code.wird.diese.JUnit.Assertion.fehlschlagen...assertEquals.2..lastIndexOf.Arrays.asList..a....b....a.....a..........................................................................Wie.würdest.du.den.Code.fixen..damit.die.Assertion.wieder.durchläuft.
  ) |>
  mutate(
    username = trimws(username)
    
    ) |>
  full_join(round_2_survey[, c("username", "future_interest", "future_interest_reason", "other_remarks")], 
            join_by(username)) |>
  mutate(
    Q1 = Q1 == "assertEquals(int1, int2)",
    Q2 = Q2 == "@AfterEach",
    Q3 = Q3 == "assertEquals(0, list.size());",
    Q4 = Q4 == "assertEquals(\"cat\", shortest(new String[] {\"rabbit\", \"cat\"}));",
    Q5 = Q5 == "Replace the relational operator \"<=\" with \"<\" in the loop statement",
    number_correct_questions = Q1 + Q2 + Q3 + Q4 + Q5,
    gender = factor(gender, levels = c("Männlich", "Weiblich", "Nichtbinär"), labels = c("male", "female", "nonbinary")),
    degree = factor(degree),
    java_experience = factor(java_experience, ordered = TRUE, levels = experience_levels, labels = experience_labels),
    junit_experience = factor(junit_experience, ordered = TRUE, levels = experience_levels, labels = experience_labels),
  ) %>%
  mutate(
    ., 
    skill = ( normalise_value(.$junit_experience) +
      normalise_value(.$number_correct_questions)
      ) / 2
    ) |>
  mutate(actor = ifelse(skill < median(skill, na.rm = TRUE), "low-skilled", "high-skilled")) |>
  
  unique()

all_tests <- load_tests("userstudy") |>
  left_join(rename_to_author(questionnaire), join_by(author_username)) |>
  left_join(rename_to_author(survey), join_by(author_username, round == author_round)) |>
  left_join(rename_to_opponent(questionnaire), join_by(opponent_username)) |>
  left_join(rename_to_opponent(survey), join_by(opponent_username, round == opponent_round)) %>%
  bind_rows(load_tests("llmvsllm")) |>
  mutate(author_actor = factor(ifelse(author_llm_or_human == "LLM", "LLM", author_actor), 
                        levels = c("low-skilled", "high-skilled", "LLM")
                        ),
         opponent_actor = factor(ifelse(opponent_llm_or_human == "LLM", "LLM", opponent_actor),
                                 levels = c("low-skilled", "high-skilled", "LLM")
         ))

all_mutants <- load_mutants("userstudy") |>
  left_join(rename_to_author(questionnaire), join_by(author_username)) |>
  left_join(rename_to_author(survey), join_by(author_username, round == author_round)) |>
  left_join(rename_to_opponent(questionnaire), join_by(opponent_username)) |>
  left_join(rename_to_opponent(survey), join_by(opponent_username, round == opponent_round)) |>
  bind_rows(load_mutants("llmvsllm")) |>
  mutate(
    author_actor = factor(ifelse(author_llm_or_human == "LLM", "LLM", author_actor),
                        levels = c("low-skilled", "high-skilled", "LLM")
                   ),
    opponent_actor = factor(ifelse(opponent_llm_or_human == "LLM", "LLM", opponent_actor),
                            levels = c("low-skilled", "high-skilled", "LLM")
    ),
  )





# Killmaps data for tests contains 2 rows with NAs: One test has no coverage and
#therefore no killed/ignored mutants, these values should be changed to 0.
# The very first test doesn't show up in the dedup data. I will have to figure
#out why that is. Until then, they remain as NA.
tmp <- killmap_data(tests = filter(all_tests, is_compiled), mutants = filter(all_mutants, is_compiled))
tests <- tmp$tests
mutants <- tmp$mutants
rm(tmp)

deftests <- tests |> 
  filter(!is_equivalence_test, !fails_against_cut)

defender_games <- deftests |>
  group_by(
    game_id, 
    across(starts_with("author_")), 
    across(starts_with("opponent_")),
    cut,
    round
  ) |>
  summarise(
    points = sum(points), .groups = "drop_last"
  )

attacker_games <- mutants |>
  group_by(
    game_id, 
    across(starts_with("author_")), 
    across(starts_with("opponent_")),
    cut,
    round
  ) |>
  summarise(
    points = sum(points), .groups = "drop_last"
  )

all_messages <- load_messages("userstudy") |>
  bind_rows(load_messages("llmvsllm")) |>
  inner_join(defender_games, join_by(game_id)) |>
  rename_with(.cols = starts_with("author"), \(text) sub("author", "defender", text)) |>
  rename_with(.cols = starts_with("opponent"), \(text) sub("opponent", "attacker", text))

all_conversations <- all_messages |>
  mutate(value = 1) |>
  pivot_wider(
    names_from = rejection_reason,
    values_from = value,
    values_fill = 0,
    names_prefix = "rejection_"
  ) |>
  summarise(
    input_tokens = sum(input_tokens),
    output_tokens = sum(output_tokens),
    across(starts_with("rejection_"), sum),
    .by = c(conversation_id, game_id, strategy, type, user_id, mutant_id, test_id, is_success)
  )

n_loc <- tests |>
  select(cut, lines_covered) |>
  separate_longer_delim(lines_covered, ",") |>
  unique() |>
  summarise(loc_for_cut = n(), .by = cut)
