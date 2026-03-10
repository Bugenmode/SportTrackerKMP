package aleksei.bakycharov.sporttracker.android.ui.screens.sleep.components

import aleksei.bakycharov.sporttracker.android.ui.theme.Blue
import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import aleksei.bakycharov.sporttracker.android.ui.theme.Purple
import aleksei.bakycharov.sporttracker.android.ui.theme.TextSecondary
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SleepChart(
    totalData: List<Pair<String, Float>>,
    deepData: List<Pair<String, Float>>,
    animationPlayed: Boolean,
    modifier: Modifier = Modifier
) {
    val animatedProgress by animateFloatAsState(
        targetValue = if (animationPlayed) 1f else 0f,
        animationSpec = tween(1500, easing = FastOutSlowInEasing),
        label = "sleepChart"
    )

    Column(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            val width = size.width
            val height = size.height
            val paddingTop = 10f
            val paddingBottom = 10f
            val chartHeight = height - paddingTop - paddingBottom
            val maxValue = totalData.maxOf { it.second }.coerceAtLeast(1f)

            for (i in 0..3) {
                val y = paddingTop + chartHeight * (1 - i / 3f)
                drawLine(
                    color = Color.Gray.copy(alpha = 0.1f),
                    start = Offset(0f, y),
                    end = Offset(width, y),
                    strokeWidth = 1f
                )
            }

            fun buildSmoothPath(data: List<Pair<String, Float>>): Path {
                val points = data.mapIndexed { index, (_, value) ->
                    Offset(
                        x = width * index / (data.size - 1).coerceAtLeast(1),
                        y = paddingTop + chartHeight * (1 - value / maxValue)
                    )
                }
                val path = Path()
                path.moveTo(points.first().x, points.first().y)
                for (i in 0 until points.size - 1) {
                    val p0 = points[i]
                    val p1 = points[i + 1]
                    val cx = (p0.x + p1.x) / 2
                    path.cubicTo(cx, p0.y, cx, p1.y, p1.x, p1.y)
                }
                return path
            }

            fun drawAreaChart(data: List<Pair<String, Float>>, color: Color, alpha: Float) {
                val linePath = buildSmoothPath(data)

                val measure = PathMeasure()
                measure.setPath(linePath, false)

                val currentLength = measure.length * animatedProgress
                val animatedPath = Path()

                measure.getSegment(
                    startDistance = 0f,
                    stopDistance = currentLength,
                    destination = animatedPath,
                    startWithMoveTo = true)

                drawPath(
                    path = animatedPath,
                    color = color,
                    style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
                )

                val lastPosition = measure.getPosition(currentLength)

                val fillPath = Path().apply {
                    addPath(animatedPath)

                    lineTo(lastPosition.x, height - paddingBottom)
                    lineTo(0f, height - paddingBottom)
                    close()
                }

                drawPath(
                    path = fillPath,
                    color = color.copy(alpha = alpha)
                )
            }

            drawAreaChart(totalData, Purple, 0.2f)
            drawAreaChart(deepData, Blue, 0.15f)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            totalData.forEachIndexed { index, (date, _) ->
                if (index % 2 == 0) {
                    Text(date, fontSize = 10.sp, color = TextSecondary)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Canvas(modifier = Modifier.size(8.dp)) { drawCircle(Purple) }
            Text(" Всего (ч)", fontSize = 12.sp, color = TextSecondary)
            Spacer(modifier = Modifier.width(16.dp))
            Canvas(modifier = Modifier.size(8.dp)) { drawCircle(Blue) }
            Text(" Глубокий (ч)", fontSize = 12.sp, color = TextSecondary)
        }
    }
}

@Composable
@Preview
fun SleepChartPreview() {
    var animatedPlayed by remember { mutableStateOf(false) }

    val totalData = listOf(
        "13.02" to 7.2f, "14.02" to 6.5f, "15.02" to 8.0f, "16.02" to 7.8f,
        "17.02" to 6.0f, "18.02" to 7.5f, "19.02" to 8.2f, "20.02" to 5.5f,
        "21.02" to 9.0f, "22.02" to 7.0f, "23.02" to 9.3f, "24.02" to 7.5f,
        "25.02" to 6.3f, "26.02" to 9.6f
    )
    val deepData = listOf(
        "13.02" to 2.1f, "14.02" to 1.8f, "15.02" to 2.5f, "16.02" to 2.3f,
        "17.02" to 1.5f, "18.02" to 2.0f, "19.02" to 2.8f, "20.02" to 1.2f,
        "21.02" to 3.0f, "22.02" to 2.0f, "23.02" to 2.8f, "24.02" to 2.5f,
        "25.02" to 1.8f, "26.02" to 2.9f
    )

    FitnessTrackerTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SleepChart(
                totalData = totalData,
                deepData = deepData,
                animationPlayed = animatedPlayed
            )

            Button(onClick = { animatedPlayed = !animatedPlayed }) {
                Text("Toggle")
            }
        }
    }
}