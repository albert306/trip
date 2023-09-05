package de.awolf.trip_compose.data.remote.dto.stop_monitor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlatformDto(
    @SerialName("Type") val type: String,
    @SerialName("Name") val name: String
)