package com.example.androidcourse.data

data class WeatherMapped(
    val city: String?,
    val currentDayInfo: CurrentDayInfo?,
    val hourly: List<HourInfo>?,
    val tmrHourly: List<HourInfo>?,
    val futureDaysTemps: List<FutureDayTemps>?
)

data class CurrentDayInfo(
    val currentTemp: String?,
    val maxTemp: String?,
    val minTemp: String?,
    val feelsLike: String?,
    val icon: String? = ""
)

data class FutureDayTemps(
    val date: String?,
    val maxTemp: String?,
    val minTemp: String?,
    val icon: String? = ""
)

data class HourInfo(
    val temp: String?,
    val time: String?,
    val icon: String? = ""
)