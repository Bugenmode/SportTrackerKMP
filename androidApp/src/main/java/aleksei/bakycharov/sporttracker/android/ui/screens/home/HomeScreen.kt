package aleksei.bakycharov.sporttracker.android.ui.screens.home

import aleksei.bakycharov.sporttracker.android.ui.screens.home.components.GoalsSection
import aleksei.bakycharov.sporttracker.android.ui.screens.home.components.HomeHeader
import aleksei.bakycharov.sporttracker.android.ui.screens.home.components.ProgressSection
import aleksei.bakycharov.sporttracker.android.ui.screens.home.components.StatsGrid
import aleksei.bakycharov.sporttracker.android.ui.screens.home.components.WeeklyActivitySection
import aleksei.bakycharov.sporttracker.android.ui.theme.Background
import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(animationKey: String = "") {
    Scaffold(
        topBar = { HomeHeader() }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .background(Background),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            ProgressSection(animationKey = animationKey)
            StatsGrid(animationKey = animationKey)
            WeeklyActivitySection(animationKey = animationKey)
            GoalsSection(animationKey = animationKey)
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    FitnessTrackerTheme {
        HomeScreen()
    }
}