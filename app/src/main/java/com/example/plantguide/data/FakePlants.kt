package com.example.plantguide.data

import com.example.plantguide.R
import com.example.plantguide.model.Plant
import com.example.plantguide.model.PlantStatus

val FakePlants = listOf(
    Plant("1", "Gül", "İç Mekan", PlantStatus.HEALTHY, 0.5f, R.drawable.plant_rose),
    Plant("2", "Limon Ağacı", "Dış Mekan", PlantStatus.NEEDS_WATER, 0.5f, R.drawable.plant_lemon),
    Plant("3", "Aloe Vera", "İç Mekan", PlantStatus.HEALTHY, 0.5f, R.drawable.plant_aloe),
    Plant("4", "Devetabanı", "İç Mekan", PlantStatus.HEALTHY, 0.5f, R.drawable.plant_monstera),
)