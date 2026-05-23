killmap_data <- function(tests, mutants) {
  killmap_tests <- tests |>
    select(id, seconds_since_gamestart, game_id) |>
    rename(test_id = id, test_seconds = seconds_since_gamestart, test_game_id = game_id)
  
  killmap_mutants <- mutants |>
    select(id, seconds_since_gamestart, game_id) |>
    rename(mutant_id = id, mutant_seconds = seconds_since_gamestart, mutant_game_id = game_id)
  
  canonical_killmaps <- read.csv("rawdata/killmaps/killmaps.csv", col.names = c("test_id", "mutant_id", "state")) |>
    mutate(state = factor(state))
  test_mapping <- read.csv("rawdata/killmaps/dedup_test_mapping.csv", col.names = c("dup_test_id", "canonical_id"))
  mutant_mapping <- read.csv("rawdata/killmaps/dedup_mutant_mapping.csv", col.names = c("dup_mutant_id", "canonical_id"))
  
  full_map <- test_mapping |>
    left_join(canonical_killmaps, join_by(canonical_id == test_id), relationship = "many-to-many") |>
    right_join(mutant_mapping, join_by(mutant_id == canonical_id), relationship = "many-to-many") |>
    select(dup_test_id, state, dup_mutant_id) |>
    rename(test_id = dup_test_id, mutant_id = dup_mutant_id) |>
    right_join(killmap_tests, join_by(test_id)) |>
    right_join(killmap_mutants, join_by(mutant_id))
  
  game_map <- full_map |>
    filter(test_game_id == mutant_game_id) |>
    rename(game_id = test_game_id) |>
    select(!mutant_game_id)
  
  fresh_kill_map <- game_map |>
    filter(mutant_seconds < test_seconds) |>
    filter(state == "KILLED") |>
    group_by(mutant_id) |>
    summarise(test_id = min(test_id)) |>
    group_by(test_id) |>
    summarise(fresh_killed_mutants = n())
  
  tests |>
    left_join(fresh_kill_map, join_by(id == test_id)) |>
    mutate(fresh_killed_mutants = ifelse(is.na(fresh_killed_mutants), 0, fresh_killed_mutants))
}
