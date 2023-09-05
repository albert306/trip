package de.awolf.trip_compose.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatusDto(
    @SerialName("Code") val code: String,
    @SerialName("Message") val message: String? = null,
)
