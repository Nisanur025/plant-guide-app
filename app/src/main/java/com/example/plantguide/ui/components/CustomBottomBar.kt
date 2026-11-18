package com.example.plantguide.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantguide.R
import com.example.plantguide.model.NavItem
import com.example.plantguide.ui.theme.Cream
import com.example.plantguide.ui.theme.DarkGreen
import com.example.plantguide.ui.theme.UnselectedGray

@Composable
fun CustomBottomBar(
    items: List<NavItem>,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit,
    onFabClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        // Krem renkli, üst köşeleri yuvarlak bar
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            color = Cream,
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            shadowElevation = 4.dp
        ) {
            Row(
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(top = 12.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in items.indices) {
                    if (i == 2) {
                        // Ortadaki + butonu için boşluk
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    BottomBarItem(
                        item = items[i],
                        selected = selectedIndex == i,
                        onClick = { onItemClick(i) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Ortadaki yuvarlak + butonu
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape) // Şekle göre kes
                .background(Cream)
                .padding(4.dp)
                .clip(CircleShape)
                .background(DarkGreen)
                .clickable(onClick = onFabClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = "Asistan",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
fun BottomBarItem(
    item: NavItem,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val color = if (selected) DarkGreen else UnselectedGray
    val iconRes = if (selected) item.selectedIcon else item.unSelectedIcon

    Column(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = item.title,
            tint = color,
            modifier = Modifier.size(26.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.title,
            color = color,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        // Seçili öğenin altındaki çizgi
        Box(
            modifier = Modifier
                .width(36.dp)
                .height(2.dp)
                .background(if (selected) DarkGreen else Color.Transparent)
        )
    }
}