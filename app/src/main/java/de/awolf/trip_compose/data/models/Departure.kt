package de.awolf.trip_compose.data.models

import java.time.Duration
import java.time.LocalTime

@Suppress("UNUSED")
class Departure(
    val id: String,
    val lineNumber: String,
    val lineDirection: String,
    val platform: Platform? = null,
    val mode: Mode,
    val sheduledTime: LocalTime,
    val realTime: LocalTime = sheduledTime,
    val state: State? = null,
    val routeChanges: List<String>? = null,
    val diva: Diva? = null,
) : Comparable<Departure>{

    @Suppress("UNUSED_PARAMETER")
    enum class State(rawValue: String = "Unknown") {
        ONTIME("InTime"),
        DELAYED("Delayed"),
        UNKNOWN("Unknown"),
    }

    fun getETA(): Long {
        val diff = Duration.between(LocalTime.now(), realTime)
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