package de.awolf.trip_compose.data.remote.dto

import de.awolf.trip_compose.data.remote.util.LocalDateTimeSerde
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class DepartureMonitorDto(
    @SerialName("Name") val name: String,
    @SerialName("Status") val status: StatusDto,
    @SerialName("Place") val region: String,
    @Serializable(with = LocalDateTimeSerde::class)
    @SerialName("ExpirationTime") val expirationTime: LocalDateTime,
    @SerialName("Departures") val departures: List<DepartureInfoDto>,
)