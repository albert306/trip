package de.awolf.trip_compose.domain.use_case

import de.awolf.trip_compose.domain.models.Departure
import de.awolf.trip_compose.domain.models.StopScheduleItem
import de.awolf.trip_compose.domain.repository.VvoServiceRepository
import de.awolf.trip_compose.domain.util.Resource

class GetDetailedStopSchedule(
    private val vvoServiceRepository: VvoServiceRepository
) {
    suspend operator fun invoke(
        departure: Departure,
        stopId: String
    ): Resource<List<StopScheduleItem>> {

        return when (val response = vvoServiceRepository.getDetailedStopSchedule(
            departureId = departure.id,
            time = departure.realTime,
            stopId = stopId
        )) {
            is Resource.Error -> {
                Resource.Error(
                    message = "HTTP request failed with message: " + response.message!!
                )
            }

            is Resource.Success -> {
                val delay = departure.getDelay()

                Resource.Success(response.data!!.map {
                    it.copy(
                        stopName = it.stopName.removePrefix("Dresden "),
                        arrivalTime = it.arrivalTime.plusMinutes(delay)
                    )
                })
            }
        }
    }
}