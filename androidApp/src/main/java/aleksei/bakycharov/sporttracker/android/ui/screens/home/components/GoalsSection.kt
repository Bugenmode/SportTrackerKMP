package aleksei.bakycharov.sporttracker.android.ui.screens.home.components

import aleksei.bakycharov.sporttracker.android.ui.components.LinearProgressBar
import aleksei.bakycharov.sporttracker.android.ui.theme.Blue
import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import aleksei.bakycharov.sporttracker.android.ui.theme.GreenLight
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GoalsSection(
    modifier: Modifier = Modifier,
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
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFE8F5E9)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Flag,
                        contentDescription = null,
                        tint = Blue,
                        modifier = Modifier.padding(8.dp).size(20.dp)
                    )
                }
                Text(
                    text = "Цели",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            LinearProgressBar(
                label = "10,000 steps per day",
                current = 14426f,
                target = 10000f,
                color = GreenLight,
                animationPlayed = animationPlayed,
                delay = 0
            )
            Spacer(modifier = Modifier.height(20.dp))
            LinearProgressBar(
                label = "5 workouts per week",
                current = 2f,
                target = 5f,
                color = Blue,
                animationPlayed = animationPlayed,
                delay = 150
            )
            Spacer(modifier = Modifier.height(16.dp))
            LinearProgressBar(
                label = "8 hours of sleep",
                current = 9.6f,
                target = 8f,
                color = GreenLight,
                animationPlayed = animationPlayed,
                delay = 300
            )
            Spacer(modifier = Modifier.height(16.dp))
            LinearProgressBar(
                label = "Burn 2,500 calories",
                current = 0f,
                target = 2500f,
                color = Blue,
                animationPlayed = animationPlayed,
                delay = 450
            )
        }
    }
}

@Preview
@Composable
fun GoalsSectionPreview() {
    FitnessTrackerTheme {
        GoalsSection()
    }
}