package com.example.plantguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.plantguide.ui.screens.MainScreen
import com.example.plantguide.ui.theme.PlantGuideTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() //durum çubuğunu
        setContent {
            PlantGuideTheme {
                MainScreen()
            }
        }
    }
}