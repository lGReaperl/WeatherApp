package com.example.weatherapp.ui.screens.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.domain.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor (
    private val getWeather: GetWeatherUseCase
) : ViewModel() {


    // Состояние данных о погоде
    private val _weatherState = MutableStateFlow<WeatherResponse?>(null)
    val weatherState: StateFlow<WeatherResponse?> = _weatherState.asStateFlow()

    // Флаг загрузки
    var isLoading = MutableLiveData(false)


    init {
        fetchWeather() // Выполняется только при создании ViewModel
    }

    // Загрузка данных о погоде
    private fun fetchWeather() {
        viewModelScope.launch {

            isLoading.value = true
            try {
                val response = getWeather(
                    lat = 48.450001,
                    lon = 34.983334,
                    apiKey = "3049e035d8a775848d5be823e93531f8"
                )

                response.onSuccess { data ->
                    _weatherState.value = data
                }.onFailure { error ->
                    _weatherState.value = null // Логируем или обрабатываем ошибку
                    Log.e("com.example.weatherapp.ui.screens.weather.WeatherViewModel", "Error fetching weather", error)
                }

            } finally {
                isLoading.value = false
            }
        }
    }
}
