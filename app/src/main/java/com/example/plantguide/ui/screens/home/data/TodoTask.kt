package com.example.plantguide.ui.screens.home.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TodoType {
    @SerialName("acil") ACIL,
    @SerialName("hatirlatma") HATIRLATMA,
    @SerialName("yaklasan") YAKLASAN,
    @SerialName("sulama") SULAMA,
    @SerialName("gubreleme")GUBRE,
    @SerialName("budama") BUDAMA
}

@Serializable
data class TodoTask(
    val id : Int,
    val title :String,
    val subtitle : String = "",
    val type : TodoType,
    @SerialName("image_url") val imageUrl : String? = null,
    @SerialName("is_done") val isDone : Boolean = false

)
