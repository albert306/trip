package de.awolf.trip_compose.presentation.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import de.awolf.trip_compose.presentation.home_screen.components.SearchCard
import de.awolf.trip_compose.presentation.home_screen.components.StopView
import de.awolf.trip_compose.ui.Screen

//@Preview(showBackground = true)
//@Composable
//private fun Preview() {
//    AppTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            HomeScreen()
//        }
//    }
//}

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    navController: NavController
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
            onSearchButtonClick = viewModel::startSearch,
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
                Spacer(modifier = Modifier.height(10.dp))
            }
            items(recommendedStops) { stop ->
                StopView(
                    stop = stop,
                    stopIsFavourite = false,
                    onClick = { navController.navigate(Screen.StopMonitorScreen.withArgs(stop.id, stop.name, stop.region, "false")) }
                )
            }
        }
    }
}