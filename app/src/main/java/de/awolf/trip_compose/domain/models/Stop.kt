package de.awolf.trip_compose.domain.models

data class Stop(
    val id: String,
    val name: String,
    val region: String = "Dresden",
)