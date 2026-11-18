package com.example.plantguide.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.plantguide.R


// Başka dosyalardan da kullanılabilsin diye dışarıda tanımlı
val CharacterHeight = 170.dp
val CharacterOverlap = 46.dp   // karakterin kartın içine gömüldüğü miktar

@Composable
fun HomeCharacterSection(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(CharacterHeight)
            .padding(horizontal = 16.dp)
    ) {
        SpeechBubble(
            text = "Bugün bahçemizde neler oluyor, bakalım mı?",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 0.dp, end = 135.dp)
                .width(160.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.character_girl),
            contentDescription = "Karakter",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 24.dp)
                .width(120.dp)
                .fillMaxHeight()
        )
    }
}