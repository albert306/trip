package de.awolf.trip_compose.presentation.stop_monitor_screen

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.awolf.trip_compose.domain.models.Departure
import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.domain.use_case.VvoServiceUseCases
import de.awolf.trip_compose.domain.util.Resource
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

    private val departureCount = mutableIntStateOf(30)

    init {
        updateDepartures()
    }

    fun expandStopInfo() {
        _isStopInfoCardExpanded.value = !_isStopInfoCardExpanded.value
    }

    fun updateDepartures() {
        viewModelScope.launch {
            _isRefreshing.update { true }
            _departures.update {
                val stopMonitorInfoResource = vvoServiceUseCases.getStopMonitorUseCase(
                    stop = stop,
                    time = LocalDateTime.now(),
                    limit = departureCount.intValue,
                )
                when (stopMonitorInfoResource) {
                    is Resource.Error -> {
                        println("TOAST: " + stopMonitorInfoResource.message)
                        emptyList()
                    }
                    is Resource.Success -> {
                        stopMonitorInfoResource.data!!.departures
                    }
                }
            }
            delay(300)
            _isRefreshing.update { false }
        }
    }

    fun increaseDepartureCount() {
        departureCount.intValue = departureCount.intValue + 20
        viewModelScope.launch {
            _departures.update {
                val stopMonitorInfoResource = vvoServiceUseCases.getStopMonitorUseCase(
                    stop = stop,
                    time = LocalDateTime.now(),
                    limit = departureCount.intValue,
                )
                when (stopMonitorInfoResource) {
                    is Resource.Error -> {
                        println("TOAST: " + stopMonitorInfoResource.message)
                        emptyList()
                    }
                    is Resource.Success -> {
                        stopMonitorInfoResource.data!!.departures
                    }
                }
            }
        }
    }
}