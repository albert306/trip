package de.awolf.trip_compose.domain.models

import java.time.LocalDateTime

data class DeparturesWrapper(
    val name: String,
    val status: Status,
    val region: String,
    val expirationTime: LocalDateTime,
    val departures: List<Departure>,
)