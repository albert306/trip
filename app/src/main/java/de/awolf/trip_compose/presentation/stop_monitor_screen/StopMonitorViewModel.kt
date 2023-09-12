package de.awolf.trip_compose.presentation.stop_monitor_screen

import androidx.lifecycle.ViewModel
import de.awolf.trip_compose.domain.models.Departure
import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.domain.use_case.VvoServiceUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Suppress("UNUSED")
class StopMonitorViewModel(
    val stop: Stop,
    private val vvoServiceUseCases: VvoServiceUseCases
) : ViewModel() {

    private val _isUpdating = MutableStateFlow(false)
    val isUpdating = _isUpdating.asStateFlow()

    private val _departures = MutableStateFlow(listOf<Departure>())
    val departures = _departures.asStateFlow()
}