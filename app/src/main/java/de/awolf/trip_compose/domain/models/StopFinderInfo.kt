package de.awolf.trip_compose.domain.models

import de.awolf.trip_compose.data.remote.dto.ResponseStatus
import java.time.LocalDateTime

data class StopFinderInfo(
    val responseStatus: ResponseStatus,
    val pointStatus: String,
    val stops: List<Stop>,
    val expirationTime: LocalDateTime?,
)
