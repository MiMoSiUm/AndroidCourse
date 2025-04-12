package com.example.androidcourse.data

interface WeatherRepository {
    suspend fun getWeather(city: String): List<WeatherMapped>
}