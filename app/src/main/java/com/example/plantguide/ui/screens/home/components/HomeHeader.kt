package com.example.plantguide.ui.screens.home.components

import com.example.plantguide.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.plantguide.ui.theme.LeafGreen
import com.example.plantguide.ui.theme.TextMuted


@Composable
fun HomeHeader(){
    Row(
        modifier =Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 24.dp),
        verticalAlignment =Alignment.Top
    ){
        Icon(
            painter = painterResource(id = R.drawable.ic_leaf),
            contentDescription = null,
            tint = LeafGreen,
            modifier = Modifier.size(44.dp)
        )
        Spacer(Modifier.width(8.dp))

        // Başlık
        Column(
            modifier = Modifier.weight(1f)
        ){
            Text("FloraMind", style = MaterialTheme.typography.titleLarge)
            Text("Akıllı Bahçe Asistanı" ,style = MaterialTheme.typography.bodyMedium, color = TextMuted)
            HandDrawUnderline(Modifier.width(150.dp))
        }

        // Bildirim ikonu
        Box {
            Surface(
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 4.dp,
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = "Bildirimler",
                        tint = LeafGreen,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            // kırmızı nokta
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(Color(0xFFE05A5A), CircleShape)
                    .align(Alignment.TopEnd)
            )
        }
    }
}