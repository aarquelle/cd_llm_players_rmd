#install.packages("svglite")
#install.packages("readr")
#install.packages("ineq")
#install.packages("tidyverse")
library(tidyverse)
library(jsonlite)
library(svglite)
library(scales)
library(patchwork)
library(ineq)

if (FALSE) {
  rm(list = ls())
  source("scripts/load_data.R")
  source("scripts/load_pretests.R")
  dedup_tests_per_game <-  deftests |>
    select(game_id, study, cut, author_llm_or_human, opponent_llm_or_human, test_file) |>
    mutate(test_code = sapply(paste("rawdata/", study, "/datadir/", test_file, sep = ""), \(f) paste(read_lines(f), collapse = "\n"))) |>
    summarise(n = n(), .by = c(game_id, study, cut, author_llm_or_human, opponent_llm_or_human, test_code))
}

theme_set(theme_light(base_size = 20, base_family = "Libertinus Serif", header_family = "Libertinus Sans") + theme(legend.position = "bottom", ))
update_geom_defaults("text", list(size = 7, family = "Libertinus Sans"))
options(ggplot2.ordinal.fill = function(...) scale_fill_viridis_d(..., option = "viridis", begin = 0.4, end = 1))

add_test_code <- function(df) {
  df |>
    mutate(
      test_code = read_file(paste("rawdata/", study, "/datadir/", test_file, sep = "")),
      test_code = substring(test_code, first = regexpr("public void test()", test_code)[1])
    )
}

.simpleCap <- function(x) {
  s <- strsplit(x, " ")[[1]]
  paste(toupper(substring(s, 1, 1)), substring(s, 2),
        sep = "", collapse = " ")
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

scale_percentage_bars <- function(dodge_value = 0.9, vjust = -0.2, with.percent = TRUE, text_size = 7, show.legend = TRUE) {
  list(
    if (with.percent) scale_y_continuous(labels = percent, expand = expansion(mult = c(0, 0.1))) else scale_y_continuous(expand = expansion(mult = c(0, 0.1))),
    geom_col(position = "dodge", show.legend = show.legend),
    geom_text(
      aes(label = if(with.percent) percent(after_stat(y), accuracy = 0.1) else round(after_stat(y), digits = 2)),
      position = position_dodge(dodge_value), 
      vjust = vjust,
      size = text_size
    )
  )
}

regression_as_table <- function(model) {
  coeffs = summary(model)$coefficients
  coeffs |> 
    data.frame() |>
    rename(`Std. Error` = Std..Error, `p-value` = Pr...t..) |>
    select(!t.value) |>
    mutate(across(c(Estimate, `Std. Error`), function(x) {round(x, digits = 4)})) |>
    mutate(`p-value` = sapply(`p-value`, significance_string)) |>
    rownames_to_column("Coefficient") |>
    mutate(Coefficient = gsub("`", "", Coefficient, fixed = TRUE))
    
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
  paste(signif(p, 3), s, sep = " ")
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

p.value <- function(s, variable) {
  s$coefficients[variable, "Pr(>|t|)"]
}

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
  
  number_llmvsllm_games = mutants |> filter(study == "llmvsllm") |> select(game_id) |> unique() |> nrow(),
  number_llmvsllm_bytevector_games = mutants |> filter(study == "llmvsllm") |> filter(cut == "ByteVector") |> select(game_id) |> unique() |> nrow(),
  number_llmvsllm_charrange_games = mutants |> filter(study == "llmvsllm") |> filter(cut == "CharRange") |> select(game_id) |> unique() |> nrow(),
  
  total_tests = total_tests |> pivot_wider(names_from = cut, values_from = number_of_tests),
  
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
  
  sensitive_equality_example = (deftests |> 
      filter(author_llm_or_human == "LLM", smell_sensitive_equality, !smell_eager_test, cut == "CharRange") |> 
      head(1) |>
      add_test_code() |>
      select(test_code))$test_code,
  
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
    eq ~
      author_llm_or_human +
      cut +
      opponent_llm_or_human
    , data = mutants |> summarise(.by = c(author_llm_or_human, cut, opponent_llm_or_human), eq = mean(!has_been_killed))
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
  
  att_point_ratios_summary= attacker_games |>
    inner_join(defender_games, join_by(game_id, cut, round), suffix = c(".attacker", ".defender"))|>
    select(!starts_with("opponent")) |> 
    filter(author_llm_or_human.defender == "Human") |>
    mutate(point_ratio = points.attacker / ifelse(points.defender != 0, points.defender, 1)) |> 
    select(point_ratio, cut, author_llm_or_human.attacker) |>
    summarise(mean = mean(point_ratio), sd = sd(point_ratio), max = max(point_ratio), min = min(point_ratio), .by = c(cut, author_llm_or_human.attacker)) |>
    pivot_wider(names_from = c(cut, author_llm_or_human.attacker), values_from = everything()),
  
  regression_att_point_author = attacker_games |>
    inner_join(defender_games, join_by(game_id, cut, round), suffix = c(".attacker", ".defender"))|>
    select(!starts_with("opponent")) |> 
    filter(author_llm_or_human.defender == "Human") |>
    mutate(point_ratio = points.attacker / ifelse(points.defender != 0, points.defender, 1)) |> 
    select(point_ratio, cut, author_llm_or_human.attacker) %>%
    lm(data = ., point_ratio ~ author_llm_or_human.attacker + cut) |>
    summary() |>
    p.value("author_llm_or_human.attackerHuman"),
  
  wasted_tokens_mutants = all_conversations |>
    filter(strategy == "MUTANT_ANNOTATED_SINGLE_METHOD") |>
    mutate(failed = is.na(mutant_id) & type == "ATTACK_DEPENDENCIES") |>
    summarise(across(c(input_tokens, output_tokens), sum), .by = failed) |>
    pivot_wider(names_from = failed, values_from = !failed) |>
    mutate(
      input = input_tokens_TRUE / (input_tokens_TRUE + input_tokens_FALSE),
      output = output_tokens_TRUE / (output_tokens_TRUE + output_tokens_FALSE)
      ),
  
  selection_tokens_mutants = all_conversations |>
    filter(strategy == "MUTANT_ANNOTATED_SINGLE_METHOD") |>
    mutate(
      sinput = ifelse(type == "ATTACK_DEFAULT", input_tokens, 0), 
      soutput = ifelse(type == "ATTACK_DEFAULT", output_tokens, 0)
    ) |>
    summarise(across(c(sinput, soutput, input_tokens, output_tokens), sum)) |>
    mutate(input = sinput / input_tokens, output = soutput / output_tokens),
  
  set_for_all_mutants = list(CharRange = covering_tests(mutants |> filter(cut == "CharRange")), ByteVector = covering_tests(mutants |> filter(cut == "ByteVector"))),
  
  average_sets = attacker_games |> 
    summarise(across(killing_test_set, mean), .by = c(cut)) |>
    pivot_wider(names_from = cut, values_from = killing_test_set),
  
  shortest_test_error = (all_messages |>
    filter(message_type == "SYSTEM", index_in_conversation == 3, strategy == "TEST_FULL_SUITE_PLUS_DEFAULT") |> 
    select(content) |>
    mutate(content_size = nchar(content)) |>
    slice_min(content_size, n = 1))$content,
  
  average_defender_costs = all_conversations |>
    filter(strategy == "TEST_FULL_SUITE_PLUS_DEFAULT") |>
    summarise(.by = c(game_id, study), across(c(input_tokens, output_tokens), mean)) |>
    summarise(across(c(input_tokens, output_tokens), mean)),
  
  average_attacker_costs = all_conversations |>
    filter(strategy == "MUTANT_ANNOTATED_SINGLE_METHOD") |>
    summarise(.by = c(game_id, study), across(c(input_tokens, output_tokens), mean)) |>
    summarise(across(c(input_tokens, output_tokens), mean)),
    
    
    
  
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
      y = xp_type, x = n, fill = xp_value
    )) +
    geom_col(position = "stack") +
    geom_text(aes(label = after_stat(x)), position = position_stack(vjust = 0.5)) +
    labs(
      fill = "Experience",
      y = NULL,
      x = "Count"
    ) +
    theme(legend.position = "right")
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

attacker_games |>
  inner_join(defender_games, join_by(game_id, cut, round), suffix = c(".attacker", ".defender"))|>
  select(!starts_with("opponent")) |>
  filter(author_llm_or_human.attacker == "Human") |>
  mutate(
    `Point ratio` = points.defender / ifelse(points.attacker != 0, points.attacker, 1),
    `Defender is LLM` = as.numeric(author_llm_or_human.defender == "LLM"),
    `CuT is CharRange` = as.numeric(cut == "CharRange"),
    `2nd experiment round` = as.numeric(round == "Round 2")
  
    )%>% 
  #run_regressions("is_llm", dependent_vars = c("point_ratio", "Win"), control_str = " + cut + round")
  lm(data = ., `Point ratio` ~ `Defender is LLM` + `CuT is CharRange` + `2nd experiment round`) |>
  regression_as_table() |>
  csv("point_ratios_regression")

(
  deftests |>
    filter(opponent_llm_or_human == "Human") |>
    summarise(.by = c(
      game_id,
      study, 
      author_llm_or_human,
      opponent_opinion_human_or_ai, 
      opponent_opinion_judged_original,
      opponent_opinion_judged_programming_skill
    )) |>
    pivot_longer(
      cols = c(
        opponent_opinion_human_or_ai, 
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
          "opponent_opinion_judged_original",
          "opponent_opinion_judged_programming_skill"
        ), labels = c(
          "Human or AI?",
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
      opponent_opinion_judged_original,
      opponent_opinion_judged_programming_skill
    )) |>
    pivot_longer(
      cols = c(
        opponent_opinion_human_or_ai, 
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
                          "opponent_opinion_judged_original",
                          "opponent_opinion_judged_programming_skill"
                        ), labels = c(
                          "Human or AI?",
                          "Resourceful?",
                          "Skillful?"
                        )
      ),
      answer = ordered(answer),
      author_llm_or_human = factor(author_llm_or_human,
                                   levels = c("LLM", "Human"),
                                   labels = c("LLM Att.", "Human Att.")
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




# Creates a df with the estimates and p-values of how each dependent variable
# is correlated to the independent variable
run_regressions <- function(df, independent_var, dependent_vars, control_str = "") {
  
  results <- lapply(dependent_vars, function(dep_var) {
    
    formula <- as.formula(paste(
      dep_var, 
      "~", 
      independent_var, 
      control_str))
    print(formula)
    model   <- lm(formula, data = df)
    coefs   <- summary(model)$coefficients
    print(summary(model))
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
    "opponent_opinion_human_or_ai"
    ), control_str = " + cut + round") |>
  mutate(
    ` ` = factor(` `,
                 levels = c(
                   "opponent_opinion_human_or_ai",
                   "opponent_opinion_judged_original",
                   "opponent_opinion_judged_programming_skill"
                 ), labels = c(
                   "Human or AI?",
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
    "opponent_opinion_human_or_ai"
  ), control_str = " + cut + round") |>
  mutate(
    ` ` = factor(` `,
                      levels = c(
                        "opponent_opinion_human_or_ai",
                        "opponent_opinion_judged_original",
                        "opponent_opinion_judged_programming_skill"
                      ), labels = c(
                        "Human or AI?",
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
    theme(legend.position = "right") +
    facet_wrap( ~ evasion_rate_type)#todo: diamond for means??
) |> pr("evasion_rates")

mutants |>
  filter(has_been_killed) |>
  mutate(is_llm = as.numeric(author_llm_or_human == "LLM")) |>
  summarise(.by = c(is_llm, game_id, round, cut, opponent_llm_or_human), across(ends_with("evasion_rate"), mean)) |>
  run_regressions("is_llm", c("evasion_rate", "llm_evasion_rate", "human_evasion_rate", "existing_evasion_rate", "future_evasion_rate"), "+ round + cut + opponent_llm_or_human") |>
  rename(`Evasion rate type` = ` `) |>
  mutate(`Evasion rate type` = replace_values(`Evasion rate type`,
                                              "evasion_rate" ~ "Total evasion rate",
                                              "llm_evasion_rate" ~ "Against LLM tests",
                                              "human_evasion_rate" ~ "Against human tests",
                                              "existing_evasion_rate" ~ "Against existing tests (same game)",
                                              "future_evasion_rate" ~ "Against future tests (same game)"
                                              )) |>
  csv("evasion_rate_regression")
  

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
  labs(title = "ByteVector", x = NULL, y = NULL)) /
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

(
  attacker_games |>
    inner_join(defender_games, join_by(game_id, cut, round), suffix = c(".attacker", ".defender"))|>
    select(!starts_with("opponent")) |> 
    filter(author_llm_or_human.defender == "Human") |>
    mutate(point_ratio = points.attacker / ifelse(points.defender != 0, points.defender, 1)) |> 
    select(study, game_id, points.attacker, points.defender, point_ratio, author_llm_or_human.attacker, cut) |>
    filter(point_ratio < 5) |>
    ggplot(aes(x = author_llm_or_human.attacker, y = point_ratio)) +
    geom_boxplot() +
    #ylab("Defender points / attacker points") +
    facet_wrap( ~ cut)
) |> pr("attacker_point_ratios")

(
  attacker_games |>
    inner_join(defender_games, join_by(game_id, cut, round), suffix = c(".attacker", ".defender"))|>
    select(!starts_with("opponent")) |> 
    filter(author_llm_or_human.defender == "Human") |>
    mutate(point_ratio = points.attacker / ifelse(points.defender != 0, points.defender, 1)) |> 
    select(point_ratio, cut, author_llm_or_human.attacker) |>
    summarise(mean = mean(point_ratio), sd = sd(point_ratio), max = max(point_ratio), min = min(point_ratio), n = n(), .by = c(cut, author_llm_or_human.attacker)) |>
    mutate(across(c(mean, sd, max, min, n), function(x) {round(x, digits = 2)})) |>
    rename(sigma = sd, Attacker = author_llm_or_human.attacker, CuT = cut) |>
    arrange(CuT, sigma)
    #pivot_wider(names_from = c(cut, author_llm_or_human.attacker), values_from = everything())
) |> csv("attacker_point_ratios")

(
  mutants |>
    mutate(
      `Minutes since start` = seconds_since_gamestart / 60,
      `Attacker is LLM` = as.numeric(author_llm_or_human == "LLM"),
      `Defender is LLM` = as.numeric(opponent_llm_or_human == "LLM"),
      `CuT is CharRange` = as.numeric(cut == "CharRange")
    ) %>%
    #filter(opponent_llm_or_human == "Human") %>%
    lm(
      data = ., 
      is_stillborn ~ 
        `Minutes since start` +
        `Attacker is LLM` +
        `Defender is LLM` +
        `CuT is CharRange`
      
    ) |>
    regression_as_table()
) |> csv("regression_mutants_stillborn")

(
  deftests |>
    filter(author_llm_or_human == "Human") |>
    separate_longer_delim(lines_covered, ",") |>
    summarise(
      .by = c(
        lines_covered,
        game_id,
        cut,
        opponent_llm_or_human
      )
    ) |>
    left_join(n_loc, join_by(cut)) |>
    summarise(
      lines_covered = n(),
      .by = c(
        game_id,
        cut,
        opponent_llm_or_human,
        loc_for_cut
      )
    ) |>
    mutate(
      line_coverage = lines_covered / loc_for_cut,
      `Attacker is LLM` = as.numeric(opponent_llm_or_human == "LLM"),
      `CuT is CharRange` = as.numeric(cut == "CharRange")
    ) %>%
    lm(
      data = .,
      line_coverage ~ `Attacker is LLM` + `CuT is CharRange`
    ) |> regression_as_table()
) |> csv("human_line_coverage")

{
  suite_stats <- deftests |>
    filter(author_llm_or_human == "Human") |>
    separate_longer_delim(lines_covered, ",") |>
    summarise(
      .by = c(
        lines_covered,
        game_id,
        cut,
        opponent_llm_or_human
      )
    ) |>
    left_join(n_loc, join_by(cut)) |>
    summarise(
      lines_covered = n(),
      .by = c(
        game_id,
        cut,
        opponent_llm_or_human,
        loc_for_cut
      )
    ) |>
    mutate(
      line_coverage = lines_covered / loc_for_cut,
      is_llm = as.numeric(opponent_llm_or_human == "LLM")
    ) |>
    left_join(defender_games, join_by(game_id, cut)) %>% 
    run_regressions("is_llm", c("number_of_tests" ,"line_coverage", "mutation_score", "author_opinion_fun", "author_opinion_challenged"), control_str = "+ cut + round")
  
  individual_stats <- deftests |>
    filter(author_llm_or_human == "Human") |>
    mutate(is_llm = as.numeric(opponent_llm_or_human == "LLM")) |>
    mutate(number_of_smells = smell_assertion_roulette + smell_unknown_test + smell_eager_test + smell_duplicate_assert + smell_sensitive_equality + smell_redundant_assertion) |>
    summarise(.by = c(game_id, cut, round, is_llm), across(c(kill_rate, human_kill_rate, llm_kill_rate, number_of_smells), mean)) |>
    run_regressions("is_llm", c("kill_rate", "human_kill_rate", "llm_kill_rate", "number_of_smells"), control_str = "+ cut + round")
  
  suite_stats |> bind_rows(individual_stats) |>
    rename(Metric = ` `) |>
    mutate(Metric = replace_values(
      Metric,
      "number_of_tests" ~ "Tests per game",
      "line_coverage" ~ "Line coverage",
      "mutation_score"  ~ "Mutation score",
      "kill_rate" ~ "Total kill rate",
      "human_kill_rate" ~ "Kill rate against human mutants",
      "llm_kill_rate" ~ "Kill rate against LLM mutants",
      "number_of_smells"  ~ "Number of test smells",
      "author_opinion_challenged" ~ "Student felt challenged",
      "author_opinion_fun" ~ "Student had fun"
    ))
} |>  csv("human_def_behaviour_corrs")

{
  
  suite_stats <- mutants |>
    filter(author_llm_or_human == "Human") |>
    separate_longer_delim(mutated_methods, ",") |>
    mutate(mutated_methods = factor(mutated_methods)) |>
    summarise(n = n(), .by = c(opponent_llm_or_human, game_id, mutated_methods, has_been_killed, is_stillborn, author_opinion_fun, author_opinion_challenged, cut, round)) |>
    summarise(gini = fixed_gini(n, length(levels(mutated_methods))), .by = c(opponent_llm_or_human, game_id, has_been_killed, is_stillborn, author_opinion_fun, author_opinion_challenged, cut, round)) |>
    summarise(mutants_per_game = n(), across(c(has_been_killed, is_stillborn), mean), .by = c(opponent_llm_or_human, game_id, gini, author_opinion_fun, author_opinion_challenged, cut, round)) |>
    mutate(is_llm = as.numeric(opponent_llm_or_human == "LLM"), is_equivalent = !has_been_killed) |>
    left_join(attacker_games |> select(game_id, killing_test_set), join_by(game_id)) |>
    run_regressions("is_llm", c("gini", "mutants_per_game", "is_equivalent", "is_stillborn", "author_opinion_fun", "author_opinion_challenged", "killing_test_set"), control_str = "+ cut + round")
  
  individual_stats <- mutants |>
    filter(author_llm_or_human == "Human") |>
    mutate(is_llm = as.numeric(opponent_llm_or_human == "LLM")) |>
    summarise(.by = c(is_llm, cut, round, game_id), across(c(evasion_rate, human_evasion_rate, llm_evasion_rate), mean)) |>
    run_regressions("is_llm", c("evasion_rate", "human_evasion_rate", "llm_evasion_rate"), control_str = "+ cut + round")
  
  suite_stats |> bind_rows(individual_stats) |>
    rename(Metric = ` `) |>
    mutate(Metric = replace_values(
      Metric,
      "mutants_per_game" ~ "Mutants per game",
      "is_equivalent"  ~ "Equivalence rate",
      "evasion_rate" ~ "Total evasion rate",
      "human_evasion_rate" ~ "Evasion rate against human tests",
      "llm_evasion_rate" ~ "Evasion rate against LLM tests",
      "author_opinion_challenged" ~ "Student felt challenged",
      "author_opinion_fun" ~ "Student had fun",
      "gini" ~ "Unevenness of mutated methods",
      "is_stillborn" ~ "Stillborn rate",
      "killing_test_set" ~ "Minimum set size"
    ))
} |>  csv("human_att_behaviour_corrs")


(
  deftests |>
    filter(opponent_llm_or_human == "Human") |>
    summarise(.by = c(
      game_id,
      study, 
      author_llm_or_human,
      opponent_opinion_fun, 
      opponent_opinion_challenged
    )) |>
    pivot_longer(
      cols = c(
        opponent_opinion_fun, 
        opponent_opinion_challenged
      ),
      names_to = "question",
      values_to = "answer"  
    ) |>
    mutate(
      question = factor(question,
                        levels = c(
                          "opponent_opinion_challenged",
                          "opponent_opinion_fun"
                        ), labels = c(
                          "Challenging?",
                          "Fun?"
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
) |> pr("defender_opponent_enjoyment")

(
  mutants |>
    filter(opponent_llm_or_human == "Human") |>
    summarise(.by = c(
      game_id,
      study, 
      author_llm_or_human,
      opponent_opinion_fun, 
      opponent_opinion_challenged
    )) |>
    pivot_longer(
      cols = c(
        opponent_opinion_fun, 
        opponent_opinion_challenged
      ),
      names_to = "question",
      values_to = "answer"  
    ) |>
    mutate(
      question = factor(question,
                        levels = c(
                          "opponent_opinion_challenged",
                          "opponent_opinion_fun"
                        ), labels = c(
                          "Challenging?",
                          "Fun?"
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
) |> pr("attacker_opponent_enjoyment")


defender_games |> 
  filter(author_llm_or_human == "Human") |>
  mutate(
    `Attacker is LLM` = as.numeric(opponent_llm_or_human == "LLM"),
    `CuT is CharRange` = as.numeric(cut == "CharRange"),
    `2nd experiment round` = as.numeric(round == "Round 2")
  )%>% 
  lm(data = ., author_opinion_fun ~ `Attacker is LLM` + `CuT is CharRange` + `2nd experiment round`) |> 
  regression_as_table() |> csv("regression_defender_fun")

defender_games |> 
  filter(author_llm_or_human == "Human") |>
  mutate(
    `Attacker is LLM` = as.numeric(opponent_llm_or_human == "LLM"),
    `CuT is CharRange` = as.numeric(cut == "CharRange"),
    `2nd experiment round` = as.numeric(round == "Round 2")
  )%>% 
  lm(data = ., author_opinion_challenged ~ `Attacker is LLM` + `CuT is CharRange` + `2nd experiment round`) |> 
  regression_as_table() |> csv("regression_defender_challenge")

{
  per_submission <- mutants |>
  filter(author_llm_or_human == "LLM") |>
  summarise(
    across(c(input_tokens, output_tokens), mean), 
    mutants_per_game = n(),
    .by = c(cut, game_id)
  )
  #rename(CuT = cut, `Input tokens` = input_tokens, `Output tokens` = output_tokens) 
  
  
  failing <- all_conversations |>
    filter(type == "ATTACK_DEPENDENCIES", is.na(mutant_id)) |>
    summarise(across(c(input_tokens, output_tokens), sum), .by = game_id) |>
    rename(failing_input = input_tokens, failing_output = output_tokens)
  
  selection <- all_conversations |>
    filter(type == "ATTACK_DEFAULT") |>
    summarise(across(c(input_tokens, output_tokens), sum), .by = game_id) |>
    rename(selection_input = input_tokens, selection_output = output_tokens)
  
  per_submission |>
    left_join(failing, join_by(game_id)) |>
    mutate(
      failing_input = ifelse(is.na(failing_input), 0, failing_input),
      failing_output = ifelse(is.na(failing_output), 0, failing_output)
    ) |>
    mutate(
      failing_input = failing_input / mutants_per_game,
      failing_output = failing_output / mutants_per_game
    )|>
    
    left_join(selection, join_by(game_id)) |>
    mutate(
      selection_input = ifelse(is.na(selection_input), 0, selection_input),
      selection_output = ifelse(is.na(selection_output), 0, selection_output)
    ) |>
    mutate(
      selection_input = selection_input / mutants_per_game,
      selection_output = selection_output / mutants_per_game
    )|>
  
    rename(
      success_input  = input_tokens,
      success_output = output_tokens,
      failing_input  = failing_input,
      failing_output = failing_output
    ) %>%
    pivot_longer(
      cols = c(success_input, success_output, failing_input, failing_output, selection_input, selection_output),
      names_to = c("success_failing_selection", "in_out"),
      names_sep = "_",
      values_to = "tokens"
    ) |> 
    summarise(tokens = mean(tokens), .by = c(cut, success_failing_selection, in_out)) |>
    mutate(
      success_failing_selection = recode_values(success_failing_selection,
                                                "success" ~ "Success",
                                                "failing" ~ "Failure",
                                                "selection" ~ "Method selection"
                                                )
    ) |>
    ggplot(aes(y = tokens, fill = success_failing_selection, x = in_out)) +
    geom_col(position = "dodge") +
    facet_wrap(~ cut) +
    scale_fill_manual(values = c("#aaffff", "#00aaaa", "#007777")) +
    labs(fill = NULL, x = NULL, y = "Token count") +
    geom_text(aes(label = round(tokens)), position = position_dodge(width = 0.9), vjust = -0.2) +
    scale_y_continuous(expand = expansion(mult = c(0, 0.1)))
  
} |> pr("mutant_tokens")

{
  (mutants |>
    filter(author_llm_or_human == "LLM") |>
    ggplot(aes(x = input_tokens, fill = factor(number_of_messages))) +
    geom_histogram(position = "stack", bins = 20, show.legend = FALSE) +
    facet_wrap(cut ~ ., ncol = 1) +
    labs(x = "Input tokens")+ theme(axis.text.x = element_text(angle = 45))) +
  (mutants |>
    filter(author_llm_or_human == "LLM") |>
    ggplot(aes(x = output_tokens, fill = factor(number_of_messages))) +
    geom_histogram(position = "stack", bins = 20) +
    scale_fill_discrete(name = "Number of\n messages") +
    facet_wrap(cut ~ ., ncol = 1) +
    labs(x = "Output tokens", y = NULL)+ theme(axis.text.x = element_text(angle = 45))) 
} |> pr("mutant_tokens_grouped_by_messages")

{
  all_messages |>
    filter(startsWith(as.character(rejection_reason), "Mutant")) |>
    summarise(n = n(), .by = c(rejection_reason, cut)) |>
    ggplot(aes(fill = rejection_reason, y = n, x = rejection_reason)) +
    geom_col(position = "dodge", show.legend = FALSE) +
    labs(title = "Rejection reasons for attacker tests",
         x = NULL,#"Conversation is eventually successful",
         y = "Number of failed attemps",
         fill = "Rejection reason") +
    geom_text(aes(label = after_stat(y)), position = position_dodge(0.9), vjust = -0.2) +
    facet_wrap( ~ cut) +
    theme(axis.text.x = element_text(angle = 90))
} |> pr("mutant_rejection_reasons", height = 10)

all_messages |>
  filter(startsWith(as.character(rejection_reason), "Mutant")) |>
  summarise(n = n(), .by = c(rejection_reason, cut)) |>
  mutate(
    rejection_reason = replace_values
    (
      as.character(rejection_reason),
      "Mutant includes a new system call, or a new control structure" ~ "New system call or control structure",
      "Mutant already exists" ~ "Already exists",
      "Mutant has prohibited operations" ~ "Prohibited operations",
      "Mutant is identical to the CuT" ~ "Identical to CuT",
      "Mutant has compile error" ~ "Compile error",
      "Mutant contains new logical operations" ~ "New logical operations"
    )
  ) |>
  pivot_wider(names_from = cut, values_from = n, values_fill = 0) |>
  rename(`Rejection reason` = rejection_reason) |>
  csv("mutant_rejection_reasons")

{
  conversation_count <- all_messages |>
    filter(user_id == 5, message_type == "AI", type == "ATTACK_DEPENDENCIES") |>
    summarise(.by = c(conversation_id, study, cut)) |>
    summarise(total = n(), .by = cut)
  
  all_messages |>
    filter(user_id == 5, message_type == "AI", type == "ATTACK_DEPENDENCIES") |>
    mutate(success = !is.na(mutant_id)) |> 
    summarise(number_of_messages = n(), .by = c(success, conversation_id, study, cut)) |>
    mutate(
      number_of_failures = ifelse(!success, 4, number_of_messages - 1)
    ) %>% 
    summarise(n = n(), .by = c(cut, number_of_failures)) |>
    left_join(conversation_count, join_by(cut)) |>
    mutate(m = n/total) |>
    ggplot(aes(x = number_of_failures, y = m, fill = cut)) +
    scale_cut +
    scale_percentage_bars(text_size = 5) +
    labs(x = "Failures per conversation", y = "Number of conversations")
} |> pr("mutant_failures_per_conversation")

stepwise_success_rate <- function(msgs){
  #Success rates for each message count
  #1: n_0_error / total
  #2: n_1_error / total - n_0_error
  #3: n_2_error / total - n_0_error - n_1_error
  conversation_count <- msgs |>
    summarise(.by = c(conversation_id, study, cut)) |>
    summarise(total = n(), .by = cut)
  
  counts <- msgs |>
    mutate(success = is_success) |>#!is.na(test_id)) |> 
    summarise(number_of_messages = n(), .by = c(success, conversation_id, study, cut)) |>
    mutate(
      number_of_failures = ifelse(!success, 4, number_of_messages - 1)
    ) %>% 
    summarise(n = n(), .by = c(cut, number_of_failures)) |>
    left_join(conversation_count, join_by(cut))
  
  result <- data.frame(cut = c(rep("ByteVector", 4), rep("CharRange", 4)), error = rep(0:3, 2))
  for (cut_l in c("CharRange", "ByteVector")) {
    total <- (counts|>filter(cut == cut_l))[1, "total"]
    for (i in 0:3) {
      succ <- (counts|>filter(cut == cut_l, number_of_failures == i))$n
      result[result$cut == cut_l & result$error == i, "success_rate"] <- succ / total
      total <- total - succ
    }
  }
  
  result |> mutate(error = error + 1)
  
}

(all_messages |>
  filter(user_id == 5, message_type == "AI", type == "ATTACK_DEPENDENCIES") |>
  stepwise_success_rate() |>
  ggplot(aes(x = error, y = success_rate, fill = cut)) +
  scale_percentage_bars(text_size = 6) +
  scale_cut +
  labs(x = "Attempt", y = "Success rate")) |>
  pr("stepwise_mutant_success")

{
  
  
  total_tests <- defender_games |>
    filter(author_user_id == 6) |>
    select(game_id, study, number_of_tests, cut, opponent_llm_or_human) |>
    mutate(is_success = TRUE)

  tests_per_game <- all_conversations |> 
    filter(user_id == 6) |>
    mutate(value = 1) |>
    pivot_wider(names_from = defend_prompt_type, values_from = value, values_fill = 0) |>
    summarise(SUITE = sum(SUITE), SUITE_REPAIR = sum(SUITE_REPAIR), FOCUS = sum(FOCUS), SINGLE = sum(SINGLE), .by = c(study, game_id, is_success)) |>
    left_join(total_tests, join_by(is_success, study, game_id)) |>
    mutate(suite_tests = number_of_tests - SINGLE - FOCUS - SUITE_REPAIR) |>
    pivot_longer(cols = c(SUITE, SUITE_REPAIR, FOCUS, SINGLE, number_of_tests, suite_tests), names_to = "test_type", values_to = "count")
  
  tests_per_game |> 
    filter(is_success) |>
    filter(test_type %in% c("FOCUS", "SINGLE", "SUITE_REPAIR", "suite_tests")) |>
    summarise(count = mean(count), .by = c(test_type, cut, opponent_llm_or_human)) |>
    mutate(test_type = replace_values(test_type, 
                                      "FOCUS" ~ "Focused",
                                      "SINGLE" ~ "Unfocused",
                                      "SUITE_REPAIR" ~ "Repaired suite test",
                                      "suite_tests" ~ "Suite tests"
                                      ),
           opponent_llm_or_human = paste(opponent_llm_or_human, "Attacker")
           ) |>
    ggplot(aes(fill = test_type, y = count, x = cut)) +
    scale_percentage_bars(with.percent = FALSE, text_size = 5) +
    labs(fill = "Generation type", x = NULL, y = "Tests per game") +
    facet_wrap(~ opponent_llm_or_human)
} |> pr("test_generation_types")

{
  
  
  total_tests <- defender_games |>
    filter(author_user_id == 6) |>
    select(game_id, study, number_of_tests, cut)
  
  tests_per_game <- all_conversations |> 
    filter(user_id == 6, is_success) |>
    mutate(value = 1) |>
    pivot_wider(names_from = defend_prompt_type, values_from = value, values_fill = 0) |>
    summarise(SUITE_REPAIR = sum(SUITE_REPAIR), FOCUS = sum(FOCUS), SINGLE = sum(SINGLE), .by = c(study, game_id)) |>
    left_join(total_tests, join_by(study, game_id)) |>
    mutate(suite_tests = number_of_tests - SINGLE - FOCUS - SUITE_REPAIR) |>
    pivot_longer(cols = c(SUITE_REPAIR, FOCUS, SINGLE, suite_tests), names_to = "test_type", values_to = "count")
  
  tokens_per_game <- all_conversations |>
    filter(user_id == 6) |>
    summarise(across(c(input_tokens, output_tokens), sum), .by = c(study, game_id, defend_prompt_type))
  
  tests_per_game |> 
    #filter(is_success) |>
    filter(test_type %in% c("FOCUS", "SINGLE", "SUITE_REPAIR", "suite_tests")) |>
    mutate(test_type = ifelse(test_type == "suite_tests", "SUITE", test_type)) |>
    left_join(tokens_per_game, join_by(study, game_id, test_type == defend_prompt_type)) |>
    mutate(count = ifelse(count == 0, 1, count)) |>
    mutate(input_tokens = input_tokens / count, output_tokens = output_tokens / count) |>
    mutate(input_tokens = replace_na(input_tokens, 0), output_tokens = replace_na(output_tokens, 0)) |>
    summarise(across(c(input_tokens, output_tokens), mean), .by = c(test_type, study)) |>
    mutate(
      test_type = replace_values(test_type, 
                                      "FOCUS" ~ "Focused",
                                      "SINGLE" ~ "Unfocused",
                                      "SUITE_REPAIR" ~ "Repaired suite test",
                                      "SUITE" ~ "Suite tests"
      ),
      study = ifelse(study == "userstudy", "Human attacker", "LLM attacker")
    ) |>
    ggplot(aes(fill = test_type, y = input_tokens, x = "")) +
    scale_percentage_bars(with.percent = FALSE, text_size = 5) +
    labs(fill = "Generation type", x = NULL, y = "Tokens per test") +
    facet_wrap(~ study)
} #|> pr("test_tokens_by_type")#BROKEN!!!

{
  
  
  total_tests <- defender_games |>
    filter(author_user_id == 6) |>
    summarise(number_of_tests = sum(number_of_tests), .by = cut)
  
  tests_per_type <- all_conversations |> 
    filter(user_id == 6, is_success) |>
    mutate(value = 1) |>
    pivot_wider(names_from = defend_prompt_type, values_from = value, values_fill = 0) |>
    left_join(defender_games, join_by(game_id, study)) |>
    summarise(SUITE_REPAIR = sum(SUITE_REPAIR), FOCUS = sum(FOCUS), SINGLE = sum(SINGLE), .by = cut) |>
    left_join(total_tests, join_by(cut)) |>
    mutate(SUITE = number_of_tests - SINGLE - FOCUS - SUITE_REPAIR) |>
    pivot_longer(cols = c(SUITE_REPAIR, FOCUS, SINGLE, SUITE), names_to = "defend_prompt_type", values_to = "count")
  
  tokens_per_type <- all_conversations |>
    filter(user_id == 6) |>
    left_join(defender_games, join_by(game_id, study)) |>
    summarise(across(c(input_tokens, output_tokens), sum), .by = c(defend_prompt_type, cut))
  
  d <- tokens_per_type |>
    left_join(tests_per_type, join_by(defend_prompt_type, cut)) |>
    mutate(input_tokens = input_tokens / count, output_tokens = output_tokens / count) |>
    mutate(
      defend_prompt_type = replace_values(defend_prompt_type, 
                                 "FOCUS" ~ "Focused",
                                 "SINGLE" ~ "Unfocused",
                                 "SUITE_REPAIR" ~ "Repaired suite test",
                                 "SUITE" ~ "Suite tests"
      )
    ) |>
    mutate(input_tokens = ifelse(is.infinite(input_tokens), 0, input_tokens))
    
    (ggplot(d, aes(fill = defend_prompt_type, y = input_tokens, x = cut)) +
    scale_percentage_bars(with.percent = FALSE, text_size = 4) +
    labs(fill = "Generation type", x = NULL, y = "Input") +
    theme(legend.position = "right")
    ) /
    (ggplot(d, aes(fill = defend_prompt_type, y = output_tokens, x = cut)) +
    scale_percentage_bars(with.percent = FALSE, text_size = 4, show.legend = FALSE) +
    labs(fill = "Generation type", x = NULL, y = "Output")
    )
} |> pr("test_tokens_by_type")

{
  default_tests <- all_messages |>
    filter(user_id == 6, message_type == "AI", type %in% c("DEFEND_DEFAULT")) |>
    stepwise_success_rate() |>
    mutate(type = "Unfocused")
  
  focus_tests <- all_messages |>
    filter(user_id == 6, message_type == "AI", type %in% c("DEFEND_FOCUS")) |>
    stepwise_success_rate() |>
    mutate(type = "Focused")
  
  default_tests |>
    bind_rows(focus_tests) |>
    ggplot(aes(x = error, y = success_rate, fill = cut)) +
    scale_percentage_bars(text_size = 4) +
    scale_cut +
    facet_wrap( ~ type) +
    labs(x = "Attempt", y = "Success rate") +
    theme(legend.position = "bottom")
  }|>
  pr("stepwise_test_success")

all_messages |>
  filter(startsWith(as.character(rejection_reason), "Test")) |>
  summarise(n = n(), .by = c(rejection_reason, cut)) |>
  mutate(
    rejection_reason = replace_values
    (
      as.character(rejection_reason),
      "Test does not compile" ~ "Does not compile",
      "Test fails on original" ~ "Fails on original",
      "Test has a prohibited call" ~ "Prohibited call",
      "Test includes an invalid statement" ~ "Invalid statement",
      "Test has too many assertions" ~ "Too many assertions",
      "Tests breaks an unknown rule" ~ "Bitwise operations",
      "Test declares additional classes or methods" ~ "Adds additional classes or methods"
    )
  ) |>
  pivot_wider(names_from = cut, values_from = n, values_fill = 0) |>
  rename(`Rejection reason` = rejection_reason) |>
  csv("test_rejection_reasons")

attacker_games |>
  ggplot(aes(y = killing_test_set, x = author_llm_or_human, fill = cut)) +
  geom_boxplot()

attacker_games |>
  mutate(`Attacker is LLM` = as.numeric(author_llm_or_human == "LLM"),
         `Defender is LLM` = as.numeric(opponent_llm_or_human == "LLM"),
         `CuT is CharRange` = as.numeric(cut == "CharRange")
         ) %>% 
  lm(data = ., killing_test_set ~ `Attacker is LLM` + `Defender is LLM` + `CuT is CharRange`) |>
  regression_as_table() |>
  csv("test_set_regression")

pt_conversation_stats |>
  filter(experiment_name == "Pretest", startsWith(strategy, "MUTANT")) |>
  arrange(-point_ratio) |>
  select(strategy, is_success, point_ratio, input_tokens, output_tokens) |>
  mutate(
    strategy = strategy %>% gsub("MUTANT", "", .) %>% gsub("_", " ", .) %>% tolower() %>% sapply(.simpleCap),
    point_ratio = round(point_ratio, digits = 3),
    is_success = percent(is_success, accuracy = 0.01),
    input_tokens = round(input_tokens / 30),
    output_tokens = round(output_tokens / 30)
  ) |>
  rename(Strategy = strategy, `Success rate` = is_success, `Point ratio` = point_ratio, `Input tokens` = input_tokens, `Output tokens` = output_tokens) |>
  relocate(`Strategy`, `Point ratio`) |>
  csv("pretest_1_mutants")


pt_conversation_stats |>
  filter(experiment_name == "narrow_further_down", startsWith(strategy, "MUTANT")) |>
  arrange(-point_ratio) |>
  select(strategy, is_success, point_ratio, input_tokens, output_tokens) |>
  mutate(
    strategy = strategy %>% gsub("MUTANT", "", .) %>% gsub("_", " ", .) %>% tolower() %>% sapply(.simpleCap),
    point_ratio = round(point_ratio, digits = 3),
    is_success = percent(is_success, accuracy = 0.01),
    input_tokens = round(input_tokens / 210),
    output_tokens = round(output_tokens / 210)
  ) |>
  rename(Strategy = strategy, `Success rate` = is_success, `Point ratio` = point_ratio, `Input tokens` = input_tokens, `Output tokens` = output_tokens) |>
  relocate(`Strategy`, `Point ratio`) |>
  csv("pretest_2_mutants")

pt_conversation_stats |>
  filter(experiment_name == "Pretest", startsWith(strategy, "TEST")) |>
  arrange(-point_ratio) |>
  select(strategy, is_success, point_ratio, input_tokens, output_tokens) |>
  mutate(
    strategy = strategy %>% gsub("TEST", "", .) %>% gsub("_", " ", .) %>% tolower() %>% sapply(.simpleCap),
    point_ratio = round(point_ratio, digits = 3),
    is_success = percent(is_success, accuracy = 0.01),
    input_tokens = round(input_tokens / 30),
    output_tokens = round(output_tokens / 30)
  ) |>
  rename(Strategy = strategy, `Success rate` = is_success, `Point ratio` = point_ratio, `Input tokens` = input_tokens, `Output tokens` = output_tokens) |>
  relocate(`Strategy`, `Point ratio`) |>
  csv("pretest_1_tests")


pt_conversation_stats |>
  filter(experiment_name == "narrow_further_down", startsWith(strategy, "TEST")) |>
  arrange(-point_ratio) |>
  select(strategy, is_success, point_ratio, input_tokens, output_tokens) |>
  mutate(
    strategy = strategy %>% gsub("TEST", "", .) %>% gsub("_", " ", .) %>% tolower() %>% sapply(.simpleCap),
    point_ratio = round(point_ratio, digits = 3),
    is_success = percent(is_success, accuracy = 0.01),
    input_tokens = round(input_tokens / 42),
    output_tokens = round(output_tokens / 42)
  ) |>
  rename(Strategy = strategy, `Success rate` = is_success, `Point ratio` = point_ratio, `Input tokens` = input_tokens, `Output tokens` = output_tokens) |>
  relocate(`Strategy`, `Point ratio`) |>
  csv("pretest_2_tests")
