package aleksei.bakycharov.sporttracker.android.ui.screens.home.components

import aleksei.bakycharov.sporttracker.android.ui.components.StatCard
import aleksei.bakycharov.sporttracker.android.ui.theme.Blue
import aleksei.bakycharov.sporttracker.android.ui.theme.BlueLightBg
import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import aleksei.bakycharov.sporttracker.android.ui.theme.Green
import aleksei.bakycharov.sporttracker.android.ui.theme.Orange
import aleksei.bakycharov.sporttracker.android.ui.theme.Purple
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StatsGrid(
    modifier: Modifier = Modifier,
    animationKey: String = ""
) {
    var animationPlayed by remember(animationKey) { mutableStateOf(false) }

    LaunchedEffect(animationKey) {
        animationPlayed = true
    }

    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard(
                icon = Icons.Filled.DirectionsWalk,
                iconColor = Blue,
                iconBackground = BlueLightBg,
                title = "Шагов",
                value = "14 426",
                valueColor = Blue,
                animatedPlayed = animationPlayed,
                delay = 0,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                icon = Icons.Filled.LocalFireDepartment,
                iconColor = Orange,
                iconBackground = Color.White,
                title = "Калорий",
                value = "0",
                valueColor = Orange,
                animatedPlayed = animationPlayed,
                delay = 100,
                modifier = Modifier.weight(1f)
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard(
                icon = Icons.Filled.FitnessCenter,
                iconColor = Green,
                iconBackground = Color(0xFFE8F5E9),
                title = "Тренировок",
                value = "0",
                valueColor = Green,
                animatedPlayed = animationPlayed,
                delay = 200,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                icon = Icons.Filled.Nightlight,
                iconColor = Purple,
                iconBackground = Color(0xFFF3E5F5),
                title = "Часов сна",
                value = "9.6",
                valueColor = Purple,
                animatedPlayed = animationPlayed,
                delay = 300,
                modifier = Modifier.weight(1f)
            )
        }

    }
}

@Preview
@Composable
fun StatsGridPreview() {
    FitnessTrackerTheme {
        StatsGrid()
    }
}