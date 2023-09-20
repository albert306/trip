package de.awolf.trip_compose.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Platform(
    @SerialName("Type") val type: String,
    @SerialName("Name") val name: String
)