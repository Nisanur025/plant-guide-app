package com.example.plantguide.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantguide.ui.screens.home.components.CharacterHeight
import com.example.plantguide.ui.screens.home.components.CharacterOverlap
import com.example.plantguide.ui.screens.home.components.GardenStatusCard
import com.example.plantguide.ui.screens.home.components.HomeCharacterSection
import com.example.plantguide.ui.screens.home.components.HomeHeader
import com.example.plantguide.ui.theme.PlantGuideTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    // En dıştaki ana sütun
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        // Üst başlık sabit durur
        HomeHeader()

        // Kaydırılabilir alan (weight(1f) ile kalan tüm boş alanı doldurur)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                // 1) Kart önce çizilir (altta kalır)
                GardenStatusCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = CharacterHeight - CharacterOverlap)
                )

                // 2) Karakter ve balon sonra çizilir (kartın üstüne biner)
                HomeCharacterSection()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PlantGuideTheme {
        HomeScreen()
    }
}