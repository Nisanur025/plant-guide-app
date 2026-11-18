package com.example.plantguide.navigation

sealed class Screen(val route:String) {
    object HomeScreen: Screen("home_screen")
    object MyPlantsScreen: Screen("my_plants_screen")
    object AssistantScreen : Screen("assistant")
    object CalendarScreen : Screen("calendar_screen")
    object ProfileScreen : Screen("profile_screen")
}