package com.example.plantguide.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.plantguide.R
import com.example.plantguide.ui.screens.home.data.TodoTask
import com.example.plantguide.ui.screens.home.data.TodoType
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon

@Composable
fun TodoItem(task: TodoTask, onToggle: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(26.dp),
        color = Color(0xFFFBF1E8),
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 1.dp

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            AsyncImage(
                model = task.imageUrl,
                contentDescription = task.title,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.bitki_placeholder),
                error = painterResource(R.drawable.bitki_placeholder),
                fallback = painterResource(R.drawable.bitki_placeholder),
                modifier = Modifier.size(56.dp).clip(CircleShape)
            )

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    textDecoration = if (task.isDone) TextDecoration.LineThrough else null
                )
                Text(text = task.subtitle, fontSize = 13.sp, color = Color.Gray)
            }

            StatusChip(task.type)
            Spacer(Modifier.width(12.dp))
            CheckCircle(done = task.isDone, onClick = onToggle)
        }
    }
}


@Composable
fun CheckCircle(done: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
            .background(if (done) Color(0xD29ED4A1) else Color.Transparent)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (done) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "Tamamlandı",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        } else {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color.Transparent)
                    .border(1.dp, Color.Gray, CircleShape)
            )
        }
    }
}

@Composable
fun StatusChip(type: TodoType) {
    val (text, backgroundColor, textColor) = when (type) {
        TodoType.YAKLASAN -> Triple("Yaklaşan", Color(0xFFF9FBE7), Color(0xFF827717))
        TodoType.HATIRLATMA -> Triple("Hatırlatma", Color(0xFFE8F5E9), Color(0xFF43A047))
        TodoType.ACIL -> Triple("Acil", Color(0xFFFFF3E0), Color(0xFFF57C00))
        TodoType.SULAMA -> Triple("Sulama", Color(0xFFE3F2FD), Color(0xFF1976D2))
        TodoType.GUBRE -> Triple("Gübre", Color(0xFFFFF3E0), Color(0xFFF57C00))
        TodoType.BUDAMA -> Triple("Budama", Color(0xFFFCE4EC), Color(0xFFC2185B))
        else -> Triple("Bakım", Color(0xFFF1F8E9), Color(0xFF388E3C))
    }

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}