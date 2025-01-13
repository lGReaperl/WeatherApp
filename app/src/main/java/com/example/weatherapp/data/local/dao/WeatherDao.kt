package com.example.weatherapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.local.entity.WeatherEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)

    @Query("SELECT * FROM weather WHERE id = :id")
    suspend fun getWeatherById(id: Int): WeatherEntity? // Указан точный тип

    @Query("DELETE FROM weather")
    suspend fun deleteAllWeather() // Указан тип `Unit`, так как этот запрос ничего не возвращает
}