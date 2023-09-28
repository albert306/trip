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
                checkResponseStatus(responseData = response.data!!)
            }
        }
    }

    private fun checkResponseStatus(responseData: StopMonitorInfo): Resource<StopMonitorInfo> {
        if (!responseData.responseStatus.isOk()) {
            return Resource.Error(
                message = "API response bad status\ncode: " +  responseData.responseStatus.code + "\nmessage: " + responseData.responseStatus.message
            )
        }

        return Resource.Success(responseData)
    }
}