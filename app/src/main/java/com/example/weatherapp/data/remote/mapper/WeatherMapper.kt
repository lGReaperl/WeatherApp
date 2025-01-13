package com.example.weatherapp.data.remote.mapper

import com.example.weatherapp.data.local.entity.WeatherEntity
import com.example.weatherapp.data.remote.dto.WeatherResponseDto
import com.example.weatherapp.domain.model.Weather

fun WeatherResponseDto.toDomain(): Weather {
    return Weather(
        id = id,
        name = name,
        temp = main.temp,
        description = weather.firstOrNull()?.description ?: "No description",
        icon = weather.firstOrNull()?.icon ?: "",
        pressure = main.pressure,
        humidity = main.humidity,
        windSpeed = wind.speed,
        sunrise = sys.sunrise,
        sunset = sys.sunset,
        updatedAt = System.currentTimeMillis()
    )
}

fun WeatherResponseDto.toWeatherEntity():WeatherEntity{
    return WeatherEntity(
        id = id,
        name = name,
        temp = main.temp,
        description = weather.firstOrNull()?.description ?: "No description",
        icon = weather.firstOrNull()?.icon ?: "",
        pressure = main.pressure,
        humidity = main.humidity,
        windSpeed = wind.speed,
        sunrise = sys.sunrise,
        sunset = sys.sunset,
        updatedAt = System.currentTimeMillis()
    )
}