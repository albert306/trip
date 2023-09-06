package de.awolf.trip_compose.presentation.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.awolf.trip_compose.domain.use_case.VvoServiceUseCases
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val vvoServiceUseCases: VvoServiceUseCases
) : ViewModel() {

    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    var getRecommendedStopsJob: Job? = null

    init {
        getRecommendedStopsJob = viewModelScope.launch {
            _state.value = state.value.copy(
                recommendedStops = vvoServiceUseCases.getRecommendedStopsUseCase("")
            )
        }
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.ChangeSearchBarText -> {
                _state.value = state.value.copy(
                    searchBarText = event.newText,
                )
                getRecommendedStopsJob?.cancel() //cancel previous api request upon rapid changes in search bar
                getRecommendedStopsJob = viewModelScope.launch {
                    _state.value = state.value.copy(
                        recommendedStops = vvoServiceUseCases.getRecommendedStopsUseCase(event.newText)
                    )
                }
            }
            is HomeScreenEvent.StartSearch -> {
                if (_state.value.recommendedStops.isEmpty()) { return }

                _state.value.recommendedStops[0] //TODO(launch activity with this stop)
            }
        }
    }
}