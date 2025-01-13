package com.example.weatherapp.ui.screens.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _weatherState = MutableStateFlow<Weather?>(null)
    val weatherState: StateFlow<Weather?> = _weatherState.asStateFlow()

    var isLoading = MutableLiveData(false)
    var errorMessage = MutableLiveData<String?>()

    init {
        fetchWeather()
    }

    private fun fetchWeather() {
        viewModelScope.launch {
            isLoading.value = true
            val result = getWeatherUseCase(48.450001, 34.983334, "3049e035d8a775848d5be823e93531f8")
            if (result.isSuccess) {
                _weatherState.value = result.getOrNull()
            } else {
                errorMessage.value = result.exceptionOrNull()?.message ?: "Unknown error"
            }
            isLoading.value = false
        }
    }
}