package com.example.weatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Инициализация SDK или логирования
        // Например, Firebase или Timber
    }
}