package de.awolf.trip_compose.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.domain.use_case.VvoServiceUseCases
import de.awolf.trip_compose.ui.Screen
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime
import java.time.ZonedDateTime

@Suppress("UNUSED")
class HomeScreenViewModel(
    private val vvoServiceUseCases: VvoServiceUseCases,
    private val navController: NavController
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _selectedTime = MutableStateFlow(LocalDateTime.now())
    val selectedTime = _selectedTime.asStateFlow()

    private val _recommendedStops = MutableStateFlow(listOf<Stop>())
    @OptIn(FlowPreview::class)
    val recommendedStops = _searchText
        .debounce(100L)
        .onEach { _isSearching.update { true } }
        .combine(_recommendedStops) { text, _ ->
            vvoServiceUseCases.getRecommendedStopsUseCase(text)
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _recommendedStops.value
        )


    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun startStopMonitor(stop: Stop?) {
        if (stop == null) { return } //TODO(give user feedback that no such stop was found)

        navController.navigate(Screen.StopMonitorScreen.withArgs(
            stop.id,
            stop.name,
            stop.region,
            "false",
            ZonedDateTime.now().toEpochSecond().toString()
        ))
    }
}