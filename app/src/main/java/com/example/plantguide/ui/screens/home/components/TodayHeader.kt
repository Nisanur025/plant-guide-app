package com.example.plantguide.ui.screens.home.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantguide.R
import com.example.plantguide.ui.theme.DarkGreen

@Composable
fun TodayHeader(onSeeAll: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.calendar_icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.DarkGray
            )
            Spacer(Modifier.width(8.dp))
            Text(
                "Bugünün Yapılacakları",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.DarkGray
            )
        }
        TextButton(onClick = onSeeAll) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Tümünü Gör", color = DarkGreen, style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.width(4.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = DarkGreen
                )
            }
        }
    }
}