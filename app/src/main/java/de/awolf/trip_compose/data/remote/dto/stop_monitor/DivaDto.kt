package de.awolf.trip_compose.data.remote.dto.stop_monitor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DivaDto(
    @SerialName("Number") val number: String,
    @SerialName("Network") val network: String
)
