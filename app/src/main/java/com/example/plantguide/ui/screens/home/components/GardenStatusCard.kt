package com.example.plantguide.ui.screens.home.components

import android.hardware.lights.Light
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantguide.R
import com.example.plantguide.ui.theme.Green
import com.example.plantguide.ui.theme.UrgentText

@Composable
fun GardenStatusCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(32.dp),
        color = Color(0xFFFBEDE3),
        border = BorderStroke(1.dp, Color(0xFF8A7A6A)),
        shadowElevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            // Başlık satırı
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_flower),
                    contentDescription = null,
                    tint = UrgentText,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Bahçenizin Durumu",
                    color = Color(0xFF3E2F23),
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFEADFD3)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "›", fontSize = 20.sp, color = Color(0xFF3E2F23))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Alt kısım: sağlık göstergesi + istatistikler
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(110.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        progress = { 0.92f },
                        modifier = Modifier.fillMaxSize(),
                        color = Green,
                        trackColor = Color(0xFFD9D2C5),
                        strokeWidth = 10.dp
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "%92",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3E2F23)
                        )
                        Text("Genel Sağlık", fontSize = 11.sp, color = Color(0xFF6B5E52))
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(70.dp)
                        .background(Color(0xFFD9CFC2))
                )
                Spacer(modifier = Modifier.width(16.dp))

                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    StatItem(value = "12", label = "Toplam Bitki")
                    StatItem(value = "3", label = "Bakım Gerekiyor")
                }
            }
        }
    }
}

@Composable
private fun StatItem(value: String, label: String) {
    Column {
        Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3E2F23))
        Text(label, fontSize = 12.sp, color = Color(0xFF6B5E52))
    }
}