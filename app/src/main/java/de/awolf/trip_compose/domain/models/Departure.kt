package de.awolf.trip_compose.domain.models

import java.time.Duration
import java.time.LocalDateTime

@Suppress("UNUSED")
class Departure(
    val id: String,
    val lineNumber: String,
    val lineDirection: String,
    val platform: Platform,
    val mode: Mode,
    val sheduledTime: LocalDateTime,
    val realTime: LocalDateTime,
    val state: State,
    val routeChanges: List<String>,
    val diva: Diva,
) : Comparable<Departure> {

    enum class State(val rawValue: String = "Unknown") {
        ONTIME("InTime"),
        DELAYED("Delayed"),
        UNKNOWN("Unknown");

        companion object {
            fun fromString(value: String): State {
                return State.values().find { it.rawValue == value } ?: UNKNOWN
            }
        }
    }

    fun getETA(): Long {
        val diff = Duration.between(LocalDateTime.now(), realTime)
        return diff.toMinutes()
    }

    fun getDelay(): Long {
        val diff = Duration.between(sheduledTime, realTime)
        return diff.toMinutes()
    }

    override fun compareTo(other: Departure): Int {
        return realTime.compareTo(other.realTime)
    }
}