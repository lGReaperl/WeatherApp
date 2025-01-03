package com.example.weatherapp.domain

import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.network.ApiService
import retrofit2.awaitResponse
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val apiService: ApiService
) {
    suspend operator fun invoke(lat: Double, lon: Double, apiKey: String): Result<WeatherResponse>{
        return try {
            val response = apiService.getWeather(lat,lon,apiKey).awaitResponse()
            if (response.isSuccessful){
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}