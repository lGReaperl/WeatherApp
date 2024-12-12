package com.example.weatherapp.useri.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.useri.screens.home.HomeScreenView
import com.example.weatherapp.useri.screens.weather.WeatherScreenView

@Composable
fun MainNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreenView(navController) }
        composable("weather") { WeatherScreenView(navController) }
    }

}

