package aleksei.bakycharov.sporttracker.android.ui.screens.workout.components

import aleksei.bakycharov.sporttracker.android.ui.theme.Blue
import aleksei.bakycharov.sporttracker.android.ui.theme.CardBackground
import aleksei.bakycharov.sporttracker.android.ui.theme.Green
import aleksei.bakycharov.sporttracker.android.ui.theme.Orange
import aleksei.bakycharov.sporttracker.android.ui.theme.Red
import aleksei.bakycharov.sporttracker.android.ui.theme.TextPrimary
import aleksei.bakycharov.sporttracker.android.ui.theme.TextSecondary
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class Intensity(val label: String, val color: Color) {
    ALL("Все", Blue),
    LOW("Низкая", Green),
    MEDIUM("Средняя", Orange),
    HIGH("Высокая", Red)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun IntensityFilter(
    selected: Intensity,
    onSelect: (Intensity) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(20.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Tune,
                    contentDescription = null,
                    tint = TextSecondary
                )
                Text(
                    text = "Интенсивность",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Intensity.entries.forEach { intensity ->
                    val isSelected = selected == intensity
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = if (isSelected) intensity.color else Color.Transparent,
                        border = if (!isSelected) BorderStroke(1.dp, Color.LightGray) else null,
                        modifier = Modifier.clickable {
                            onSelect(intensity)
                        }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            if (!isSelected && intensity != Intensity.ALL) {
                                Canvas(modifier = Modifier.size(8.dp)) {
                                    drawCircle(intensity.color)
                                }
                            }
                            Text(
                                text = intensity.label,
                                color = if (isSelected) Color.White else TextPrimary,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}