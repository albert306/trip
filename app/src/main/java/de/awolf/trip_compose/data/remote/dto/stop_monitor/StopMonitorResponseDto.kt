package de.awolf.trip_compose.data.remote.dto.stop_monitor

import de.awolf.trip_compose.data.remote.dto.StatusDto
import de.awolf.trip_compose.data.remote.util.LocalDateTimeSerde
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class StopMonitorResponseDto(
    @SerialName("Name") val name: String,
    @SerialName("Status") val status: StatusDto,
    @SerialName("Place") val region: String,
    @Serializable(with = LocalDateTimeSerde::class)
    @SerialName("ExpirationTime") val expirationTime: LocalDateTime,
    @SerialName("Departures") val departures: List<DepartureDto>,
)