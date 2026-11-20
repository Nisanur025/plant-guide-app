package com.example.plantguide.model

data class Plant(
    val id: String,
    val name: String,
    val location: String,
    val status: PlantStatus,
    val waterLevel: Float,
    val imageRes: Int
)
enum class PlantStatus(val label: String) {
    HEALTHY("Sağlıklı"),
    NEEDS_WATER("Bugün Sula"),
    NEEDS_FERTILIZER("Gübreleme Yakın")
}