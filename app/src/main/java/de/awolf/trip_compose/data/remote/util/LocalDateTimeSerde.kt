package de.awolf.trip_compose.data.remote.util

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = LocalDateTime::class)
object LocalDateTimeSerde : KSerializer<LocalDateTime> {

    override fun deserialize(decoder: Decoder): LocalDateTime {
        val dateString = decoder.decodeString().removeSurrounding("\\/") // of form: "Date(1487778356000-0000)"

        val regex = Regex("[(](.*)([+]|-)") //regex to capture everything between "(" and "+" or "-"
        val match = regex.find(dateString)
        val timestamp = match?.groups?.get(1)?.value?.toLong() ?: "0000000000000".toLong()

        return LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timestamp),
            ZoneId.systemDefault()
        )
    }

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
        encoder.encodeString(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Europe/Berlin")).format(dateFormat))
    }
}