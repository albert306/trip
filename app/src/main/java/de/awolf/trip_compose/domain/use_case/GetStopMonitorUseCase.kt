package de.awolf.trip_compose.domain.use_case

import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.domain.models.StopMonitorInfo
import de.awolf.trip_compose.domain.repository.VvoServiceRepository
import de.awolf.trip_compose.domain.util.Resource
import java.time.LocalDateTime

class GetStopMonitorUseCase(
    private val vvoServiceRepository: VvoServiceRepository
) {

    suspend operator fun invoke(stop: Stop, time: LocalDateTime, limit: Int): Resource<StopMonitorInfo> {
        return when (val response = vvoServiceRepository.monitorStop(
            stopId = stop.id,
            limit = limit,
            time = time, // add optional query parameters
        )) {
            is Resource.Error -> {
                Resource.Error(
                    message = "HTTP request failed with message: " +  response.message!!
                )
            }
            is Resource.Success -> {
                Resource.Success(response.data!!)
            }
        }
    }
}