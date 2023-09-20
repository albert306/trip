package de.awolf.trip_compose.data.remote.dto.stop_monitor

import de.awolf.trip_compose.data.remote.util.LocalDateTimeSerde
import de.awolf.trip_compose.data.remote.dto.ResponseStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class StopMonitorResponseDto(
    @SerialName("Status") val responseStatus: ResponseStatus,
    @SerialName("Name") val name: String,
    @SerialName("Place") val region: String,
    @Serializable(with = LocalDateTimeSerde::class)
    @SerialName("ExpirationTime") val expirationTime: LocalDateTime? = null,
    @SerialName("Departures") val departures: List<DepartureDto> = emptyList(),
)