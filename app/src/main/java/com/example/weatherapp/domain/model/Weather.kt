package com.example.weatherapp.domain.model

data class Weather(
    val id: Int,
    val name: String,
    val temp: Double,
    val description: String,
    val icon: String,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double,
    val sunrise: Long,
    val sunset: Long,
    val updatedAt: Long
)