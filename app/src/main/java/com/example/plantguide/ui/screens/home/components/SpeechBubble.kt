package com.example.plantguide.ui.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class BubbleShape(
    private val cornerRadius: Dp,
    private val tailWidth: Dp,
    private val tailHeight: Dp
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        // Dp'yi piksele çeviriyoruz, Path piksel ile çalışır
        val r = with(density) { cornerRadius.toPx() }
        val tailW = with(density) { tailWidth.toPx() }
        val tailH = with(density) { tailHeight.toPx() }

        // Balonun gövdesi, kuyruk payını çıkardıktan sonraki yükseklik
        val bodyBottom = size.height - tailH

        // Kuyruğun tabanının sağ ucu (köşeden biraz içeride)
        val tailRight = size.width - r * 1.3f
        val tailLeft = tailRight - tailW

        val path = Path().apply {
            // Sol üst köşeden başla, saat yönünde ilerle
            moveTo(r, 0f)
            lineTo(size.width - r, 0f)
            // Sağ üst köşe yayı (-90°'den başlar, 90° döner)
            arcTo(Rect(size.width - 2 * r, 0f, size.width, 2 * r), -90f, 90f, false)
            lineTo(size.width, bodyBottom - r)
            // Sağ alt köşe yayı
            arcTo(Rect(size.width - 2 * r, bodyBottom - 2 * r, size.width, bodyBottom), 0f, 90f, false)

            // Kuyruk: alt kenarda sağdan sola giderken üçgen çiz
            lineTo(tailRight, bodyBottom)
            lineTo(tailRight + tailW * 0.4f, size.height) // uç nokta, hafif sağa
            lineTo(tailLeft, bodyBottom)

            lineTo(r, bodyBottom)
            // Sol alt köşe yayı
            arcTo(Rect(0f, bodyBottom - 2 * r, 2 * r, bodyBottom), 90f, 90f, false)
            lineTo(0f, r)
            // Sol üst köşe yayı
            arcTo(Rect(0f, 0f, 2 * r, 2 * r), 180f, 90f, false)
            close()
        }
        return Outline.Generic(path)
    }
}

@Composable
fun SpeechBubble(
    text: String,
    modifier: Modifier = Modifier
) {
    val tailHeight = 12.dp

    Surface(
        modifier = modifier,
        shape = BubbleShape(
            cornerRadius = 25.dp,
            tailWidth = 18.dp,
            tailHeight = tailHeight
        ),
        color = Color(0xFFFFF8F0),
        border = BorderStroke(1.dp, Color(0xFF8A7A6A)),
        shadowElevation = 4.dp
    ) {
        Text(
            text = text,
            color = Color(0xFF3E2F23),
            fontSize = 13.sp,
            lineHeight = 18.sp,
            modifier = Modifier.padding(
                start = 14.dp,
                end = 10.dp,
                top = 10.dp,
                bottom = 10.dp + tailHeight
            )
        )
    }
}