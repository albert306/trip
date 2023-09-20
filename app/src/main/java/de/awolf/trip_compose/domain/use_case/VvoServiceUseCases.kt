package de.awolf.trip_compose.domain.use_case

data class VvoServiceUseCases(
    val getRecommendedStopsUseCase: GetRecommendedStopsUseCase,
    val getStopMonitorUseCase: GetStopMonitorUseCase,
    // add more usecases like get upcoming departures
)
