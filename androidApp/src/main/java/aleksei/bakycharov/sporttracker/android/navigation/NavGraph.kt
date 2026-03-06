package aleksei.bakycharov.sporttracker.android.navigation

import aleksei.bakycharov.sporttracker.android.ui.screens.goals.GoalsScreen
import aleksei.bakycharov.sporttracker.android.ui.screens.home.HomeScreen
import aleksei.bakycharov.sporttracker.android.ui.screens.sleep.SleepScreen
import aleksei.bakycharov.sporttracker.android.ui.screens.workout.WorkoutScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home
    ) {
        composable<Screen.Home> { backStackEntry ->
            HomeScreen(animationKey = backStackEntry.id)
        }
        composable<Screen.Workout> {
            WorkoutScreen()
        }
        composable<Screen.Sleep> {
            SleepScreen()
        }
        composable<Screen.Goals> {
            GoalsScreen()
        }
    }
}