package de.awolf.trip_compose.domain.use_case

import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.domain.repository.StopDatabaseRepository
import de.awolf.trip_compose.domain.repository.VvoService
import de.awolf.trip_compose.domain.util.Resource

class GetRecommendedStopsUseCase(
    private val vvoService: VvoService,
    private val stopDatabaseRepository: StopDatabaseRepository
) {

    suspend operator fun invoke(query: String): Resource<List<Stop>> {

        return if (query.length < 3) {
            Resource.Success(getFavouriteStops())
        } else {
            when (val response = vvoService.getStopByName(
                query = query, // add optional query parameters
            )) {
                is Resource.Error -> {
                    Resource.Error(
                        data = getFavouriteStops(),
                        message = "HTTP request failed with message: " +  response.message!!
                    )
                }
                is Resource.Success -> {
                    Resource.Success(response.data!!.stops)
                }
            }
        }
    }
    private suspend fun getFavouriteStops(): List<Stop> {
        return stopDatabaseRepository.getFavouriteStops()
    }
}