package de.awolf.trip_compose.domain.use_case

import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.domain.models.StopFinderInfo
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
                    checkResponseStatus(response.data!!)
                }
            }
        }
    }

    private suspend fun checkResponseStatus(responseData: StopFinderInfo): Resource<List<Stop>> {
        if (responseData.pointStatus == "NotIdentified") {
            return Resource.Error(
                data = emptyList(),
                message = "Stop could not be identified"
            )
        }

        if (!responseData.responseStatus.isOk()) {
            return Resource.Error(
                data = getFavouriteStops(),
                message = "API response bad status\ncode: " +  responseData.responseStatus.code + "\nmessage: " + responseData.responseStatus.message
            )
        }

        return Resource.Success(responseData.stops)
    }

    private suspend fun getFavouriteStops(): List<Stop> {
        return stopDatabaseRepository.getFavouriteStops()
    }
}