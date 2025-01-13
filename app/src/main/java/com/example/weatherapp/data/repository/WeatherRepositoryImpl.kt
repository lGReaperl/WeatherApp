package com.example.weatherapp.data.repository


import android.util.Log

import com.example.weatherapp.data.local.dao.WeatherDao
import com.example.weatherapp.data.remote.api.WeatherApi
import com.example.weatherapp.data.remote.mapper.toDomain
import com.example.weatherapp.data.remote.mapper.toWeatherEntity
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao // DAO для работы с базой данных
) : WeatherRepository {

    override suspend fun getWeatherFromApiAndSave(lat: Double, lon: Double, apiKey: String): Result<Weather> {
        return withContext(Dispatchers.IO) {
            try {
                val response = weatherApi.getWeather(lat, lon, apiKey)

                // Преобразование через методы расширения
                val weather = response.toDomain()
                val weatherEntity = response.toWeatherEntity()

                // Сохранение в базу данных
                weatherDao.insertWeather(weatherEntity)

                Result.success(weather)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}