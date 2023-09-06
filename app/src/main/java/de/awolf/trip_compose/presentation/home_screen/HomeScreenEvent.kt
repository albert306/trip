package de.awolf.trip_compose.presentation.home_screen

sealed class HomeScreenEvent {
    data class ChangeSearchBarText(val newText: String): HomeScreenEvent()
    object StartSearch : HomeScreenEvent()
}
