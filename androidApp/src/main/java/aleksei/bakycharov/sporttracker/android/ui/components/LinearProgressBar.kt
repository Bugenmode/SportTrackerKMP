package aleksei.bakycharov.sporttracker.android.ui.components

import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import aleksei.bakycharov.sporttracker.android.ui.theme.GreenLight
import aleksei.bakycharov.sporttracker.android.ui.theme.TextPrimary
import aleksei.bakycharov.sporttracker.android.ui.theme.TextSecondary
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LinearProgressBar(
    modifier: Modifier = Modifier,
    label: String,
    current: Float,
    target: Float,
    color: Color,
    animationPlayed: Boolean,
    delay: Int = 0,
) {
    val progress = (current / target).coerceIn(0f, 1f)
    val animatedProgress by animateFloatAsState(
        targetValue = if (animationPlayed) progress else 0f,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = delay,
            easing = FastOutSlowInEasing
        ),
        label = "goalProgress"
    )

    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, fontSize = 14.sp, color = TextPrimary)
            Text(
                text = "${current.toInt()} / ${target.toInt()}",
                fontSize = 14.sp,
                color = TextSecondary
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color.copy(alpha = 0.15f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(animatedProgress)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color)
            )
        }
    }
}

@Preview
@Composable
fun LinearProgressBarPreview() {
    FitnessTrackerTheme {
        LinearProgressBar(
            label = "Preview",
            current = 14426f,
            target = 10000f,
            color = GreenLight,
            animationPlayed = true,
            delay = 0
        )
    }
}
