package de.awolf.trip_compose.domain.use_case

import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.domain.repository.StopDatabaseRepository
import de.awolf.trip_compose.domain.repository.VvoServiceRepository
import de.awolf.trip_compose.domain.util.Resource

class GetRecommendedStopsUseCase(
    private val vvoServiceRepository: VvoServiceRepository,
    private val stopDatabaseRepository: StopDatabaseRepository
) {

    suspend operator fun invoke(query: String): Resource<List<Stop>> {

        return if (query.length < 3) {
            Resource.Success(getFavouriteStops())
        } else {
            when (val response = vvoServiceRepository.getStopByName(
                query = query, // add optional query parameters
            )) {
                is Resource.Error -> {
                    Resource.Error(
                        data = getFavouriteStops(),
                        message = response.message!!
                    )
                }
                is Resource.Success -> {
                    // merge two with favourite stop information to show favourites in api response stoplist
                    val stopListMarkedFavourites = response.data!!.stops.map { stop ->
                        stop.isFavourite = stopDatabaseRepository.isStopFavourite(stop.id)
                        stop
                    }

                    Resource.Success(stopListMarkedFavourites)
                }
            }
        }
    }
    private suspend fun getFavouriteStops(): List<Stop> {
        return stopDatabaseRepository.getFavouriteStops()
    }
}