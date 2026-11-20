package com.example.plantguide.ui.screens.myplants

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantguide.data.FakePlants
import com.example.plantguide.ui.components.drawBranch
import com.example.plantguide.ui.components.drawLoopFlowerWave
import com.example.plantguide.ui.components.drawTulipLine
import com.example.plantguide.ui.components.drawWatercolorBranch
import com.example.plantguide.ui.screens.myplants.components.FilterChipRow
import com.example.plantguide.ui.screens.myplants.components.MyPlantsHeader
import com.example.plantguide.ui.screens.myplants.components.PlantCard
import com.example.plantguide.ui.screens.myplants.components.SearchField
import com.example.plantguide.ui.theme.PlantGuideTheme

@Composable
fun MyPlantsScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Arka plan çizimleri
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            val sw = 1.4.dp.toPx()
            val soft = Color(0xFF8E9B85)

            // Başlığın yanında küçük çiçekli dalga
            drawLoopFlowerWave(
                topLeft = Offset(size.width * 0.30f, 8.dp.toPx()),
                height = 60.dp.toPx(),
                strokeW = sw,
                color = soft
            )
            // Sol: sulu boya dal, arama kutusunun üstünden yükselir
            val branchH = 70.dp.toPx()
            val branchPos = Offset(2.dp.toPx(), 200.dp.toPx())
            rotate(
                degrees = 20f,
                pivot = Offset(branchPos.x + branchH * 0.25f, branchPos.y + branchH / 2f)
            ) {
                drawWatercolorBranch(
                    topLeft = branchPos,
                    height = branchH,
                    strokeW = sw,
                    color = soft,
                    fillColor = Color(0xFFA9BC9F)
                )
            }

            // Sağ: lale, Ekle butonunun altından, arama kutusunun arkasından yükselir
            val tulipH = 80.dp.toPx()
            val tulipPos = Offset(size.width - 40.dp.toPx(), 65.dp.toPx())
            rotate(
                degrees = -19f,
                pivot = Offset(tulipPos.x + tulipH * 0.25f, tulipPos.y + tulipH / 2f)
            ) {
                drawTulipLine(
                    topLeft = tulipPos,
                    height = tulipH,
                    strokeW = sw,
                    color = soft
                )
            }
            // ---- Yumuşak yeşil lekeler (sol üst) ----
            val blob = Color(0xFFCFDABB)
            drawOval(
                color = blob.copy(alpha = 0.45f),
                topLeft = Offset((-30).dp.toPx(), 10.dp.toPx()),
                size = Size(150.dp.toPx(), 140.dp.toPx())
            )
            drawOval(
                color = blob.copy(alpha = 0.30f),
                topLeft = Offset(60.dp.toPx(), (-20).dp.toPx()),
                size = Size(110.dp.toPx(), 100.dp.toPx())
            )

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            MyPlantsHeader()
            SearchField()
            Spacer(Modifier.height(10.dp))
            FilterChipRow()

            Spacer(Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(FakePlants, key = { it.id }) { plant ->
                    PlantCard(plant = plant, onWaterClick = { })
                }
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