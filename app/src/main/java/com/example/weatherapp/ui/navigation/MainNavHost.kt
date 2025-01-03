package com.example.weatherapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.screens.details.DetailsScreenView
import com.example.weatherapp.ui.screens.home.HomeScreenView
import com.example.weatherapp.ui.screens.weather.WeatherScreenView

@Composable
fun MainNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreenView(navController) }
        composable("weather") { WeatherScreenView(navController) }
        composable("details") { DetailsScreenView(navController) }
    }

}

