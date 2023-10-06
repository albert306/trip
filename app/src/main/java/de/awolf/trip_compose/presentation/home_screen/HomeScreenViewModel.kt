package de.awolf.trip_compose.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.domain.use_case.UseCases
import de.awolf.trip_compose.domain.util.Resource
import de.awolf.trip_compose.ui.Screen
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZonedDateTime

@OptIn(FlowPreview::class)
@Suppress("UNUSED")
class HomeScreenViewModel(
    private val useCases: UseCases,
    private val navController: NavController
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _selectedTime = MutableStateFlow(LocalDateTime.now())
    val selectedTime = _selectedTime.asStateFlow()

    private val _recommendedStops = MutableStateFlow(listOf<Stop>())
    val recommendedStops = _recommendedStops.asStateFlow()


    init {
        _searchText
            .debounce(100L)
            .onEach { _isSearching.update { true } }
            .combine(_recommendedStops, ) { text, _ ->
                setRecommendedStops(text)
            }
            .onEach { _isSearching.update { false } }
            .launchIn(
                viewModelScope
            )
    }


    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun startStopMonitor(stop: Stop?) {
        if (stop == null) { return } //TODO(give user feedback that no such stop was found)

        navController.navigate(Screen.StopMonitorScreen.withArgs(
            stop.id,
            stop.name,
            stop.region,
            stop.isFavourite.toString(),
            ZonedDateTime.now().toEpochSecond().toString()
        ))
    }

    fun toggleFavouriteStop(stop: Stop) {
        viewModelScope.launch {
            useCases.toggleAndReturnFavouriteStopStatusUseCase(stop)
            setRecommendedStops(searchText.value)
        }
    }

    private suspend fun setRecommendedStops(query: String) {
        when (val recommendedStopsResource = useCases.getRecommendedStopsUseCase(query)) {
            is Resource.Error -> {
                println("TOAST: " + recommendedStopsResource.message)
                _recommendedStops.update { emptyList() }
            }
            is Resource.Success -> {
                _recommendedStops.update { recommendedStopsResource.data!! }

            }
        }
    }
}