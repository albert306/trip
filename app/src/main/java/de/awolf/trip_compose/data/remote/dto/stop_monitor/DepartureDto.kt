package de.awolf.trip_compose.data.remote.dto.stop_monitor

import de.awolf.trip_compose.data.remote.util.LocalDateTimeSerde
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class DepartureDto(
    @SerialName("Id") val id: String,
    @SerialName("LineName") val lineNumber: String,
    @SerialName("Direction") val lineDirection: String,
    @SerialName("Platform") val platform: PlatformDto,
    @SerialName("Mot") val mode: String,
    @Serializable(with = LocalDateTimeSerde::class)
    @SerialName("ScheduledTime") val sheduledTime: LocalDateTime,
    @Serializable(with = LocalDateTimeSerde::class)
    @SerialName("RealTime") val realTime: LocalDateTime = sheduledTime,
    @SerialName("State") val state: String = "InTime",
    @SerialName("RouteChanges") val routeChanges: List<String>,
    @SerialName("Diva") val diva: DivaDto,
)
