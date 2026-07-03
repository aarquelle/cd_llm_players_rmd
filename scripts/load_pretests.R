library(tidyverse)

readpt <- function(filename) {
  read.csv(paste("rawdata/pretests/db_tables/", filename, ".csv", sep = ""), skipNul = TRUE) |>
    rename_with(tolower)
}

pt_experiment <- readpt("experiment") |>
  filter(experiment_name %in% c("Pretest", "narrow_further_down")) |>
  separate_wider_delim(experiment_description, "|", names = c("cut", "attack_strat", "defend_strat")) |>
  select(attack_strat, experiment_name, defend_strat, int_value, cut)

pt_classes <- readpt("classes") |>
  select(class_id, name)

  
  
pt_games <- readpt("games") |>
  rename(game_id = id, game_start = start_time) |>
  select(game_id, game_start, class_id) |>
  right_join(pt_experiment, join_by(game_id == int_value)) |>
  left_join(pt_classes, join_by(class_id, cut == name))

pt_players <- readpt("players") |>
  filter(user_id %in% 5:6, game_id %in% pt_games$game_id) |>
  select(id, role)

pt_mutants <- readpt("mutants") |>
  select(mutant_id, points, equivalent, game_id, mutatedlines, timestamp) |>
  filter(game_id %in% pt_games$game_id) |>
  left_join(pt_games, join_by(game_id))

pt_tests <- readpt("tests") |>
  left_join(pt_players, join_by(player_id == id)) |>
  select(test_id, points, game_id, lines_covered, timestamp, role) |>
  filter(game_id %in% pt_games$game_id) |>
  left_join(pt_games, join_by(game_id)) |>
  filter(role == "DEFENDER")

pt_games <- pt_games |>
  left_join(
    pt_tests |> summarise(defpoints = sum(points), number_of_tests = n(), .by = game_id),
    join_by(game_id)
  ) |>
  left_join(
    pt_mutants|> summarise(attpoints = sum(points), number_of_mutants = n(), .by = game_id),
    join_by(game_id)
  ) |>
  mutate(point_ratio = defpoints / attpoints)

pt_conversations <- readpt("llm_conversations") |>
  filter(game_id %in% pt_games$game_id) |>
  left_join(pt_experiment, join_by(game_id == int_value))

pt_messages <-readpt("llm_messages") |>
  filter(conversation_id %in% pt_conversations$conversation_id)

pt_conversations <- pt_messages |>
  summarise(.by = conversation_id, across(c(input_tokens, output_tokens), sum), number_of_messages = n()) |>
  inner_join(pt_conversations, join_by(conversation_id))


pt_conversation_stats <- pt_conversations |>
  summarise(.by = c(experiment_name, strategy), is_success = mean(is_success), across(c(input_tokens, output_tokens), sum)) |>
  left_join(
    pt_mutants |>
      summarise(.by = c(attack_strat, experiment_name), points = sum(points)), join_by(strategy == attack_strat, experiment_name)
  ) |>
  left_join(
    pt_tests |>
      summarise(.by = c(attack_strat, experiment_name), opponent_points = sum(points)), join_by(strategy == attack_strat, experiment_name)
  ) |>
  left_join(
    pt_tests |>
      summarise(.by = c(defend_strat, experiment_name), points = sum(points)), join_by(strategy == defend_strat, experiment_name)
  ) |>
  left_join(
    pt_mutants |>
      summarise(.by = c(defend_strat, experiment_name), opponent_points = sum(points)), join_by(strategy == defend_strat, experiment_name)
  ) |>
  mutate(points = coalesce(points.x, points.y), opponent_points = coalesce(opponent_points.x, opponent_points.y)) |>
  select(!c(points.x, points.y, opponent_points.x, opponent_points.y)) |>
  mutate(
    point_ratio = points / opponent_points,
  )
  