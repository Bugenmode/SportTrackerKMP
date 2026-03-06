package aleksei.bakycharov.sporttracker.android.ui.screens.home.components

import aleksei.bakycharov.sporttracker.android.ui.components.CircularProgressBar
import aleksei.bakycharov.sporttracker.android.ui.theme.Blue
import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import aleksei.bakycharov.sporttracker.android.ui.theme.Orange
import aleksei.bakycharov.sporttracker.android.ui.theme.Purple
import aleksei.bakycharov.sporttracker.android.ui.theme.Teal
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProgressSection(
    modifier: Modifier = Modifier,
    animationKey: String = ""
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
            Text(
                text = "Прогресс за сегодня",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CircularProgressBar(
                    progress = if (animationPlayed) 1f else 0f,
                    value = "14.4k",
                    label = "шаги",
                    subtitle = "из 10к",
                    color = Blue
                )
                CircularProgressBar(
                    progress = if (animationPlayed) 1f else 0f,
                    value = "0",
                    label = "ккал",
                    subtitle = "из 2500",
                    color = Orange
                )
                CircularProgressBar(
                    progress = if (animationPlayed) 1f else 0f,
                    value = "9.6",
                    label = "сон",
                    subtitle = "из 8 ч",
                    color = Purple
                )
                CircularProgressBar(
                    progress = if (animationPlayed) 1f else 0f,
                    value = "46",
                    label = "мин",
                    subtitle = "активность",
                    color = Teal
                )
            }
        }
    }
}

@Preview
@Composable
fun ProgressSectionPreview() {
    FitnessTrackerTheme {
        ProgressSection()
    }
}