package com.example.plantguide.ui.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.plantguide.ui.screens.home.components.HomeHeader
import com.example.plantguide.ui.theme.PlantGuideTheme

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HomeHeader()
    }
}

@Preview(showBackground = true)
@Composable
fun HomeHeaderPreview() {
    PlantGuideTheme { HomeHeader() }
}