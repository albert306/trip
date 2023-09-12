package de.awolf.trip_compose.ui

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object StopMonitorScreen : Screen("stop_monitor_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
