package aleksei.bakycharov.sporttracker.android.ui.components

import aleksei.bakycharov.sporttracker.android.ui.theme.BlueBg
import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import aleksei.bakycharov.sporttracker.android.ui.theme.GreenDark
import aleksei.bakycharov.sporttracker.android.ui.theme.Orange
import aleksei.bakycharov.sporttracker.android.ui.theme.Teal
import aleksei.bakycharov.sporttracker.android.ui.theme.TextSecondary
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun LineChart(
    stepsData: List<Pair<String, Float>>,
    caloriesData: List<Pair<String, Float>>,
    animationPlayed: Boolean,
    modifier: Modifier = Modifier
) {
    val animatedProgress by animateFloatAsState(
        targetValue = if (animationPlayed) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1500,
            easing = FastOutSlowInEasing
        ),
        label = "chartProgress"
    )

    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    val paddingStart = 16f
    val paddingEnd = 16f

    Column(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        val chartWidth = size.width - paddingStart - paddingEnd
                        val index = ((offset.x - paddingStart) / chartWidth * (stepsData.size - 1))
                            .roundToInt()
                            .coerceIn(0, stepsData.size - 1)
                        selectedIndex = if (selectedIndex == index) null else index
                    }
                }
        ) {
            val width = size.width
            val height = size.height
            val paddingTop = 20f
            val paddingBottom = 20f
            val chartHeight = height - paddingTop - paddingBottom

            val maxSteps = stepsData.maxOf { it.second }.coerceAtLeast(1f)
            val maxCalories = caloriesData.maxOf { it.second }.coerceAtLeast(1f)

            for (i in 0..4) {
                val y = paddingTop + chartHeight * (1 - i / 4f)
                drawLine(
                    color = Color.Gray.copy(alpha = 0.15f),
                    start = Offset(0f, y),
                    end = Offset(width, y),
                    strokeWidth = 1f
                )
            }

            fun drawSmoothLine(
                data: List<Pair<String, Float>>,
                maxValue: Float,
                color: Color
            ) {
                if (data.size < 2) return
                val chartWidth = width - paddingStart - paddingEnd
                val points = data.mapIndexed { index, (_, value) ->
                    Offset(
                        x = paddingStart + chartWidth * index / (data.size - 1).coerceAtLeast(1),
                        y = paddingTop + chartHeight * (1 - value / maxValue)
                    )
                }

                val path = Path()
                path.moveTo(points.first().x, points.first().y)
                for (i in 1 until points.size - 1) {
                    val point1 = points[i]
                    val point2 = points[i + 1]
                    val controlX1 = (point1.x + point2.x) / 2
                    path.cubicTo(controlX1, point1.y, controlX1, point2.y, point2.x, point2.y)
                }

                val animatedPath = Path()
                val measure = PathMeasure()
                measure.setPath(path, false)
                val length = measure.length
                measure.getSegment(0f, length * animatedProgress, animatedPath, true)

                drawPath(
                    path = animatedPath,
                    color = color,
                    style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                )
            }

            drawSmoothLine(stepsData, maxSteps, GreenDark)
            drawSmoothLine(caloriesData, maxCalories, Orange)

            selectedIndex?.let { index ->
                val chartWidth = width - paddingStart - paddingEnd
                val x = paddingStart + chartWidth * index / (stepsData.size - 1).coerceAtLeast(1)
                val stepsY = paddingTop + chartHeight * (1 - stepsData[index].second / maxSteps)
                val caloriesY = paddingTop + chartHeight * (1 - caloriesData[index].second / maxCalories)

                drawLine(
                    color = Color.Gray.copy(alpha = 0.3f),
                    start = Offset(x, paddingTop),
                    end = Offset(x, height - paddingBottom),
                    strokeWidth = 1.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                )

                drawCircle(GreenDark, 6.dp.toPx(), Offset(x, stepsY))
                drawCircle(Color.White, 3.dp.toPx(), Offset(x, stepsY))
                drawCircle(Orange, 6.dp.toPx(), Offset(x, caloriesY))
                drawCircle(Color.White, 3.dp.toPx(), Offset(x, caloriesY))
            }
        }

        selectedIndex?.let { index ->
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = BlueBg,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = stepsData[index].first,
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Text(
                        text = "Шаги: ${stepsData[index].second.toInt()} шагов",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Text(
                        text = "Калории: ${caloriesData[index].second.toInt()} ккал",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            stepsData.forEach { (date, _) ->
                Text(
                    text = date,
                    fontSize = 10.sp,
                    color = TextSecondary,
                    modifier = Modifier.width(36.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Canvas(modifier = Modifier.size(8.dp)) {
                drawCircle(color = Teal)
            }
            Text(" Шаги", fontSize = 12.sp, color = TextSecondary)
            Spacer(modifier = Modifier.width(16.dp))
            Canvas(modifier = Modifier.size(8.dp)) {
                drawCircle(color = Orange)
            }
            Text(" Калории", fontSize = 12.sp, color = TextSecondary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LineChartPreview() {
    val stepsData = listOf(
        "20.02" to 3500f,
        "21.02" to 2800f,
        "22.02" to 3200f,
        "23.02" to 1800f,
        "24.02" to 2500f,
        "25.02" to 4200f,
        "26.02" to 4500f
    )
    val caloriesData = listOf(
        "20.02" to 200f,
        "21.02" to 150f,
        "22.02" to 180f,
        "23.02" to 100f,
        "24.02" to 250f,
        "25.02" to 300f,
        "26.02" to 280f
    )

    FitnessTrackerTheme {
        LineChart(
            stepsData = stepsData,
            caloriesData = caloriesData,
            animationPlayed = true
        )
    }
}