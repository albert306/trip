package de.awolf.trip_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import de.awolf.trip_compose.presentation.home_screen.HomeScreen
import de.awolf.trip_compose.presentation.home_screen.HomeScreenViewModel
import de.awolf.trip_compose.presentation.viewModelFactory
import de.awolf.trip_compose.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {

                val homeScreenViewModel = viewModel<HomeScreenViewModel>(
                    factory = viewModelFactory {
                        HomeScreenViewModel(MyApp.appModule.vvoServiceUseCases)
                    }
                )

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(viewModel = homeScreenViewModel)
                }
            }
        }
    }
}