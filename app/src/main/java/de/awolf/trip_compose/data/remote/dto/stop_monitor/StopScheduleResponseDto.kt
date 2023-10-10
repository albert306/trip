package de.awolf.trip_compose.data.remote.dto.stop_monitor

import de.awolf.trip_compose.data.remote.dto.ResponseStatus
import de.awolf.trip_compose.data.remote.util.LocalDateTimeSerde
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class StopScheduleResponseDto(
    @SerialName("Status") val responseStatus: ResponseStatus,
    @Serializable(with = LocalDateTimeSerde::class)
    @SerialName("ExpirationTime") val expirationTime: LocalDateTime? = null,
    @SerialName("Stops") val scheduledStops: List<StopScheduleItemDto>
)