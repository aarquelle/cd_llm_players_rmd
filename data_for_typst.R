#install.packages("svglite")
#install.packages("readr")
#install.packages("ineq")
library(jsonlite)
library(ggplot2)
library(svglite)
library(data.table)
library(dplyr)
library(glue)
library(readr)
library(scales)
library(patchwork)
library(ineq)

if (FALSE) {
  rm(list = ls())
  source("scripts/load_data.R")
  dedup_tests_per_game <-  deftests |>
    select(game_id, study, cut, author_llm_or_human, opponent_llm_or_human, test_file) |>
    mutate(test_code = sapply(paste("rawdata/", study, "/datadir/", test_file, sep = ""), \(f) paste(read_lines(f), collapse = "\n"))) |>
    summarise(n = n(), .by = c(game_id, study, cut, author_llm_or_human, opponent_llm_or_human, test_code))
}

theme_set(theme_light(base_size = 20, base_family = "Libertinus Serif", header_family = "Libertinus Sans"))
update_geom_defaults("text", list(size = 7, family = "Libertinus Serif"))

add_test_code <- function(df) {
  df |>
    mutate(
      test_code = read_file(paste("rawdata/", study, "/datadir/", test_file, sep = "")),
      test_code = substring(test_code, first = regexpr("public void test()", test_code)[1])
    )
}


pr <- function(plot,
               filename,
               width = 8.5,
               height = 4,
               ...) {
  ggsave(
    paste("../typst_ba/images/", filename, ".svg", sep = ""),
    plot = plot,
    width = width,
    height = height,
    ...
  )
}

fixed_gini <- function(data_vector, n) {
  #print(paste("data_vector", data_vector))
  #print(paste("n", n))
  #if (length(data_vector) > n) {
  #  stop("data has more rows than n")
  #}
  data_vector <- append(data_vector, rep(0, n - length(data_vector)))
  Gini(data_vector)
}

CharRange_mapping <- function(l) {
  l <- as.integer(l)
  case_when(
    l < 6 ~ "Documentation",
    l < 8 ~ "Class declaration",
    l < 18 ~ "Fields",
    l < 45 ~ "Constructor",
    l < 57 ~ "is()",
    l < 69 ~ "isNot()",
    l < 82 ~ "isIn()",
    l < 95 ~ "isNotIn()",
    l < 106 ~ "getStart()",
    l < 115 ~ "getEnd()",
    l < 127 ~ "isNegated()",
    l < 139 ~ "contains(char)",
    l < 161 ~ "contains(CharRange)",
    l < 184 ~ "equals()",
    l < 194 ~ "hashCode()",
    l <= 217 ~ "toString()"
  )
}

ByteVector_mapping <- function(l) {
  l <- as.integer(l)
  case_when(
    l < 6 ~ "Documentation",
    l < 8 ~ "Class declaration",
    l < 18 ~ "Fields",
    l < 26 ~ "ByteVector()",
    l < 36 ~ "ByteVector(int)",
    l < 53 ~ "putByte()",
    l < 73 ~ "put11()",
    l < 92 ~ "putShort()",
    l < 113 ~ "put12()",
    l < 134 ~ "putInt()",
    l < 161 ~ "putLong()",
    l < 226 ~ "putUTF8()",
    l < 248 ~ "putByteArray()",
    l <= 262 ~ "enlarge()"
  )
}

csv <- function(df, filename, row.names = FALSE) {
  write.csv(df, file = paste("../typst_ba/data/tables/", filename, ".csv", sep = ""), row.names = row.names)
}

colors.opinion <- c("red", "orange", "grey", "cyan", "blue")
colors.actor <- c(LLM = "turquoise3", Human = "thistle4")
colors.quality <- c("#458B00", "darkgoldenrod3", "#8B1C62", "red4")
colors.red <- "#BA2F2A"
colors.green <- "#088158"
colors.cut <- c(CharRange = "#E6B91E", ByteVector = "#90C226")

scale_defender <- scale_fill_manual(values = colors.actor, name = "Defender")
scale_attacker <- scale_fill_manual(values = colors.actor, name = "Attacker")
scale_cut <- scale_fill_manual(values = colors.cut, name = "Class under Test")

scale_percentage_bars <- function(dodge_value = 0.9, vjust = -0.2, with.percent = TRUE) {
  list(
    if (with.percent) scale_y_continuous(labels = percent, expand = expansion(mult = c(0, 0.1))) else scale_y_continuous(expand = expansion(mult = c(0, 0.1))),
    geom_col(position = "dodge"),
    geom_text(
      aes(label = if(with.percent) percent(after_stat(y), accuracy = 0.1) else round(after_stat(y), digits = 2)),
      position = position_dodge(dodge_value), 
      vjust = vjust
    )
  )
}

# Groups by author_llm_or_human and cut, fills to the same as x
scale_default_grouping <- function(type) {
  if (! type %in% c("t", "m")) {
    stop("type must be t or m")
  }
  list(
    aes(x = author_llm_or_human, fill = author_llm_or_human),
    scale_fill_manual(values = colors.actor),
    facet_wrap( ~ cut),
    labs(x = ifelse(type == "t", "Defender", "Attacker"), 
         fill = ifelse(type == "t", "Defender", "Attacker"),)
    
  )
}

test_performance_plot <- function(.data, y, title = NULL, ylab = NULL, geom = geom_boxplot(show.legend = FALSE)) {
  ggplot(.data, aes(x = author_llm_or_human, y = {{y}}, fill = author_llm_or_human)) +
    geom +
    facet_wrap(~ cut) +
    labs(title = title, y = ylab, x = "Defender")
}

performance_binary_mean_cols <- function(.data, y, type, with.percent = TRUE, ...) {
  .data |>
    summarise(
      {{y}} := mean({{y}}),
      .by = c(author_llm_or_human, cut)
    ) |>
    ggplot(aes(y = {{y}})) +
    scale_default_grouping(type) +
    scale_percentage_bars(with.percent = with.percent, ...)
}

typst_defender_mutation_scores <- defender_games |> 
  summarise(
    mean = mean(mutation_score),
    max = max(mutation_score, na.rm = TRUE),
    min = min(mutation_score, na.rm = TRUE),
    .by = c(cut, author_llm_or_human)
  )

list(
  #Demographics
  number_participants = nrow(questionnaire),
  demo_gender = questionnaire |>
    summarise(value = n(), .by = gender) |>
    pivot_wider(names_from = gender),
  
  demo_degree = questionnaire |>
    summarise(value = n(), .by = degree) |>
    mutate(degree = replace_values(
      as.character(degree),
      "Bachelor Informatik" ~ "Computer Science",
      "Bachelor Internet Computing" ~ "Internet Computing",
      "Artificial Intelligence" ~ "Artificial Intelligence",
      "Lehramt Informatik" ~ "Computer Science for a teaching post"
    )) |>
    pivot_wider(names_from = degree),
  
  # filter(degree == "Artificial Intelligence") |> nrow(),
  #demo_degree = questionnaire |> filter(degree == "Bachelor Informatik") |> nrow(),
  #demo_degree = questionnaire |> filter(degree == "Bachelor Internet Computing") |> nrow(),
  #demo_degree = questionnaire |> filter(degree == "Lehramt Informatik") |> nrow(),
  
  demo_ages = questionnaire$age,
  demo_semesters = questionnaire$semester,
  
  number_llmvsllm_games = mutants |> filter(grepl("l", mutant_id)) |> select(game_id) |> unique() |> nrow(),
  number_llmvsllm_bytevector_games = mutants |> filter(grepl("l", mutant_id)) |> filter(cut == "ByteVector") |> select(game_id) |> unique() |> nrow(),
  number_llmvsllm_charrange_games = mutants |> filter(grepl("l", mutant_id)) |> filter(cut == "CharRange") |> select(game_id) |> unique() |> nrow(),
  
  loc = n_loc,
  
  def_mutation_score_llm_bv = typst_defender_mutation_scores |> filter(author_llm_or_human == "LLM", cut == "ByteVector"),
  def_mutation_score_human_bv = typst_defender_mutation_scores |> filter(author_llm_or_human == "Human", cut == "ByteVector"),
  def_mutation_score_llm_cr = typst_defender_mutation_scores |> filter(author_llm_or_human == "LLM", cut == "CharRange"),
  def_mutation_score_human_cr = typst_defender_mutation_scores |> filter(author_llm_or_human == "Human", cut == "CharRange"),
  
  test_point_mean_llm = mean((deftests |> filter(author_llm_or_human == "LLM"))[,"points"]),
  test_point_mean_human = mean((deftests |> filter(author_llm_or_human == "Human"))[,"points"]),
  test_point_median_llm = median((deftests |> filter(author_llm_or_human == "LLM"))[,"points"]),
  test_point_median_human = median((deftests |> filter(author_llm_or_human == "Human"))[,"points"]),
  test_point_75q_llm = quantile((deftests |> filter(author_llm_or_human == "LLM"))[,"points"], 0.75),
  test_point_75q_human = quantile((deftests |> filter(author_llm_or_human == "Human"))[,"points"], 0.75),
  
  kill_rate_llm = mean((deftests |> filter(author_llm_or_human == "LLM"))[,"kill_rate"]),
  kill_rate_human = mean((deftests |> filter(author_llm_or_human == "Human"))[,"kill_rate"]),
  
  llm_assertion_roulette = mean((deftests |> filter(author_llm_or_human == "LLM"))[, "smell_assertion_roulette"]),
  human_assertion_roulette = mean((deftests |> filter(author_llm_or_human == "Human"))[, "smell_assertion_roulette"]),
  
  sensitive_equality_example = deftests |> 
      filter(author_llm_or_human == "LLM", smell_sensitive_equality, !smell_eager_test, cut == "CharRange") |> 
      head(1) |>
      add_test_code() |>
      select(test_code) |>
      rename(typst_code = test_code),
  
  winrates_against_humans = attacker_games |>
    inner_join(defender_games, join_by(game_id, cut, round), suffix = c(".attacker", ".defender"))|>
    select(!starts_with("opponent")) |>
    filter(author_llm_or_human.attacker == "Human") |>
    mutate(winrate = points.defender > points.attacker) |>
    summarise(winrate = mean(winrate), .by = c(cut, author_llm_or_human.defender)) |>
    pivot_wider(names_from = c(cut, author_llm_or_human.defender), values_from = winrate),
  
  n_def_games_against_humans = defender_games |>
    filter(opponent_llm_or_human == "Human") |>
    summarise(value = n(), .by = c(cut, author_llm_or_human)) |>
    pivot_wider(names_from = c(cut, author_llm_or_human)),
  
  defender_turing_test = defender_games |>
    filter(opponent_llm_or_human == "Human") |>
    mutate(
      opponent_opinion_human_or_ai = recode_values(opponent_opinion_human_or_ai,
        1 ~ "Human",
        2 ~ "Human",
        3 ~ "Unsure",
        4 ~ "AI",
        5 ~ "AI"
      )
    ) |>
    summarise(value = n(), .by = c(author_llm_or_human, opponent_opinion_human_or_ai)) |>
    pivot_wider(names_from = c(author_llm_or_human, opponent_opinion_human_or_ai), names_sep = " judged as "),
  
    
  def_turing_point_ratios = attacker_games |>
    inner_join(defender_games, join_by(game_id, cut, round), suffix = c(".attacker", ".defender"))|>
    select(!starts_with("opponent")) |>
    filter(author_llm_or_human.attacker == "Human") |>
    mutate(
      name = recode_values(
        author_opinion_human_or_ai.attacker,
        1 ~ "Human",
        2 ~ "Human",
        3 ~ "Unsure",
        4 ~ "AI",
        5 ~ "AI"
      ),
      value = points.defender / points.attacker
    ) |>
    select(name, value) |> 
    summarise(value = mean(value), .by = name) |>
    pivot_wider(),
  
  correlation_point_ratio_def_turing = (attacker_games |>
    inner_join(defender_games, join_by(game_id, cut, round), suffix = c(".attacker", ".defender"))|>
    select(!starts_with("opponent")) |>
    filter(author_llm_or_human.attacker == "Human") |>
    mutate(point_ratio = points.defender / points.attacker) %>%
    lm(
      author_opinion_human_or_ai.attacker ~ point_ratio + author_llm_or_human.defender,
      data = .
    ) |> summary())$coefficients[2,4],
  
  dedup_tests = dedup_tests_per_game |>
    filter(author_llm_or_human == "LLM") |>
    rename(number_of_tests = n) |>
    summarise(n = n(), .by = c(number_of_tests)) |>
    pivot_wider(names_from = number_of_tests, values_from = n),
  
  double_eq = mutants |>
    mutate(kbe = eqtests_killed_by > 0, kbd = deftests_killed_by > 0) |>
    summarise(kbe = mean(kbe), .by = c(opponent_llm_or_human, kbd)) |> 
    filter(!kbd) |>
    select(opponent_llm_or_human, kbe) |>
    pivot_wider(names_from = opponent_llm_or_human, values_from = kbe),
  
  p_opponent_equivalent = (lm(
    !has_been_killed ~
      author_llm_or_human +
      cut +
      opponent_llm_or_human
    , data = mutants
  ) |> summary())$coefficients["opponent_llm_or_humanHuman", "Pr(>|t|)"],
  
  n_mutated_lines = mutants |>
    summarise(across(number_mutated_lines, mean), .by =author_llm_or_human) |>
    pivot_wider(names_from = author_llm_or_human, values_from = number_mutated_lines),
  
  mutant_methods_gini_bv = mutants |>
    filter(cut == "ByteVector") |>
    separate_longer_delim(mutated_methods, ",") |>
    mutate(mutated_methods = factor(mutated_methods)) |>
    summarise(n = n(), .by = c(author_llm_or_human, game_id, mutated_methods)) |>
    summarise(gini = fixed_gini(n, length(levels(mutated_methods))), .by = c(author_llm_or_human, game_id)) |>
    summarise(gini = mean(gini), .by = author_llm_or_human) |>
    pivot_wider(values_from = gini, names_from = author_llm_or_human),
  
  total_llm_mutant_method_gini_bv = (mutants |>
      filter(cut == "ByteVector", author_llm_or_human == "LLM") |>
      summarise(n = n(), .by = mutated_methods) |>
      summarise(gini = Gini(n)))$gini,
  
  mutant_methods_gini_cr = mutants |>
    filter(cut == "CharRange") |>
    separate_longer_delim(mutated_methods, ",") |>
    mutate(mutated_methods = factor(mutated_methods)) |>
    summarise(n = n(), .by = c(author_llm_or_human, game_id, mutated_methods)) |>
    summarise(gini = fixed_gini(n, length(levels(mutated_methods))), .by = c(author_llm_or_human, game_id)) |>
    summarise(gini = mean(gini), .by = author_llm_or_human) |>
    pivot_wider(values_from = gini, names_from = author_llm_or_human),
  
  total_llm_mutant_method_gini_cr = (mutants |>
                                       filter(cut == "CharRange", author_llm_or_human == "LLM") |>
                                       summarise(n = n(), .by = mutated_methods) |>
                                       summarise(gini = Gini(n)))$gini,
    
    
    
  n_mutants_with_multiple_methods = mutants |> filter(grepl(",", fixed = TRUE, mutated_methods)) |> nrow(),
    
  
  #mutation_scores_bv = defender_games |> 
  #  filter(cut == "ByteVector") |>
  #  summarise(
  #    mean = mean(mutation_score),
  #    max = max(mutation_score, na.rm = TRUE),
  #    min = min(mutation_score, na.rm = TRUE),
  #    .by = c( author_llm_or_human)
  #    ),
  #
  #mutation_scores_cr = defender_games |> 
  #  filter(cut == "CharRange") |>
  #  summarise(
  #    mean = mean(mutation_score),
  #    max = max(mutation_score, na.rm = TRUE),
  #    min = min(mutation_score, na.rm = TRUE),
  #    .by = c( author_llm_or_human)
  #  ),
  
  
  dummy = 1
) |>
  write_json("../typst_ba/data/r_data.json", pretty = TRUE)





(
  questionnaire |>
    pivot_longer(
      cols = c(java_experience, junit_experience),
      names_to = "xp_type",
      values_to = "xp_value"
    ) |>
    mutate(
      xp_type = recode_values(
        xp_type,
        "java_experience" ~ "Java experience",
        "junit_experience" ~ "JUnit experience"
      )
    ) |>
    summarise(n = n(), .by = c(xp_type, xp_value)) |>
    
    ggplot(aes(
      x = xp_type, y = n, fill = xp_value
    )) +
    geom_col(position = "stack") +
    geom_text(aes(label = after_stat(y)), position = position_stack(vjust = 0.5)) +
    labs(
      fill = "Experience",
      x = NULL,
      y = "Count"
    )
) |>
  pr("xp")

(
  questionnaire |>
    mutate(number_correct_questions = ordered(number_correct_questions)) |>
    summarise(n = n(), .by = c(number_correct_questions)) |>
    ggplot(aes(
      y = "", x = n, fill = number_correct_questions
    )) +
    geom_col() +
    geom_text(aes(label = n), position = position_stack(vjust = 0.5)) +
    labs(
      y = NULL,
      x = "Count",
      fill = "Correct answers"
    )
) |>
  pr("quiz", height = 2.5)


(
  deftests |>
    separate_longer_delim(lines_covered, ",") |>
    summarise(
      .by = c(
        lines_covered,
        game_id,
        cut,
        author_llm_or_human,
        opponent_llm_or_human
      )
    ) |>
    left_join(n_loc, join_by(cut)) |>
    summarise(
      lines_covered = n(),
      .by = c(
        game_id,
        cut,
        author_llm_or_human,
        opponent_llm_or_human,
        loc_for_cut
      )
    ) |>
    mutate(line_coverage = lines_covered / loc_for_cut) |>
    ggplot(
      aes(y = line_coverage, fill = author_llm_or_human, x = cut)
    ) +
    
    geom_boxplot() +
    scale_defender +
    labs(y = "Line coverage", x = "Class under test") +
    scale_y_continuous(labels = percent)
) |> pr("line_coverage")

(
  defender_games |>
    ggplot(aes(y = mutation_score, fill = author_llm_or_human, x = cut)) +
    geom_boxplot() +
    scale_y_continuous(labels = percent) +
    scale_defender +
    labs(y = "Mutation score", x = "Class under test")
) |> pr("mutation_scores_llm_performance")

(
  deftests |>
    test_performance_plot(y = points, ylab = "Points")
) |> pr("def_test_points")

(
  deftests |>
    mutate(has_points = points > 0) |>
    summarise(has_points = mean(has_points), .by = c(author_llm_or_human, cut)) |>
    ggplot(aes(y = has_points, fill = author_llm_or_human, x = cut)) +
    scale_defender +
    scale_percentage_bars() +
    labs(y = NULL, x = "Class under test")
) |> pr("test_at_least_one")

(
  deftests |>
    pivot_longer(
      cols = c(kill_rate, llm_kill_rate, human_kill_rate, existing_kill_rate, future_kill_rate),
      names_to = "kill_rate_type",
      values_to = "kill_rate_value"
    ) |>
    mutate(kill_rate_type = factor(kill_rate_type, 
                                   levels = c(
                                     "kill_rate", 
                                     "human_kill_rate", 
                                     "llm_kill_rate", 
                                     "existing_kill_rate",
                                     "future_kill_rate"),
                                   labels = c(
                                     "Total kill rate",
                                     "vs human mutants",
                                     "vs llm mutants",
                                     "vs existing mutants",
                                     "vs future mutants"
                                   ))) |>
    ggplot(aes(x = author_llm_or_human, y = kill_rate_value, fill = author_llm_or_human)) + 
    geom_boxplot(position = "dodge") + 
    xlab("Defender") +
    scale_y_continuous(name = "Kill rate", labels = percent) + 
    scale_defender +
    facet_wrap( ~ kill_rate_type)
) |> pr("kill_rate_facet")

(
  deftests |>
  
    pivot_longer(starts_with("smell"), names_to = "smell_type", values_to = "smelly") |>
    mutate(smell_type = factor(smell_type,
      levels = c(
        "smell_assertion_roulette",
        "smell_duplicate_assert",
        "smell_eager_test",
        "smell_redundant_assertion",
        "smell_sensitive_equality",
        "smell_unknown_test"
      ),
      labels = c(
        "Assertion roulette",
        "Duplicate assertion",
        "Eager test",
        "Redundant assertion",
        "Sensitive equality",
        "Unknown test"
      )
    )) |>
    summarise(
      smelly = mean(smelly),
      .by = c(cut, smell_type, author_llm_or_human)
    ) |>
    ggplot(aes(x = smelly, fill = author_llm_or_human, y = smell_type)) +
    facet_grid(cut ~ .) +
    geom_col(position = "dodge") +
    scale_defender +
    labs(y = "Smell type", x = NULL)
) |> pr("test_smells")

(
  deftests |>
    summarise(
      across(starts_with("smell"), \(name) paste(100*round(mean(name), digits = 4), "%", sep = "")),
      .by = c(cut, author_llm_or_human)
    ) |>
    arrange(cut, author_llm_or_human) |>
    pivot_longer(starts_with("smell"), names_to = "Smell type") |>
    pivot_wider(names_from = c(cut, author_llm_or_human), names_glue = "{author_llm_or_human} ({cut})") |>
    mutate(`Smell type` = factor(`Smell type`,
                               levels = c(
                                 "smell_assertion_roulette",
                                 "smell_duplicate_assert",
                                 "smell_eager_test",
                                 "smell_redundant_assertion",
                                 "smell_sensitive_equality",
                                 "smell_unknown_test"
                               ),
                               labels = c(
                                 "Assertion roulette",
                                 "Duplicate assertion",
                                 "Eager test",
                                 "Redundant assertion",
                                 "Sensitive equality",
                                 "Unknown test"
                               )
    ))
    
) |> csv("test_smells")

(
  attacker_games |>
    inner_join(defender_games, join_by(game_id, cut, round), suffix = c(".attacker", ".defender"))|>
    select(!starts_with("opponent")) |>
    mutate(point_ratio = points.defender / ifelse(points.attacker != 0, points.attacker, 1)) |>
    filter(author_llm_or_human.attacker == "Human") |>
    ggplot(aes(x = cut, fill = author_llm_or_human.defender, y = point_ratio)) +
    geom_boxplot() +
    scale_defender +
    labs(y = "Defender point ratio", x = "Class under Test")
) |> pr("point_ratios")

(
  deftests |>
    filter(opponent_llm_or_human == "Human") |>
    summarise(.by = c(
      game_id,
      study, 
      author_llm_or_human,
      opponent_opinion_human_or_ai, 
      opponent_opinion_challenged, 
      opponent_opinion_judged_original,
      opponent_opinion_judged_programming_skill
    )) |>
    pivot_longer(
      cols = c(
        opponent_opinion_human_or_ai, 
        opponent_opinion_challenged, 
        opponent_opinion_judged_original, 
        opponent_opinion_judged_programming_skill
        ),
      names_to = "question",
      values_to = "answer"  
      ) |>
    mutate(
      question = factor(question,
        levels = c(
          "opponent_opinion_human_or_ai",
          "opponent_opinion_challenged",
          "opponent_opinion_judged_original",
          "opponent_opinion_judged_programming_skill"
        ), labels = c(
          "Human or AI?",
          "Challenging?",
          "Resourceful?",
          "Skillful?"
        )
      ),
      answer = ordered(answer),
      author_llm_or_human = factor(author_llm_or_human,
                                   levels = c("LLM", "Human"),
                                   labels = c("LLM Defender", "Human Defender")
                                   )
    ) |>
    summarise(n = n(), .by = c(question, author_llm_or_human, answer)) |>
    ggplot(aes(y = question, fill = answer, x = n)) +
    facet_grid(author_llm_or_human ~ .) +
    geom_col(position = position_stack(reverse = TRUE)) +
    geom_text(aes(label = n), position = position_stack(0.5, reverse = TRUE)) +
    labs(y = NULL, x = NULL, fill = "Answer")
) |> pr("defender_opponent_opinions")

(
  mutants |>
    filter(opponent_llm_or_human == "Human") |>
    summarise(.by = c(
      game_id,
      study, 
      author_llm_or_human,
      opponent_opinion_human_or_ai, 
      opponent_opinion_challenged, 
      opponent_opinion_judged_original,
      opponent_opinion_judged_programming_skill
    )) |>
    pivot_longer(
      cols = c(
        opponent_opinion_human_or_ai, 
        opponent_opinion_challenged, 
        opponent_opinion_judged_original, 
        opponent_opinion_judged_programming_skill
      ),
      names_to = "question",
      values_to = "answer"  
    ) |>
    mutate(
      question = factor(question,
                        levels = c(
                          "opponent_opinion_human_or_ai",
                          "opponent_opinion_challenged",
                          "opponent_opinion_judged_original",
                          "opponent_opinion_judged_programming_skill"
                        ), labels = c(
                          "Human or AI?",
                          "Challenging?",
                          "Resourceful?",
                          "Skillful?"
                        )
      ),
      answer = ordered(answer),
      author_llm_or_human = factor(author_llm_or_human,
                                   levels = c("LLM", "Human"),
                                   labels = c("LLM Attacker", "Human Attacker")
      )
    ) |>
    summarise(n = n(), .by = c(question, author_llm_or_human, answer)) |>
    ggplot(aes(y = question, fill = answer, x = n)) +
    facet_grid(author_llm_or_human ~ .) +
    geom_col(position = position_stack(reverse = TRUE)) +
    geom_text(aes(label = n), position = position_stack(0.5, reverse = TRUE)) +
    labs(y = NULL, x = NULL, fill = "Answer")
) |> pr("attacker_opponent_opinions")


turing_defender_reasons <- attacker_games |>
  inner_join(defender_games, join_by(game_id, cut, round), suffix = c(".attacker", ".defender"))|>
  select(!starts_with("opponent")) |>
  filter(author_llm_or_human.attacker == "Human") |>
  mutate(
    winner = ifelse(points.attacker > points.defender, "Attacker wins", "Defender wins"),
    judged = recode_values(
      author_opinion_human_or_ai.attacker,
        1 ~ "Human",
        2 ~ "Human",
        3 ~ "Unsure",
        4 ~ "AI",
        5 ~ "AI"
      ),
    point_ratio = points.defender / points.attacker
    ) |>
  select(winner, point_ratio, author_llm_or_human.defender, judged, author_opinion_human_or_ai_reason.attacker) |> 
  arrange(author_llm_or_human.defender, judged)

turing_attacker_reasons <- attacker_games |>
  inner_join(defender_games, join_by(game_id, cut, round), suffix = c(".attacker", ".defender"))|>
  select(!starts_with("opponent")) |>
  filter(author_llm_or_human.defender == "Human") |>
  mutate(
    winner = ifelse(points.attacker > points.defender, "Attacker wins", "Defender wins"),
    judged = recode_values(
      author_opinion_human_or_ai.defender,
      1 ~ "Human",
      2 ~ "Human",
      3 ~ "Unsure",
      4 ~ "AI",
      5 ~ "AI"
    ),
    point_ratio = points.attacker / points.defender
  ) |>
  select(winner, point_ratio, author_llm_or_human.attacker, judged, author_opinion_human_or_ai_reason.defender) |> 
  arrange(author_llm_or_human.attacker, judged)

(
  deftests |>
    #filter(opponent_llm_or_human == "Human") |>
    summarise(fresh_killed_mutants = mean(fresh_killed_mutants), .by = c(author_llm_or_human, cut, opponent_llm_or_human)) |>
    mutate(opponent_llm_or_human = paste(opponent_llm_or_human, "Attacker")) |>
    ggplot(aes(y = fresh_killed_mutants, x = cut, fill = author_llm_or_human)) +
    geom_col(position = "dodge") +
    scale_percentage_bars(with.percent = FALSE) +
    scale_defender +
    labs(y = "Average kills", fill = "Defender", x = "Class under Test") +
    facet_wrap( ~ opponent_llm_or_human)
) |> pr("fresh_kills")


significance_string <- function(p) {
  s <- if (p < 0.001) {
    "***"
  } else if (p < 0.01) {
    "**"
  } else if (p < 0.05) {
    "*"
  } else if (p < 0.1) {
    "."
  } else {
    ""
  }
  paste(percent(p, accuracy = 0.0001, drop0trailing = TRUE), s, sep = " ")
}

# Creates a df with the estimates and p-values of how each dependent variable
# is correlated to the independent variable
run_regressions <- function(df, independent_var, dependent_vars) {
  
  results <- lapply(dependent_vars, function(dep_var) {
    
    formula <- as.formula(paste(dep_var, "~", independent_var))
    model   <- lm(formula, data = df)
    coefs   <- summary(model)$coefficients
    
    # Extract the row for the independent variable
    data.frame(
      ` ` = dep_var,
      `Estimate` = round(coefs[independent_var, "Estimate"], digits = 4),
      `Std. Error` = round(coefs[independent_var, "Std. Error"], digits = 4),
      `p-value` = significance_string(coefs[independent_var, "Pr(>|t|)"]),
      check.names = FALSE,
      row.names = NULL
    )
  })
  
  do.call(rbind, results)
}


defender_games |>
  filter(opponent_llm_or_human == "Human") |>
  mutate(is_human = as.numeric(author_llm_or_human == "Human")) |>
  run_regressions("is_human", c(
    "opponent_opinion_judged_programming_skill",
    "opponent_opinion_judged_original",
    "opponent_opinion_challenged", 
    "opponent_opinion_human_or_ai"
    )) |>
  mutate(
    ` ` = factor(` `,
                 levels = c(
                   "opponent_opinion_human_or_ai",
                   "opponent_opinion_challenged",
                   "opponent_opinion_judged_original",
                   "opponent_opinion_judged_programming_skill"
                 ), labels = c(
                   "Human or AI?",
                   "Challenging?",
                   "Resourceful?",
                   "Skillful?"
                 )
    )
  ) |>
  csv("def_opinion_regressions")

attacker_games |>
  filter(opponent_llm_or_human == "Human") |>
  mutate(is_human = as.numeric(author_llm_or_human == "Human")) |>
  run_regressions("is_human", c(
    "opponent_opinion_judged_programming_skill",
    "opponent_opinion_judged_original",
    "opponent_opinion_challenged", 
    "opponent_opinion_human_or_ai"
  )) |>
  mutate(
    ` ` = factor(` `,
                      levels = c(
                        "opponent_opinion_human_or_ai",
                        "opponent_opinion_challenged",
                        "opponent_opinion_judged_original",
                        "opponent_opinion_judged_programming_skill"
                      ), labels = c(
                        "Human or AI?",
                        "Challenging?",
                        "Resourceful?",
                        "Skillful?"
                      )
                  )
    ) |>
  csv("att_opinion_regressions")


(
  mutants |> 
    filter(has_been_killed) |>
    mutate(
      defeated = existing_tests_killed_by + future_tests_killed_by > 0,
      author_llm_or_human = paste(author_llm_or_human, "Attacker")  
    ) |>
    summarise(defeated = mean(defeated), .by = c(cut, author_llm_or_human, opponent_llm_or_human)) |>
    ggplot(aes(y = defeated, x = cut, fill = opponent_llm_or_human)) +
    #geom_col(position = "dodge") +
    scale_percentage_bars() +
    scale_defender +
    facet_wrap( ~ author_llm_or_human) + 
    labs(y = NULL, x = "Class under Test")
) |> pr("eventual_defeat_rate_tests")







(
  equivalent_rate = mutants |>
    mutate(is_equivalent = !has_been_killed) |>
    summarise(is_equivalent = mean(is_equivalent), .by = c(cut, author_llm_or_human, opponent_llm_or_human)) |>
    ggplot(aes(x = author_llm_or_human, fill = opponent_llm_or_human, y = is_equivalent)) +
    scale_percentage_bars() +
    facet_wrap(~ cut) + 
    labs(x = "Attacker", y = "Equivalence rate") +
    scale_defender
) |> pr("eq_rates")

mutants |>
  summarise(
    across(c(eqtests_survived, eqtests_killed_by, deftests_killed_by, deftests_survived), mean),
    .by = c(author_llm_or_human, opponent_llm_or_human, cut)
  )

mutants |> 
  filter(deftests_killed_by == 0) |>
  select(c(eqtests_survived, eqtests_killed_by, deftests_killed_by, deftests_survived))

mutants |> 
  select(c(opponent_llm_or_human, eqtests_survived, eqtests_killed_by, deftests_killed_by, deftests_survived)) |>
  summarise(n = n(), .by = c(deftests_killed_by, opponent_llm_or_human))

mutants |>
  mutate(kbe = eqtests_killed_by > 0, kbd = deftests_killed_by > 0) |>
  summarise(kbe = mean(kbe), .by = c(opponent_llm_or_human, kbd)) |> 
  filter(!kbd) |>
  select(opponent_llm_or_human, kbe) |>
  pivot_wider(names_from = opponent_llm_or_human, values_from = kbe)

(
  mutants |>
    filter(has_been_killed) |>
    pivot_longer(
      cols = c(evasion_rate, llm_evasion_rate, human_evasion_rate, existing_evasion_rate, future_evasion_rate),
      names_to = "evasion_rate_type",
      values_to = "evasion_rate_value"
    ) |>
    mutate(evasion_rate_type = factor(evasion_rate_type, 
                                      levels = c(
                                        "evasion_rate", 
                                        "human_evasion_rate", 
                                        "llm_evasion_rate", 
                                        "existing_evasion_rate",
                                        "future_evasion_rate"),
                                      labels = c(
                                        "Total evasion rate",
                                        "Against human tests",
                                        "Against llm tests",
                                        "Against existing tests",
                                        "Against future tests"
                                      ))) |>
    ggplot(aes(x = author_llm_or_human, y = evasion_rate_value, fill = opponent_llm_or_human)) + 
    geom_boxplot(position = "dodge") + 
    xlab("Attacker") +
    scale_y_continuous(name = "Evasion rate", labels = percent) + 
    scale_fill_manual(name = "Defender", values = colors.actor) +
    facet_wrap( ~ evasion_rate_type)#todo: diamond for means??
) |> pr("evasion_rates")

coverage_plot <- function(.data, type) {
  if (! type %in% c("t", "m")) {
    stop("type must be t or m")
  }
  old_lines <- if (type == "t") "lines_covered" else "mutatedlines"
  old_id <- if (type == "t") "test_id" else "mutant_id"
  
  .data |>
    rename(.lines = !!sym(old_lines)) |>
    rename(.id = !!sym(old_id)) |>
    select(author_llm_or_human, opponent_llm_or_human, .lines, .id) |>
    separate_longer_delim(.lines, delim=",") |>
    unique() |>
    mutate(linecount = 1) |>
    pivot_wider(
      names_from = .lines, 
      values_from = linecount, 
      names_prefix = "lineno_",
      values_fill = 0
    ) |>
    summarise(across(starts_with("lineno_"), mean), .by = c(
      "author_llm_or_human",
      "opponent_llm_or_human"
    )) |>
    pivot_longer(
      cols = starts_with("lineno_"),
      names_to = "line_number",
      names_prefix = "lineno_",
      values_to = "line_mean",
      names_transform = as.integer
    ) |>
    mutate(
      author_llm_or_human = paste(author_llm_or_human, if (type == "t") "Defender" else "Attacker"),
      opponent_llm_or_human = paste(opponent_llm_or_human, if (type == "m") "Defender" else "Attacker")) |>
    ggplot(aes(x = line_number, y = line_mean)) +
    geom_col() +
    facet_grid(author_llm_or_human ~ opponent_llm_or_human) +
    scale_y_continuous(labels = percent)
}

(mutants |>
  filter(cut == "CharRange") |>
  coverage_plot("m") +
  labs(y = "Mutants that change this line", x = "Line number")
) |> pr("cr_lines_mutants")


(mutants |>
  #filter(cut == "ByteVector") |>
  coverage_plot("m") +
  labs(y = "Mutants that change this line", x = "Line number")
  ) |> pr("bv_lines_mutants")



method_coverage_plot <- function(.data, type) {
  if (! type %in% c("t", "m")) {
    stop("type must be t or m")
  }
  old_lines <- if (type == "t") "lines_covered" else "mutatedlines"
  old_id <- if (type == "t") "test_id" else "mutant_id"
  mapping <- if (.data[1,"cut"] == "ByteVector") ByteVector_mapping else CharRange_mapping
  
  .data |>
    rename(.lines = !!sym(old_lines)) |>
    rename(.id = !!sym(old_id)) |>
    select(author_llm_or_human, .lines, .id) |>
    separate_longer_delim(.lines, delim=",") |>
    mutate(.lines = mapping(.lines)) |>
    unique() |>
    mutate(linecount = 1) |>
    pivot_wider(
      names_from = .lines, 
      values_from = linecount, 
      names_prefix = "lineno_",
      values_fill = 0
    ) |>
    summarise(across(starts_with("lineno_"), mean), .by = c(
      "author_llm_or_human"
    )) |>
    pivot_longer(
      cols = starts_with("lineno_"),
      names_to = "line_number",
      names_prefix = "lineno_",
      values_to = "line_mean",
      names_transform = factor
    ) |>
    mutate(
      author_llm_or_human = paste(author_llm_or_human, if (type == "t") "Defender" else "Attacker")
      #opponent_llm_or_human = paste(opponent_llm_or_human, if (type == "m") "Defender" else "Attacker")
    ) |>
    ggplot(aes(y = line_number, x = line_mean)) +
    geom_col() +
    facet_grid( ~ author_llm_or_human) +
    scale_x_continuous(labels = percent) +
    theme(axis.text.y = element_text(size = 10))
}

(
  (mutants |>
  filter(cut == "ByteVector") |>
  method_coverage_plot("m") +
  labs(title = "ByteVector", x = "Mutants that change this method", y = NULL)) /
    (mutants |>
       filter(cut == "CharRange") |>
       method_coverage_plot("m") +
       labs(title = "CharRange", x = "Mutants that change this method", y = NULL))
) |> pr("mutants_bv_methods", height = 8)

({
  mutant_methods_gini_bv <- mutants |>
    filter(cut == "ByteVector") |>
    separate_longer_delim(mutated_methods, ",") |>
    mutate(mutated_methods = factor(mutated_methods)) |>
    summarise(n = n(), .by = c(author_llm_or_human, game_id, mutated_methods)) |>
    summarise(gini = fixed_gini(n, length(levels(mutated_methods))), .by = c(author_llm_or_human, game_id)) |>
    summarise(gini = mean(gini), .by = author_llm_or_human) |>
    pivot_wider(values_from = gini, names_from = author_llm_or_human)
  
  total_llm_mutant_method_gini_bv <- (mutants |>
                                       filter(cut == "ByteVector", author_llm_or_human == "LLM") |>
                                       summarise(n = n(), .by = mutated_methods) |>
                                       summarise(gini = Gini(n)))$gini
  
  total_human_mutant_method_gini_bv <- (mutants |>
                                        filter(cut == "ByteVector", author_llm_or_human == "Human") |>
                                        summarise(n = n(), .by = mutated_methods) |>
                                        summarise(gini = Gini(n)))$gini
  
  mutant_methods_gini_cr <- mutants |>
    filter(cut == "CharRange") |>
    separate_longer_delim(mutated_methods, ",") |>
    mutate(mutated_methods = factor(mutated_methods)) |>
    summarise(n = n(), .by = c(author_llm_or_human, game_id, mutated_methods)) |>
    summarise(gini = fixed_gini(n, length(levels(mutated_methods))), .by = c(author_llm_or_human, game_id)) |>
    summarise(gini = mean(gini), .by = author_llm_or_human) |>
    pivot_wider(values_from = gini, names_from = author_llm_or_human)
  
  total_llm_mutant_method_gini_cr <- (mutants |>
                                       filter(cut == "CharRange", author_llm_or_human == "LLM") |>
                                       summarise(n = n(), .by = mutated_methods) |>
                                       summarise(gini = Gini(n)))$gini
  
  total_human_mutant_method_gini_cr <- (mutants |>
                                        filter(cut == "CharRange", author_llm_or_human == "Human") |>
                                        summarise(n = n(), .by = mutated_methods) |>
                                        summarise(gini = Gini(n)))$gini
  
  
  
  n_mutants_with_multiple_methods <- mutants |> filter(grepl(",", fixed = TRUE, mutated_methods)) |> nrow()
  
  data.frame(
    tmp = c("Avg. per game – Humans", "Avg. per game – LLMs", "Total – Humans", "Total – LLMs"),
    ByteVector = round(c(
      mutant_methods_gini_bv$Human,
      mutant_methods_gini_bv$LLM,
      total_human_mutant_method_gini_bv,
      total_llm_mutant_method_gini_bv
    ), digits = 2),
    
    CharRange = round(c(
      mutant_methods_gini_cr$Human,
      mutant_methods_gini_cr$LLM,
      total_human_mutant_method_gini_cr,
      total_llm_mutant_method_gini_cr
    ), digits = 2)
  )

}) |> rename(`Gini Coefficient` = tmp) |> csv("ginis")
