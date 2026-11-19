package com.example.plantguide.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun BottomHills(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.fillMaxWidth()) {
        // Arkadan öne doğru: en açık -> en koyu
        drawWave(Color(0xFFE3E9D3), baseline = 0.30f, amplitude = 0.22f, frequency = 1.2f, phase = 0.0f)
        drawWave(Color(0xFFCBD8B8), baseline = 0.50f, amplitude = 0.25f, frequency = 1.8f, phase = 1.5f)
        drawWave(Color(0xFF7E9870), baseline = 0.62f, amplitude = 0.22f, frequency = 1.0f, phase = 3.0f)
        drawWave(Color(0xFFA9BC93), baseline = 0.70f, amplitude = 0.16f, frequency = 2.5f, phase = 0.8f)
        drawWave(Color(0xFF6F8A62), baseline = 0.80f, amplitude = 0.06f, frequency = 3.2f, phase = 2.2f)
    }
}

private fun DrawScope.drawWave(
    color: Color,
    baseline: Float,   // dalganın ortası: 0 = en üst, 1 = en alt
    amplitude: Float,  // tepe ve çukurun yüksekliği
    frequency: Float,  // genişlik boyunca kaç dalga olacağı
    phase: Float       // dalganın yatayda kayması
) {
    val steps = 120
    val path = Path().apply {
        moveTo(0f, size.height)
        for (i in 0..steps) {
            val t = i / steps.toFloat()
            val x = t * size.width
            val y = size.height *
                    (baseline - amplitude * sin(2f * PI.toFloat() * frequency * t + phase))
            lineTo(x, y)
        }
        lineTo(size.width, size.height)
        close()
    }
    drawPath(path, color)
}