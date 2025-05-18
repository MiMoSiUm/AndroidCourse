package com.example.androidcourse.data

interface AppContainer {
    val weatherRepository: WeatherRepository
}

class DefaultAppContainer : AppContainer {
    override val weatherRepository: WeatherRepository by lazy {
        NetworkWeatherRepository()
    }
}