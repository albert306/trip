package de.awolf.trip_compose.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import de.awolf.trip_compose.MyApp
import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.presentation.helper.viewModelFactory
import de.awolf.trip_compose.presentation.home_screen.HomeScreen
import de.awolf.trip_compose.presentation.home_screen.HomeScreenViewModel
import de.awolf.trip_compose.presentation.stop_monitor_screen.StopMonitorScreen
import de.awolf.trip_compose.presentation.stop_monitor_screen.StopMonitorViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
    ) {
        composable(
            route = Screen.HomeScreen.route
        ) {
            val homeScreenViewModel = viewModel<HomeScreenViewModel>(
                factory = viewModelFactory {
                    HomeScreenViewModel(
                        useCases = MyApp.appModule.useCases,
                        navController = navController
                    )
                }
            )

            HomeScreen(
                viewModel = homeScreenViewModel
            )
        }

        composable(
            route = Screen.StopMonitorScreen.route + "/{stopId}/{stopName}/{stopRegion}/{stopIsFavourite}/{queriedTime}",
            arguments = listOf(
                navArgument("stopId") {
                    type = NavType.StringType
                },
                navArgument("stopName") {
                    type = NavType.StringType
                },
                navArgument("stopRegion") {
                    type = NavType.StringType
                },
                navArgument("stopIsFavourite") {
                    type = NavType.BoolType
                },
                navArgument("queriedTime") {
                    type = NavType.LongType
                }
            )
        ) { entry ->
            val stopMonitorViewModel = viewModel<StopMonitorViewModel>(
                factory = viewModelFactory {
                    StopMonitorViewModel(
                        stop = Stop(
                            id = entry.arguments?.getString("stopId") ?: "0000000",
                            name = entry.arguments?.getString("stopName") ?: "unknown",
                            region = entry.arguments?.getString("stopRegion") ?: "Dresden",
                        ),
                        queriedTime = entry.arguments?.getLong("queriedTime") ?: 0L,
                        useCases = MyApp.appModule.useCases
                    )
                }
            )

            StopMonitorScreen(
                viewModel = stopMonitorViewModel
            )
        }
    }
}