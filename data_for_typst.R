#install.packages("svglite")

library(jsonlite)
library(ggplot2)
library(svglite)

rm(list = ls())

theme_set(theme_bw(base_size = 20, base_family = "Libertinus Serif"))
update_geom_defaults("text",  list(size = 7, family = "Libertinus Serif"))

source("scripts/load_data.R")

pr <- function(plot, filename, width = 8.5, height = 5, ...) {
  ggsave(
    paste("../typst_ba/images/", filename, ".svg", sep = ""), 
    plot = plot, 
    width = width,
    height = height,
    ...
  )
}

list(
  
  #Demographics
  number_participants = nrow(questionnaire),
  demo_gender_female = questionnaire |> filter(gender == "female") |> nrow(),
  demo_gender_male = questionnaire |> filter(gender == "male") |> nrow(),
  demo_gender_nb = questionnaire |> filter(gender == "nonbinary") |> nrow(),
  
  demo_degree_ai = questionnaire |> filter(degree == "Artificial Intelligence") |> nrow(),
  demo_degree_cs = questionnaire |> filter(degree == "Bachelor Informatik") |> nrow(),
  demo_degree_ic = questionnaire |> filter(degree == "Bachelor Internet Computing") |> nrow(),
  demo_degree_tp = questionnaire |> filter(degree == "Lehramt Informatik") |> nrow(),
  
  demo_ages = questionnaire$age,
  demo_semesters = questionnaire$semester,
  
  number_llmvsllm_games = mutants |> filter(grepl("l", mutant_id)) |> select(game_id) |> unique() |> nrow(),
  number_llmvsllm_bytevector_games = mutants |> filter(grepl("l", mutant_id)) |> filter(cut == "ByteVector") |> select(game_id) |> unique() |> nrow(),
  number_llmvsllm_charrange_games = mutants |> filter(grepl("l", mutant_id)) |> filter(cut == "CharRange") |> select(game_id) |> unique() |> nrow(),
  
  loc = n_loc,
  
  
  dummy = 1
) |>
  write_json("../typst_ba/data/r_data.json")

(questionnaire |>
  pivot_longer(
    cols = c(
      java_experience, 
      junit_experience
    ),
    names_to = "xp_type",
    values_to = "xp_value"
  ) |>
  mutate(xp_type = recode_values(
    xp_type,
    "java_experience" ~ "Java experience",
    "junit_experience" ~ "JUnit experience"
  )) |>
  summarise(n = n(), .by = c(xp_type, xp_value)) |>
  
  ggplot(aes(x = xp_type, y = n, fill = xp_value)) +
  geom_col(position = "stack") +
  geom_text(aes(label = after_stat(y)), position = position_stack(vjust = 0.5)) +
  labs(fill = "Experience", x = NULL, y = "Count", 
       title = "Experience of study participants")) |>
  pr("xp")

(questionnaire |>
    mutate(number_correct_questions = ordered(number_correct_questions)) |>
    summarise(n = n(), .by = c(number_correct_questions)) |>
    ggplot(aes(x = "", y = n, fill = number_correct_questions)) +
    geom_col() +
    geom_text(aes(label = n), position = position_stack(vjust = 0.5)) +
    labs(x = NULL, y = "Count", fill = "Number of correctly answered questions",
         title = "Quiz results")) |>
  pr("quiz")
  
  
  (deftests |>
    separate_longer_delim(lines_covered, ",") |>
    summarise(.by = c(lines_covered, game_id, cut, author_llm_or_human, opponent_llm_or_human)) |>
    left_join(n_loc, join_by(cut)) |>
    summarise(lines_covered = n(), .by = c(game_id, cut, author_llm_or_human, opponent_llm_or_human, loc_for_cut)) |>
    mutate(line_coverage = lines_covered / loc_for_cut) |>
    ggplot(aes(y = line_coverage, x = author_llm_or_human, fill = opponent_llm_or_human)) +
    scale_default_grouping("t") + 
    geom_boxplot() +
    labs(title = "Line coverage", y = "Line coverage") +
    scale_y_continuous(labels = percent)
) |> pr("line_coverage")
