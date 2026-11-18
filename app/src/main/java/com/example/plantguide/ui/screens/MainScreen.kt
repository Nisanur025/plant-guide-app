package com.example.plantguide.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.plantguide.R
import com.example.plantguide.model.NavItem
import com.example.plantguide.navigation.Screen
import com.example.plantguide.ui.components.CustomBottomBar
import com.example.plantguide.ui.screens.home.HomeScreen
import com.example.plantguide.ui.theme.PlantGuideTheme

@Composable
fun MainScreen() {

    val items = listOf(
        NavItem("Ana Sayfa", Screen.HomeScreen.route, R.drawable.ic_home, R.drawable.ic_home),
        NavItem("Bitkilerim", Screen.MyPlantsScreen.route, R.drawable.ic_leaf, R.drawable.ic_leaf),
        NavItem(
            "Takvim",
            Screen.CalendarScreen.route,
            R.drawable.ic_calendar,
            R.drawable.ic_calendar
        ),
        NavItem("Profil", Screen.ProfileScreen.route, R.drawable.ic_person, R.drawable.ic_person)
    )

    val navController = rememberNavController()

    // Şu an hangi sayfadayız
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // Rotaya karşılık gelen sekmenin sırası (hiçbiri değilse -1)
    val selectedIndex = items.indexOfFirst { it.route == currentRoute }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            CustomBottomBar(
                items = items,
                selectedIndex = selectedIndex,
                onItemClick = { index -> navigateToTab(navController, items[index].route) },
                onFabClick = { navigateToTab(navController, Screen.AssistantScreen.route) }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.HomeScreen.route) { HomeScreen() }
            composable(Screen.MyPlantsScreen.route) { MyPlantsScreen() }
            composable(Screen.AssistantScreen.route) { AssistantScreen() }
            composable(Screen.CalendarScreen.route) { CalendarScreen() }
            composable(Screen.ProfileScreen.route) { ProfileScreen() }
        }
    }
}


private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    PlantGuideTheme {
        MainScreen()
    }
}