package de.awolf.trip_compose.data.remote.dto.stop_monitor

import de.awolf.trip_compose.data.remote.util.LocalDateTimeSerde
import de.awolf.trip_compose.domain.models.Diva
import de.awolf.trip_compose.domain.models.Platform
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class DepartureDto(
    @SerialName("Id") val id: String,
    @SerialName("LineName") val lineNumber: String,
    @SerialName("Direction") val lineDirection: String,
    @SerialName("Platform") val platform: Platform? = null,
    @SerialName("Mot") val mode: String = "Unknown",
    @Serializable(with = LocalDateTimeSerde::class)
    @SerialName("ScheduledTime") val sheduledTime: LocalDateTime,
    @Serializable(with = LocalDateTimeSerde::class)
    @SerialName("RealTime") val realTime: LocalDateTime = sheduledTime,
    @SerialName("State") val state: String = "InTime",
    @SerialName("RouteChanges") val routeChanges: List<String> = emptyList(),
    @SerialName("Diva") val diva: Diva? = null,
)
