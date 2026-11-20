package com.example.plantguide.ui.screens.myplants

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.plantguide.ui.screens.myplants.components.FilterChipRow
import com.example.plantguide.ui.screens.myplants.components.MyPlantsHeader
import com.example.plantguide.ui.screens.myplants.components.SearchField

import com.example.plantguide.ui.theme.PlantGuideTheme

@Composable
fun MyPlantsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        // Başlık
        MyPlantsHeader()

        // Bitki arama
        SearchField()

        // Filter Çip
        FilterChipRow()
    }
}

@Preview(showBackground = true)
@Composable
fun MyPlantsScreenPreview() {
    PlantGuideTheme {
        MyPlantsScreen()
    }
}