package com.example.plantguide.ui.screens.myplants.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.plantguide.R
import com.example.plantguide.data.FakePlants
import com.example.plantguide.ui.theme.TextMuted

@Composable
fun MyPlantsHeader(){
    //Üst Başlık
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Başlıklar
        Column {
            Text(
                "Bitkilerim",
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                "Bahçende ${FakePlants.size} bitki bulunuyor",
                style = MaterialTheme.typography.bodyMedium,
                color = TextMuted
            )
        }
        // Ekle Butonu
        Button(onClick = { /* ekle sayfasına git */ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.width(ButtonDefaults.IconSpacing))
            Text("Ekle", style = MaterialTheme.typography.bodyMedium)
        }
    }
}