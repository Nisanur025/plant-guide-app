package com.example.plantguide.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.plantguide.R
import com.example.plantguide.model.NavItem
import com.example.plantguide.ui.components.CustomBottomBar
import com.example.plantguide.ui.theme.PlantGuideTheme

@Composable
fun MainScreen() {

    val items = listOf(
        NavItem("Ana Sayfa", R.drawable.ic_home, R.drawable.ic_home),
        NavItem("Bitkilerim", R.drawable.ic_leaf, R.drawable.ic_leaf),
        NavItem("Takvim", R.drawable.ic_calendar, R.drawable.ic_calendar),
        NavItem("Profil", R.drawable.ic_person, R.drawable.ic_person)
    )

    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            CustomBottomBar(
                items = items,
                selectedIndex = selectedIndex,
                onItemClick = { selectedIndex = it },
                onFabClick = { /* asistan sayfasına git */ }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedIndex) {
                // 0 -> HomeScreen()
                // 1 -> MyPlantsScreen()
                // 2 -> CalendarScreen()
                // 3 -> ProfileScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    PlantGuideTheme {
        MainScreen()
    }
}