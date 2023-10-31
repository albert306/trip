package de.awolf.trip_compose.domain.models

import java.time.Clock
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId

data class Departure(
    val id: String,
    val dlId: String?,
    val lineNumber: String,
    val lineDirection: String,
    val platform: Platform?,
    val mode: Mode,
    val sheduledTime: LocalDateTime,
    val realTime: LocalDateTime,
    val departureState: DepartureState,
    val routeChanges: List<String>,
    val diva: Diva?,
) : Comparable<Departure> {

    enum class DepartureState(val rawValue: String = "Unknown") {
        INTIME("InTime"),
        DELAYED("Delayed"),
        UNKNOWN("Unknown");

        companion object {
            fun fromString(value: String): DepartureState {
                return DepartureState.values().find { it.rawValue == value } ?: UNKNOWN
            }
        }
    }

    fun getETA(): Long {
        val diff = Duration.between(
            LocalDateTime.now(Clock.tickMinutes(ZoneId.systemDefault())),
            realTime
        )
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