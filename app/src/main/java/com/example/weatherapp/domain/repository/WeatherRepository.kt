package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeatherFromApiAndSave(lat: Double, lon: Double, apiKey: String): Result<Weather>
}