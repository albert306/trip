package de.awolf.trip_compose.domain.use_case

import de.awolf.trip_compose.data.remote.mappers.toListOfStops
import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.domain.repository.VvoService
import de.awolf.trip_compose.domain.util.Resource

class GetRecommendedStopsUseCase(
    private val vvoService: VvoService
) {

    suspend operator fun invoke(query: String): List<Stop> {

        return if (query.length < 3) {
            listOf(Stop("33000028", "Hauptbahnhof", "Dresden")) //TODO(get favouriteStops from sharedPreferences)
        } else {
            when (val result = vvoService.getStopByName(query)) {
                is Resource.Success -> {
                    result.data!!.toListOfStops()
                }
                is Resource.Error -> {
                    listOf(Stop("33000028", "Hauptbahnhof", "Dresden")) //TODO(get favouriteStops from sharedPreferences)
                }
            }
        }
    }
}