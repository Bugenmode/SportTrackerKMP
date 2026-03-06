package aleksei.bakycharov.sporttracker.android.ui.screens.workout.components

import aleksei.bakycharov.sporttracker.android.ui.theme.BlueLightBg
import aleksei.bakycharov.sporttracker.android.ui.theme.CardBackground
import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import aleksei.bakycharov.sporttracker.android.ui.theme.TextPrimary
import aleksei.bakycharov.sporttracker.android.ui.theme.TextSecondary
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecentWorkouts(
    modifier: Modifier = Modifier,
    workouts: List<WorkoutItem>,
    animationKey: String = "",
) {
    var animationPlayed by remember(animationKey) { mutableStateOf(false) }

    LaunchedEffect(animationKey) {
        animationPlayed = true
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = BlueLightBg,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .size(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Filled.TrendingUp,
                        contentDescription = null,
                        tint = TextSecondary
                    )
                }
                Text(
                    "Недавние",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            workouts.forEachIndexed { index, workout ->
                WorkoutCard(
                    workout = workout,
                    animationPlayed = animationPlayed,
                    delay = index * 100
                )
                if (index < workouts.lastIndex) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


@Preview
@Composable
fun RecentWorkoutsPreview() {
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

    FitnessTrackerTheme {
        RecentWorkouts(workouts = workouts)
    }
}