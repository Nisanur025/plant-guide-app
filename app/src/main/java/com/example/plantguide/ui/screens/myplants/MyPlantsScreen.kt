package com.example.plantguide.ui.screens.myplants

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantguide.data.FakePlants
import com.example.plantguide.ui.screens.myplants.components.FilterChipRow
import com.example.plantguide.ui.screens.myplants.components.MyPlantsHeader
import com.example.plantguide.ui.screens.myplants.components.PlantCard
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

        // Fake Bitkiler
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f),
            contentPadding =PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(FakePlants,key={it.id}){
                plant->
                PlantCard(plant = plant, onWaterClick = { })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPlantsScreenPreview() {
    PlantGuideTheme {
        MyPlantsScreen()
    }
}