package com.example.weatherapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,               // Название города
    val temp: Double,               // Температура
    val description: String,        // Описание погоды
    val icon: String,               // Иконка погоды
    val pressure: Int,              // Давление
    val humidity: Int,              // Влажность
    val windSpeed: Double,          // Скорость ветра
    val sunrise: Long,              // Время восхода
    val sunset: Long,               // Время заката
    val updatedAt: Long             // Время обновления данных
)