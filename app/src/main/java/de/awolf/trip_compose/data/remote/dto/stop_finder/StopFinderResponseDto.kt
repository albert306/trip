package de.awolf.trip_compose.data.remote.dto.stop_finder

import de.awolf.trip_compose.data.remote.util.LocalDateTimeSerde
import de.awolf.trip_compose.data.remote.dto.ResponseStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class StopFinderResponseDto(
    @SerialName("Status") val responseStatus: ResponseStatus,
    @SerialName("PointStatus") val pointStatus: String,
    @SerialName("Points") val stops: List<String> = emptyList(),
    @Serializable(with = LocalDateTimeSerde::class)
    @SerialName("ExpirationTime") val expirationTime: LocalDateTime? = null,
)
