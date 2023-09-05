package de.awolf.trip_compose.data.remote.dto.stop_finder

import de.awolf.trip_compose.data.remote.dto.StatusDto
import de.awolf.trip_compose.data.remote.util.LocalDateTimeSerde
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class StopFinderResponseDto(
    @SerialName("PointStatus") val pointStatus: String,
    @SerialName("Status") val status: StatusDto,
    @SerialName("Points") val stops: List<String>,
    @Serializable(with = LocalDateTimeSerde::class)
    @SerialName("ExpirationTime") val expirationTime: LocalDateTime,
)
