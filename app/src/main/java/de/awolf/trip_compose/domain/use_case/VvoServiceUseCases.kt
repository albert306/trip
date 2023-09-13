package de.awolf.trip_compose.domain.use_case

data class VvoServiceUseCases(
    val getRecommendedStopsUseCase: GetRecommendedStopsUseCase,
    val getStopMonitorDeparturesUseCase: GetStopMonitorDeparturesUseCase
    // add more usecases like get upcoming departures
)
