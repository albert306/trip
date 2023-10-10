package de.awolf.trip_compose.domain.models

import java.time.LocalDateTime

data class StopScheduleItem(
    val stopId: String,
    val stopRegion: String,
    val stopName: String,
    val shedulePosition: String,
    val platform: Platform,
    val arrivalTime: LocalDateTime
)