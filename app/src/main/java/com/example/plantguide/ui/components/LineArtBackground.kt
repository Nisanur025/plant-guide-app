package com.example.plantguide.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke


private val LineColor = Color(0xFF8E9B85).copy(alpha = 0.70f)
private val BlobColor = Color(0xFFCFDABB)
private val CloudColor = Color(0xFFE6DFD0)
private val OliveLine = Color(0xFF8C9A5E)
private val OliveFill = Color(0xFFC9D2A2)

private const val DEG = (PI / 180.0).toFloat()

@Composable
fun LineArtBackground(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val s = size.width / 528f
        val sw = 1.2.dp.toPx() / s

        withTransform({
            scale(s, s, Offset.Zero)
            translate(-17f, 0f)
        }) {
            // ---- Yumuşak yeşil lekeler ----
            drawOval(BlobColor.copy(alpha = 0.45f), Offset(-30f, 95f), Size(150f, 145f))
            drawOval(BlobColor.copy(alpha = 0.30f), Offset(240f, 120f), Size(120f, 110f))
            drawOval(BlobColor.copy(alpha = 0.30f), Offset(450f, 120f), Size(130f, 120f))

            // ---- Bulutlar ----
            drawOval(CloudColor, Offset(498f, 34f), Size(50f, 12f))     // sağ üst
            drawOval(CloudColor, Offset(512f, 26f), Size(28f, 15f))
            drawOval(CloudColor, Offset(110f, 20f), Size(50f, 15f))     // sol (yukarı alındı)
            drawOval(CloudColor, Offset(125f, 10f), Size(32f, 18f))

            // ---- Çiçekler (tek çizgi, yeşil) ----
            drawScribbleFlower(topLeft = Offset(24f, 10f), height = 160f, strokeW = sw)

            // ---- Sol dallar ----
            drawBranch(
                base = Offset(60f, 238f), tip = Offset(126f, 128f), bend = -10f,
                leafCount = 3, leafLen = 70f, color = LineColor, strokeW = sw,
                spreadDeg = 34f
            )
            drawBranch(
                base = Offset(28f, 242f), tip = Offset(100f, 175f), bend = 10f,
                leafCount = 2, leafLen = 50f, color = LineColor, strokeW = sw,
                spreadDeg = 30f, firstSide = 1
            )

            // ---- Sağ dal ----
            drawBranch(
                base = Offset(548f, 240f), tip = Offset(502f, 112f), bend = 12f,
                leafCount = 4, leafLen = 62f, color = LineColor, strokeW = sw,
                spreadDeg = 30f, firstSide = 1
            )

            // ---- Sağ üst dolgulu zeytin yeşili yapraklar ----
            drawLeaf(Offset(541f, 100f), -174f * DEG, 40f, -3f, 0.22f, OliveLine, sw, OliveFill)
            drawLeaf(Offset(533f, 98f), -82f * DEG, 30f, 3f, 0.22f, OliveLine, sw, OliveFill)
        }
    }
}

/** Kartın ÜSTÜNE binen, alt köşelerdeki küçük yapraklar */
@Composable
fun LineArtForeground(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val sw = 1.2.dp.toPx()

        drawBranch(
            base = Offset(6.dp.toPx(), h - 2.dp.toPx()),
            tip = Offset(40.dp.toPx(), h - 54.dp.toPx()),
            bend = 8.dp.toPx(), leafCount = 3, leafLen = 28.dp.toPx(),
            color = LineColor, strokeW = sw, fill = BlobColor.copy(alpha = 0.55f)
        )
        drawBranch(
            base = Offset(w - 6.dp.toPx(), h - 2.dp.toPx()),
            tip = Offset(w - 40.dp.toPx(), h - 54.dp.toPx()),
            bend = 15.dp.toPx(), leafCount = 3, leafLen = 28.dp.toPx(),
            color = LineColor, strokeW = sw, fill = BlobColor.copy(alpha = 0.55f)
        )
    }
}

/** Kıvrımlı sap + sapın teğetine göre sağa/sola dönüşümlü dizilen ince yapraklar */
fun DrawScope.drawBranch(
    base: Offset,
    tip: Offset,
    bend: Float,
    leafCount: Int,
    leafLen: Float,
    color: Color,
    strokeW: Float,
    fill: Color? = null,
    spreadDeg: Float = 32f,
    widthRatio: Float = 0.16f,
    firstSide: Int = -1
) {
    val mid = Offset((base.x + tip.x) / 2f, (base.y + tip.y) / 2f)
    val dx = tip.x - base.x
    val dy = tip.y - base.y
    val length = sqrt(dx * dx + dy * dy)
    val control = Offset(mid.x - dy / length * bend, mid.y + dx / length * bend)

    val stem = Path().apply {
        moveTo(base.x, base.y)
        quadraticTo(control.x, control.y, tip.x, tip.y)
    }
    drawPath(stem, color, style = Stroke(strokeW, cap = StrokeCap.Round))

    fun pointAt(t: Float) = Offset(
        (1 - t) * (1 - t) * base.x + 2 * (1 - t) * t * control.x + t * t * tip.x,
        (1 - t) * (1 - t) * base.y + 2 * (1 - t) * t * control.y + t * t * tip.y
    )

    fun tangentAngle(t: Float): Float {
        val tx = 2 * (1 - t) * (control.x - base.x) + 2 * t * (tip.x - control.x)
        val ty = 2 * (1 - t) * (control.y - base.y) + 2 * t * (tip.y - control.y)
        return atan2(ty, tx)
    }

    val spread = spreadDeg * DEG
    for (i in 0 until leafCount) {
        val t = 0.20f + 0.62f * i / max(1, leafCount - 1)
        val side = if (i % 2 == 0) firstSide else -firstSide
        val len = leafLen * (1.0f - 0.40f * t)
        drawLeaf(
            base = pointAt(t),
            angle = tangentAngle(t) + side * spread,
            length = len,
            curve = side * len * 0.07f,
            widthRatio = widthRatio,
            color = color, strokeW = strokeW, fill = fill
        )
    }
    // Uçtaki yaprak sapın devamı
    drawLeaf(tip, tangentAngle(1f), leafLen * 0.70f, 0f, widthRatio, color, strokeW, fill)
}

/**
 * İnce, iki ucu sivri, hafif kavisli yaprak + orta damar + yan damarlar.
 * curve: yaprağın yana eğilme miktarı, widthRatio: genişlik/uzunluk oranı
 */
fun DrawScope.drawLeaf(
    base: Offset,
    angle: Float,
    length: Float,
    curve: Float,
    widthRatio: Float,
    color: Color,
    strokeW: Float,
    fill: Color? = null
) {
    val dir = Offset(cos(angle), sin(angle))
    val nrm = Offset(-dir.y, dir.x)
    val tip = base + dir * length
    val ctrl = base + dir * (length * 0.5f) + nrm * curve

    fun p(t: Float): Offset {
        val u = 1f - t
        return Offset(
            u * u * base.x + 2 * u * t * ctrl.x + t * t * tip.x,
            u * u * base.y + 2 * u * t * ctrl.y + t * t * tip.y
        )
    }

    fun n(t: Float): Offset {
        val dx = 2 * (1 - t) * (ctrl.x - base.x) + 2 * t * (tip.x - ctrl.x)
        val dy = 2 * (1 - t) * (ctrl.y - base.y) + 2 * t * (tip.y - ctrl.y)
        val l = sqrt(dx * dx + dy * dy).coerceAtLeast(0.0001f)
        return Offset(-dy / l, dx / l)
    }

    // En geniş nokta ortanın biraz önünde
    fun halfW(t: Float) = length * widthRatio * sin(PI.toFloat() * t.pow(0.85f))

    val steps = 24
    val outline = Path().apply {
        moveTo(base.x, base.y)
        for (i in 1..steps) {
            val t = i / steps.toFloat()
            val q = p(t) + n(t) * halfW(t)
            lineTo(q.x, q.y)
        }
        for (i in steps - 1 downTo 0) {
            val t = i / steps.toFloat()
            val q = p(t) - n(t) * halfW(t)
            lineTo(q.x, q.y)
        }
        close()
    }
    if (fill != null) drawPath(outline, fill)
    drawPath(outline, color, style = Stroke(strokeW, cap = StrokeCap.Round, join = StrokeJoin.Round))

    // Orta damar
    val vein = Path().apply {
        moveTo(base.x, base.y)
        for (i in 1..12) {
            val q = p(0.92f * i / 12f)
            lineTo(q.x, q.y)
        }
    }
    drawPath(vein, color, style = Stroke(strokeW * 0.8f, cap = StrokeCap.Round, join = StrokeJoin.Round))

    // Yan damarlar (uca doğru kısalır)
    for (k in 1..4) {
        val t = 0.14f + 0.16f * k
        if (t > 0.82f) break
        val t2 = (t + 0.12f).coerceAtMost(0.95f)
        val a = p(t)
        val reach = halfW(t2) * 0.78f
        val b = p(t2)
        val nn = n(t2)
        drawLine(color, a, b + nn * reach, strokeWidth = strokeW * 0.65f, cap = StrokeCap.Round)
        drawLine(color, a, b - nn * reach, strokeWidth = strokeW * 0.65f, cap = StrokeCap.Round)
    }
}

fun DrawScope.drawScribbleFlower(
    topLeft: Offset,
    height: Float,
    strokeW: Float,
    color: Color = Color(0xFF1A1A1A),
    leafColor: Color = Color(0xFFB7C4A5)
) {
    val width = height * 0.71f
    fun p(x: Float, y: Float) = Offset(
        topLeft.x + (x - 140f) / 332f * width,
        topLeft.y + (y - 88f) / 468f * height
    )

    fun Path.m(x: Float, y: Float) { val a = p(x, y); moveTo(a.x, a.y) }
    fun Path.c(x1: Float, y1: Float, x2: Float, y2: Float, x: Float, y: Float) {
        val a = p(x1, y1); val b = p(x2, y2); val e = p(x, y)
        cubicTo(a.x, a.y, b.x, b.y, e.x, e.y)
    }

    val bold = Stroke(width = strokeW, cap = StrokeCap.Round, join = StrokeJoin.Round)
    val thin = Stroke(width = strokeW * 0.5f, cap = StrokeCap.Round, join = StrokeJoin.Round)

    //YEŞİL DOLGULAR
    val fills = Path().apply {
        // büyük çiçeğin altındaki yaprak
        m(182f, 222f)
        c(195f, 200f, 225f, 186f, 262f, 186f)
        c(300f, 186f, 335f, 196f, 352f, 208f)
        c(320f, 218f, 290f, 222f, 265f, 236f)
        c(240f, 240f, 205f, 232f, 182f, 222f)
        close()

        // büyük çiçeğin üst yaprağı
        m(243f, 130f)
        c(265f, 140f, 290f, 160f, 296f, 188f)
        c(275f, 190f, 262f, 180f, 255f, 165f)
        c(250f, 150f, 245f, 140f, 243f, 130f)
        close()

        // küçük çiçek sol yaprak
        m(306f, 248f)
        c(340f, 255f, 362f, 292f, 360f, 330f)
        c(330f, 322f, 304f, 290f, 306f, 248f)
        close()

        // küçük çiçek alt yaprak
        m(366f, 334f)
        c(385f, 305f, 415f, 290f, 440f, 284f)
        c(432f, 305f, 405f, 330f, 372f, 342f)
        c(368f, 340f, 366f, 337f, 366f, 334f)
        close()
    }
    drawPath(fills, leafColor)

    // ---------- GÖVDE + ALT İLMEK ----------
    val stem = Path().apply {
        m(265f, 212f)
        c(250f, 255f, 256f, 320f, 316f, 410f)       // gövde iner, ilmeğin kesişimine
        c(332f, 435f, 343f, 480f, 338f, 520f)       // ilmeğin sağ kenarı
        c(334f, 552f, 298f, 562f, 297f, 520f)       // ilmeğin altı
        c(296f, 480f, 302f, 440f, 316f, 414f)       // ilmeğin sol kenarı
        c(330f, 385f, 346f, 352f, 366f, 328f)       // küçük çiçeğe doğru ince gövde
    }
    drawPath(stem, color, style = thin)

    // ---------- SOL UZUN YAPRAK ----------
    val leaf = Path().apply {
        m(142f, 265f)
        c(175f, 300f, 215f, 350f, 312f, 410f)       // dış kenar
        m(142f, 265f)
        c(150f, 322f, 190f, 352f, 260f, 362f)       // alt kenar
        c(285f, 368f, 302f, 386f, 316f, 408f)
    }
    drawPath(leaf, color, style = bold)

    val leafVein = Path().apply {
        m(200f, 335f)
        c(235f, 358f, 278f, 380f, 312f, 405f)
    }
    drawPath(leafVein, color, style = thin)

    // ---------- BÜYÜK ÇİÇEK ----------
    val bigFlower = Path().apply {
        // üst büyük taç yaprak
        m(272f, 190f)
        c(235f, 150f, 205f, 100f, 240f, 94f)
        c(280f, 86f, 330f, 100f, 328f, 132f)
        c(326f, 160f, 295f, 180f, 272f, 190f)

        // sağ yuvarlak taç yaprak
        m(272f, 192f)
        c(300f, 150f, 345f, 120f, 362f, 148f)
        c(376f, 175f, 350f, 205f, 290f, 207f)
        c(280f, 207f, 274f, 200f, 272f, 192f)

        // sol taç yaprak
        m(272f, 190f)
        c(240f, 152f, 190f, 125f, 181f, 155f)
        c(175f, 185f, 240f, 195f, 272f, 190f)

        // sol alt taç yaprak
        m(262f, 215f)
        c(235f, 170f, 190f, 160f, 193f, 185f)
        c(196f, 205f, 235f, 216f, 262f, 232f)

        // aşağı bakan küçük taç yaprak
        m(262f, 200f)
        c(280f, 205f, 298f, 225f, 290f, 238f)
        c(280f, 242f, 265f, 225f, 262f, 200f)
    }
    drawPath(bigFlower, color, style = bold)

    // ince, uzun iç taç yaprak
    val innerPetal = Path().apply {
        m(272f, 190f)
        c(240f, 150f, 210f, 112f, 216f, 96f)
        c(240f, 110f, 262f, 150f, 272f, 190f)
    }
    drawPath(innerPetal, color, style = thin)

    // ---------- KÜÇÜK ÇİÇEK ----------
    val smallFlower = Path().apply {
        // sol sivri yaprak
        m(307f, 246f)
        c(345f, 250f, 365f, 290f, 361f, 330f)
        c(335f, 322f, 305f, 290f, 307f, 246f)

        // dalgalı üst kenar
        m(328f, 240f)
        c(345f, 225f, 375f, 230f, 378f, 255f)
        c(388f, 238f, 415f, 245f, 415f, 268f)
        c(430f, 258f, 455f, 265f, 453f, 288f)

        // sağ alt yaprağın sivri ucu
        m(468f, 302f)
        c(430f, 298f, 392f, 302f, 365f, 332f)
        m(468f, 302f)
        c(462f, 296f, 458f, 292f, 453f, 288f)
    }
    drawPath(smallFlower, color, style = bold)
}



/**
 * Orijinal görselin piksel koordinatlarını (x0, y0 başlangıçlı) ekrana ölçekler.
 * m = moveTo, c = cubicTo, q = quadraticTo (hepsi görsel koordinatıyla yazılır)
 */
private class RefBox(
    val topLeft: Offset,
    val scale: Float,
    val x0: Float,
    val y0: Float
) {
    fun p(x: Float, y: Float) = Offset(
        topLeft.x + (x - x0) * scale,
        topLeft.y + (y - y0) * scale
    )

    fun Path.m(x: Float, y: Float) {
        val a = p(x, y); moveTo(a.x, a.y)
    }

    fun Path.c(x1: Float, y1: Float, x2: Float, y2: Float, x: Float, y: Float) {
        val a = p(x1, y1); val b = p(x2, y2); val e = p(x, y)
        cubicTo(a.x, a.y, b.x, b.y, e.x, e.y)
    }

    fun Path.q(x1: Float, y1: Float, x: Float, y: Float) {
        val a = p(x1, y1); val e = p(x, y)
        quadraticTo(a.x, a.y, e.x, e.y)
    }
}

private fun lerp(a: Float, b: Float, t: Float) = a + (b - a) * t

/* ------------------------------------------------------------------ */
/* 1) LALE – tek çizgi                                                 */
/* ------------------------------------------------------------------ */
fun DrawScope.drawTulipLine(
    topLeft: Offset,
    height: Float,
    strokeW: Float,
    color: Color = Color(0xFF1A1A1A),
    leafColor: Color? = Color(0xFFB7C4A5)
) {
    // Görsel alanı: x 160..365, y 120..540
    val box = RefBox(topLeft, height / 420f, 160f, 120f)
    val line = Stroke(strokeW, cap = StrokeCap.Round, join = StrokeJoin.Round)

    with(box) {
        // Yaprak dolgusu (isteğe bağlı)
        if (leafColor != null) {
            val fill = Path().apply {
                // sağ yaprak
                m(365f, 250f)
                c(310f, 260f, 262f, 300f, 245f, 345f)
                c(250f, 400f, 258f, 430f, 262f, 450f)
                c(275f, 435f, 290f, 420f, 300f, 390f)
                c(320f, 340f, 320f, 290f, 365f, 250f)
                close()
                // sol yaprak
                m(162f, 277f)
                c(180f, 285f, 220f, 340f, 224f, 400f)
                c(226f, 440f, 212f, 480f, 208f, 500f)
                c(185f, 440f, 170f, 340f, 162f, 277f)
                close()
            }
            drawPath(fill, leafColor.copy(alpha = 0.55f))
        }

        // Çiçek başı
        val head = Path().apply {
            // sol dış kenar + alt
            m(172f, 130f)
            c(160f, 150f, 165f, 210f, 200f, 238f)
            c(215f, 248f, 242f, 246f, 247f, 224f)
            // sağ taç yaprağı yukarı
            c(252f, 200f, 240f, 150f, 219f, 125f)
            // sağ taç iç kenarı
            c(212f, 160f, 218f, 205f, 230f, 238f)
            // sol taç üst kenarı
            m(172f, 130f)
            c(192f, 124f, 218f, 155f, 234f, 195f)
            c(241f, 214f, 241f, 230f, 230f, 238f)
        }
        drawPath(head, color, style = line)

        // Gövde
        val stem = Path().apply {
            m(232f, 240f)
            c(250f, 272f, 252f, 335f, 240f, 400f)
            c(232f, 440f, 218f, 478f, 208f, 502f)
        }
        drawPath(stem, color, style = line)

        // Sol yaprak
        val leftLeaf = Path().apply {
            m(162f, 277f)
            c(180f, 285f, 220f, 340f, 224f, 400f)
            c(226f, 440f, 212f, 480f, 208f, 500f)
            m(162f, 277f)
            c(172f, 340f, 168f, 450f, 190f, 528f)
        }
        drawPath(leftLeaf, color, style = line)

        // Sağ yaprak
        val rightLeaf = Path().apply {
            m(365f, 250f)
            c(310f, 260f, 262f, 300f, 245f, 345f)
            c(238f, 385f, 225f, 455f, 190f, 528f)
            m(365f, 250f)
            c(320f, 290f, 320f, 340f, 300f, 390f)
            c(290f, 420f, 275f, 435f, 262f, 450f)
        }
        drawPath(rightLeaf, color, style = line)
    }
}

/* ------------------------------------------------------------------ */
/* 2) DALGALI ÇİZGİ + İLMEK + KÜÇÜK ÇİÇEK                              */
/* ------------------------------------------------------------------ */
fun DrawScope.drawLoopFlowerWave(
    topLeft: Offset,
    height: Float,
    strokeW: Float,
    color: Color = Color(0xFF1A1A1A),
    petalFill: Color? = Color(0xFFCFDABB)
) {
    // Görsel alanı: x 0..590, y 240..475
    val k = height / 235f
    val box = RefBox(topLeft, k, 0f, 240f)
    val line = Stroke(strokeW, cap = StrokeCap.Round, join = StrokeJoin.Round)

    with(box) {
        // Dalgalı çizgi + ilmek
        val wave = Path().apply {
            m(0f, 393f)
            c(60f, 390f, 110f, 440f, 170f, 432f)
            c(230f, 424f, 262f, 380f, 305f, 378f)
            // ilmek
            c(338f, 375f, 342f, 418f, 318f, 424f)
            c(294f, 430f, 292f, 386f, 335f, 372f)
            c(400f, 358f, 460f, 385f, 500f, 410f)
            // alt kıvrım
            c(470f, 428f, 440f, 445f, 441f, 471f)
        }
        drawPath(wave, color, style = line)

        // Çiçeğe çıkan sap
        val stem = Path().apply {
            m(493f, 298f)
            c(530f, 312f, 548f, 350f, 535f, 388f)
            c(525f, 400f, 510f, 406f, 500f, 410f)
        }
        drawPath(stem, color, style = line)

        // Küçük sivri yaprak
        val smallLeaf = Path().apply {
            m(545f, 385f)
            c(538f, 360f, 548f, 340f, 566f, 334f)
            c(572f, 356f, 566f, 376f, 545f, 385f)
        }
        // Alt yuvarlak yaprak
        val lowLeaf = Path().apply {
            m(500f, 410f)
            c(522f, 372f, 582f, 366f, 576f, 386f)
            c(570f, 406f, 532f, 404f, 500f, 410f)
        }
        if (petalFill != null) {
            drawPath(smallLeaf, petalFill.copy(alpha = 0.6f))
            drawPath(lowLeaf, petalFill.copy(alpha = 0.6f))
        }
        drawPath(smallLeaf, color, style = line)
        drawPath(lowLeaf, color, style = line)

        // Çiçek: merkezden çıkan 7 yaprak
        val center = p(493f, 297f)
        fun petal(angleDeg: Float, len: Float) {
            val a = angleDeg * (PI / 180.0).toFloat()
            val dir = Offset(cos(a), sin(a))
            val nrm = Offset(-dir.y, dir.x)
            val l = len * k
            val w = l * 0.32f
            val tip = center + dir * l
            val path = Path().apply {
                moveTo(center.x, center.y)
                val c1 = center + dir * (l * 0.25f) + nrm * w
                val c2 = center + dir * (l * 1.08f) + nrm * (w * 1.1f)
                cubicTo(c1.x, c1.y, c2.x, c2.y, tip.x, tip.y)
                val c3 = center + dir * (l * 1.08f) - nrm * (w * 1.1f)
                val c4 = center + dir * (l * 0.25f) - nrm * w
                cubicTo(c3.x, c3.y, c4.x, c4.y, center.x, center.y)
            }
            if (petalFill != null) drawPath(path, petalFill.copy(alpha = 0.35f))
            drawPath(path, color, style = Stroke(strokeW * 0.8f, cap = StrokeCap.Round, join = StrokeJoin.Round))
        }
        petal(-72f, 55f)
        petal(-134f, 46f)
        petal(176f, 48f)
        petal(129f, 48f)
        petal(105f, 44f)
        petal(-13f, 54f)
        petal(12f, 32f)
    }
}

/* ------------------------------------------------------------------ */
/* 3) SULU BOYA YAPRAKLI DAL                                           */
/* ------------------------------------------------------------------ */
fun DrawScope.drawWatercolorBranch(
    topLeft: Offset,
    height: Float,
    strokeW: Float,
    color: Color = Color(0xFF4F6B4A),
    fillColor: Color = Color(0xFF7F9A7A)
) {
    // Görsel alanı: x 210..545, y 290..970
    val box = RefBox(topLeft, height / 680f, 210f, 290f)
    val line = Stroke(strokeW, cap = StrokeCap.Round, join = StrokeJoin.Round)

    with(box) {
        /**
         * Yaprak: base -> (c1) -> tip -> (c2) -> base.
         * Sulu boya dolgusu çizgiden biraz kaymış ve daha dar çizilir.
         */
        fun leaf(
            bx: Float, by: Float, tx: Float, ty: Float,
            c1x: Float, c1y: Float, c2x: Float, c2y: Float,
            fillDx: Float = 0f, fillDy: Float = 8f
        ) {
            // sulu boya dolgusu: iki katman
            val f1 = Path().apply {
                m(bx, by)
                q(lerp(c1x, c2x, 0.22f), lerp(c1y, c2y, 0.22f), tx + fillDx, ty + fillDy)
                q(lerp(c2x, c1x, 0.22f), lerp(c2y, c1y, 0.22f), bx, by)
                close()
            }
            drawPath(f1, fillColor.copy(alpha = 0.45f))
            val f2 = Path().apply {
                m(bx, by)
                q(lerp(c1x, c2x, 0.40f), lerp(c1y, c2y, 0.40f), tx + fillDx * 0.8f, ty + fillDy * 1.6f)
                q(lerp(c2x, c1x, 0.40f), lerp(c2y, c1y, 0.40f), bx, by)
                close()
            }
            drawPath(f2, fillColor.copy(alpha = 0.30f))

            // koyu yeşil kontur
            val outline = Path().apply {
                m(bx, by)
                q(c1x, c1y, tx, ty)
                q(c2x, c2y, bx, by)
                close()
            }
            drawPath(outline, color, style = line)
        }

        // Yapraklar (alttan üste)
        leaf(380f, 885f, 212f, 687f, 340f, 660f, 270f, 820f, fillDx = 28f, fillDy = -27f) // sol alt
        leaf(385f, 890f, 513f, 718f, 411f, 716f, 531f, 796f, fillDx = 8f, fillDy = 25f)   // sağ alt
        leaf(400f, 740f, 488f, 468f, 365f, 590f, 520f, 610f, fillDx = 50f, fillDy = 18f)  // sağ üst
        leaf(375f, 670f, 282f, 420f, 220f, 520f, 350f, 540f)                               // sol üst
        leaf(380f, 660f, 418f, 292f, 295f, 480f, 455f, 470f)                               // tepe

        // Gövde
        val stem = Path().apply {
            m(382f, 972f)
            c(380f, 940f, 376f, 912f, 382f, 885f)
            c(392f, 830f, 400f, 790f, 400f, 740f)
            c(398f, 710f, 388f, 685f, 378f, 662f)
        }
        drawPath(stem, color, style = line)
    }
}