package de.awolf.trip_compose.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Diva(
    @SerialName("Number") val number: String,
    @SerialName("Network") val network: String
)