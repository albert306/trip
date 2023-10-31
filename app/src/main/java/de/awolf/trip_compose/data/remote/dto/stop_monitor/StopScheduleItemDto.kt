package de.awolf.trip_compose.data.remote.dto.stop_monitor

import de.awolf.trip_compose.data.remote.util.LocalDateTimeSerde
import de.awolf.trip_compose.domain.models.Platform
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class StopScheduleItemDto(
    @SerialName("Id") val stopId: String,
    @SerialName("DlId") val dlId: String = "",
    @SerialName("Place") val stopRegion: String,
    @SerialName("Name") val stopName: String,
    @SerialName("Position") val shedulePosition: String,
    @SerialName("Platform") val platform: Platform? = null,
    @Serializable(with = LocalDateTimeSerde::class)
    @SerialName("Time") val arrivalTime: LocalDateTime

)