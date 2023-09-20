package de.awolf.trip_compose.domain.models

import de.awolf.trip_compose.data.remote.dto.ResponseStatus
import java.time.LocalDateTime

data class StopMonitorInfo(
    val responseStatus: ResponseStatus,
    val name: String,
    val region: String,
    val expirationTime: LocalDateTime?,
    val departures: List<Departure>,
)