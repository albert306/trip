package de.awolf.trip_compose.domain.use_case

import de.awolf.trip_compose.data.remote.mappers.toDeparture
import de.awolf.trip_compose.data.remote.mappers.toStatus
import de.awolf.trip_compose.domain.models.Departure
import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.domain.repository.VvoService
import de.awolf.trip_compose.domain.util.Resource
import java.time.LocalDateTime

class GetStopMonitorDeparturesUseCase(
    private val vvoService: VvoService
) {

    suspend operator fun invoke(stop: Stop, time: LocalDateTime, limit: Int): List<Departure> {
        return when (val result = vvoService.monitorStop(
            stopId = stop.id,
            limit = limit,
            time = time, // add optional query parameters
        )) {
            is Resource.Success -> {
                val responseStatus = result.data!!.status.toStatus()
                if (responseStatus.code != "Ok") {
                    throw Exception("vvo api responded with code ${responseStatus.code} and message ${responseStatus.message}")
                }
                result.data.departures.map { it.toDeparture() }
            }
            is Resource.Error -> {
                throw Exception("vvo api call resulted in Resource.Error with message ${result.message}")
            }
        }
    }
}