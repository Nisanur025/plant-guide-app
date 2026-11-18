package com.example.plantguide.ui.screens.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.plantguide.ui.theme.GoldLine
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun HandDrawUnderline(
    modifier: Modifier = Modifier,
    color: Color = GoldLine
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(15.dp)
    ) {
        val w = size.width
        val h = size.height

        val path = Path().apply {
            moveTo(0f, h * 0.7f)
            cubicTo(
                w * 0.30f, h * 0.15f,   // 1. kontrol noktası: yukarı çeker
                w * 0.65f, h * 1.70f,   // 2. kontrol noktası: aşağı çeker
                w,         h * 0.40f    // bitiş noktası
            )
        }
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}