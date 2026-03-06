package aleksei.bakycharov.sporttracker.android.ui.screens.workout

import aleksei.bakycharov.sporttracker.android.ui.screens.workout.components.Intensity
import aleksei.bakycharov.sporttracker.android.ui.screens.workout.components.IntensityFilter
import aleksei.bakycharov.sporttracker.android.ui.screens.workout.components.RecentWorkouts
import aleksei.bakycharov.sporttracker.android.ui.screens.workout.components.WorkoutHeader
import aleksei.bakycharov.sporttracker.android.ui.screens.workout.components.WorkoutItem
import aleksei.bakycharov.sporttracker.android.ui.theme.Background
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WorkoutScreen(animationKey: String = "") {
    // Mock
    val workouts = listOf(
        WorkoutItem("Weightlifting", "вт, 24 февр.", 88, 528, Intensity.MEDIUM),
        WorkoutItem("Cycling", "вт, 24 февр.", 40, 320, Intensity.MEDIUM),
        WorkoutItem("Swimming", "чт, 19 февр.", 21, 231, Intensity.HIGH),
        WorkoutItem("Running", "сб, 14 февр.", 63, 693, Intensity.MEDIUM),
        WorkoutItem("Swimming", "сб, 14 февр.", 88, 968, Intensity.HIGH),
        WorkoutItem("HIIT", "пт, 13 февр.", 25, 300, Intensity.HIGH),
        WorkoutItem("Swimming", "пт, 13 февр.", 70, 770, Intensity.HIGH),
        WorkoutItem("Walking", "чт, 12 февр.", 25, 100, Intensity.LOW)
    )

    var selectedIntensity by remember { mutableStateOf(Intensity.ALL) }

    val filteredWorkouts = if (selectedIntensity == Intensity.ALL) {
        workouts
    } else {
        workouts.filter { it.intensity == selectedIntensity }
    }

    Scaffold(
        topBar = { WorkoutHeader() }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .background(Background)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            IntensityFilter(
                selected = selectedIntensity,
                onSelect = { selectedIntensity = it }
            )
            RecentWorkouts(
                workouts = filteredWorkouts,
                animationKey = animationKey + selectedIntensity.name
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}