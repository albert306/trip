package de.awolf.trip_compose.domain.use_case

data class UseCases(
    val getRecommendedStopsUseCase: GetRecommendedStopsUseCase,
    val getStopMonitorUseCase: GetStopMonitorUseCase,
    val getDetailedStopSchedule: GetDetailedStopSchedule,
    val toggleAndReturnFavouriteStopStatusUseCase: ToggleAndReturnFavouriteStopStatusUseCase
    // add more usecases like get upcoming departures
)
