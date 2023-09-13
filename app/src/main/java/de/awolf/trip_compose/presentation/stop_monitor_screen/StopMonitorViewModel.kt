package de.awolf.trip_compose.presentation.stop_monitor_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.awolf.trip_compose.domain.models.Departure
import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.domain.use_case.VvoServiceUseCases
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@Suppress("UNUSED")
class StopMonitorViewModel(
    val stop: Stop,
    private val queriedTime: Long,
    private val vvoServiceUseCases: VvoServiceUseCases
) : ViewModel() {

    private val _isStopInfoCardExpanded = MutableStateFlow(false)
    val isStopInfoCardExpanded = _isStopInfoCardExpanded.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _departures = MutableStateFlow(listOf<Departure>())
    val departures = _departures.asStateFlow()

    init {
        updateDepartures()
    }

    fun expandStopInfo() {
        _isStopInfoCardExpanded.value = !_isStopInfoCardExpanded.value
    }

    fun updateDepartures() {
        viewModelScope.launch {
            _isRefreshing.update { true }
            _departures.update { vvoServiceUseCases.getStopMonitorDeparturesUseCase(stop, LocalDateTime.now()) }
            delay(300)
            _isRefreshing.update { false }
        }
    }
}