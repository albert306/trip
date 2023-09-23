package de.awolf.trip_compose.presentation.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import de.awolf.trip_compose.presentation.home_screen.components.SearchCard
import de.awolf.trip_compose.presentation.home_screen.components.StopView

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
) {

    val searchText by viewModel.searchText.collectAsState()
//    val isSearching by viewModel.isSearching.collectAsState() not currently in use
    val recommendedStops by viewModel.recommendedStops.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy((-10).dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchCard(
            searchText = searchText,
            onSearchTextChange = viewModel::onSearchTextChange,
            onSearchButtonClick = { viewModel.startStopMonitor(recommendedStops.firstOrNull()) },
            modifier = Modifier
                .zIndex(2f)
                .fillMaxWidth()
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .zIndex(1f)
                .fillMaxSize(),
        ) {
            item {
                Spacer(modifier = Modifier.height(6.dp))
            }
            items(recommendedStops) { stop ->
                StopView(
                    stop = stop,
                    onFavouriteStarClick = { viewModel.toggleFavouriteStop(stop) },
                    onNameClick = { viewModel.startStopMonitor(stop) }
                )
            }
        }
    }
}