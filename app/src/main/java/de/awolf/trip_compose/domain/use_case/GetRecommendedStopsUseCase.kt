package de.awolf.trip_compose.domain.use_case

import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.domain.repository.VvoService
import de.awolf.trip_compose.domain.util.Resource

class GetRecommendedStopsUseCase(
    private val vvoService: VvoService
) {

    suspend operator fun invoke(query: String): Resource<List<Stop>> {

        return if (query.length < 3) {
            Resource.Success(listOf(Stop("33000028", "Hauptbahnhof", "Dresden"))) //TODO(get favouriteStops from sharedPreferences)
        } else {
            when (val response = vvoService.getStopByName(
                query = query, // add optional query parameters
            )) {
                is Resource.Error -> {
                    Resource.Error(
                        data = listOf(Stop("33000028", "Hauptbahnhof", "Dresden")), //TODO(get favouriteStops from sharedPreferences)
                        message = response.message!!
                    )
                }
                is Resource.Success -> {
                    Resource.Success(response.data!!.stops)
                }
            }
        }
    }
}