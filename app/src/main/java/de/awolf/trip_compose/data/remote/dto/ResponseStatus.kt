package de.awolf.trip_compose.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseStatus(
    @SerialName("Code") val code: String,
    @SerialName("Message") val message: String = "",
) {
    fun isOk(): Boolean {
        return code == "Ok"
    }
}