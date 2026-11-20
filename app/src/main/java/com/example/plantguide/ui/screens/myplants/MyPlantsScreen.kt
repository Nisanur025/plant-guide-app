package com.example.plantguide.ui.screens.myplants

import com.example.plantguide.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantguide.ui.screens.myplants.components.FilterChipRow
import com.example.plantguide.ui.screens.myplants.components.MyPlantsHeader
import com.example.plantguide.ui.theme.DarkGreen
import com.example.plantguide.ui.theme.PlantGuideTheme
import com.example.plantguide.ui.theme.TextMuted

@Composable
fun MyPlantsScreen() {
    var userText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        // Başlık
        MyPlantsHeader()

        // Bitki arama
        OutlinedTextField(
            value = userText,
            onValueChange = { userText = it },
            placeholder = { Text("Bitki ara...", color = TextMuted) },
            shape = RoundedCornerShape(20.dp),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = DarkGreen
                )
            }
        )
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