package com.example.plantguide.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantguide.R
import com.example.plantguide.ui.screens.home.components.CharacterHeight
import com.example.plantguide.ui.screens.home.components.CharacterOverlap
import com.example.plantguide.ui.screens.home.components.GardenStatusCard
import com.example.plantguide.ui.screens.home.components.HomeCharacterSection
import com.example.plantguide.ui.screens.home.components.HomeHeader
import com.example.plantguide.ui.screens.home.components.QuickAccessCard
import com.example.plantguide.ui.screens.home.components.TodaySection
import com.example.plantguide.ui.theme.DarkGreen
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
            val viewModel: TodayViewModel = viewModel()
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            // Bugünün yapılacakları
            TodaySection(
                state = state,
                onToggle = viewModel::toggle,
                onRetry = viewModel::load,
                onSeeAll = { /* sonra */ }
            )

            // Hızlı Erişim
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 20.dp)
            ){
                // Başlık
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_shine_star),
                        contentDescription = null,
                        tint = Color(0xFFE0A93B),
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Hızlı Erişim",
                        style= MaterialTheme.typography.bodyLarge,
                        color = Color.DarkGray
                    )
                }

                // Kartlar
                Row(modifier = Modifier.fillMaxWidth()
                    .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)){
                    // Kart Bitki analizi
                    QuickAccessCard(
                        title = "Bitki Analizi",
                        iconRes = R.drawable.ic_camera,
                        backgroundColor = Color(0xFFD9E5D2),
                        modifier = Modifier.weight(1f),
                        onClick = {/*Kamera sayfasına yönlendirme eklenecek*/ }
                    )
                    // Kart Ekim Takvimi
                    QuickAccessCard(
                        title = "Ekim Takvimi",
                        iconRes = R.drawable.ic_planting_calendar,
                        backgroundColor = Color(0xFFE3D8F0),
                        modifier = Modifier.weight(1f),
                        onClick = {/*Ekim sayfasına yönlendirme eklenecek*/ }
                    )
                    // kart Hava Durumu
                    QuickAccessCard(
                        title = "Hava Durumu",
                        iconRes = R.drawable.ic_weather,
                        backgroundColor = Color(0xFFF7D9C0),
                        modifier = Modifier.weight(1f),
                        onClick = {/*hava durumu sayfasına yönlendirme eklenecek*/ }
                    )
                    //kart bahçe günlüğüm
                    QuickAccessCard(
                        title = "Bahçe Günlüğüm",
                        iconRes = R.drawable.ic_garden_journal,
                        backgroundColor = Color(0xC9EBC7DC),
                        modifier = Modifier.weight(1f),
                        onClick = {/*Günlük sayfasına yönelendirilecek*/
                        }
                    )
                }
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