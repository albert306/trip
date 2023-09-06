package de.awolf.trip_compose.presentation.home_screen

import de.awolf.trip_compose.domain.models.Stop
import java.time.LocalDateTime

data class HomeScreenState(
    val searchBarText: String = "",
    val selectedTime: LocalDateTime = LocalDateTime.now(),
    val recommendedStops: List<Stop> = emptyList(),
)