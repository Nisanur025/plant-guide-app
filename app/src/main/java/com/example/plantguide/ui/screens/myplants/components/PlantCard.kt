package com.example.plantguide.ui.screens.myplants.components

import com.example.plantguide.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.plantguide.model.Plant
import com.example.plantguide.model.PlantStatus
import com.example.plantguide.ui.theme.DarkGreen
import com.example.plantguide.ui.theme.LeafGreen
import com.example.plantguide.ui.theme.SoftGreen
import com.example.plantguide.ui.theme.TextMuted

@Composable
fun StatusBadge(status: PlantStatus, modifier: Modifier = Modifier) {
    val background = when (status) {
        PlantStatus.HEALTHY -> LeafGreen
        PlantStatus.NEEDS_WATER -> Color(0xFFF2B866)
        PlantStatus.NEEDS_FERTILIZER -> Color(0xFFD9E3A8)
    }
    Text(
        text = status.label,
        style = MaterialTheme.typography.labelMedium,
        color = Color.White,
        modifier = modifier
            .background(background, RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    )
}

@Composable
fun PlantCard(plant: Plant, onWaterClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFAF6EA)),
        elevation = CardDefaults.cardElevation(6.dp)
    )
    {
        Column(Modifier.padding(10.dp))
        {
            Box {
                Image(
                    painter = painterResource(plant.imageRes),
                    contentDescription = plant.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.2f)
                        .clip(RoundedCornerShape(16.dp))
                )
                StatusBadge(
                    status = plant.status,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                )
            }
            Spacer(Modifier.height(6.dp))
            Text(
                plant.name,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                plant.location,
                style = MaterialTheme.typography.bodySmall,
                color = TextMuted
            )
            Spacer(Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painterResource(R.drawable.ic_drop), null,
                    tint = DarkGreen, modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(6.dp))

                LinearProgressIndicator(
                    progress ={plant.waterLevel},
                    modifier = Modifier.weight(1f).height(6.dp).clip(CircleShape),
                    color =LeafGreen,
                    trackColor = SoftGreen
                )
                Spacer(Modifier.width(6.dp))
                FilledIconButton(onClick = onWaterClick, modifier = Modifier.size(40.dp)) {
                    Icon(painterResource(R.drawable.ic_drop), null)
                }

            }
        }
    }
}