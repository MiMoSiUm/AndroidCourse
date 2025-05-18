package com.example.androidcourse.data

import com.example.androidcourse.network.WeatherApi
import kotlin.math.round
import kotlin.math.roundToInt

interface WeatherRepository {
    suspend fun getWeather(city: String): WeatherMapped
}

class NetworkWeatherRepository : WeatherRepository {
    override suspend fun getWeather(city: String): WeatherMapped {
        val result = WeatherApi.retrofitService.getWeather(city = city)
        return WeatherMapped(
            city = city,
            currentDayInfo = CurrentDayInfo(
                currentTemp = result.current?.tempC!!.roundToInt().toString() + "℃",
                maxTemp = result.forecast?.forecastday[0]?.day?.maxtempC!!.roundToInt().toString() + "℃",
                minTemp = result.forecast?.forecastday[0]?.day?.mintempC!!.roundToInt().toString() + "℃",
                feelsLike = result.current?.feelslikeC!!.roundToInt().toString() + "℃",
                icon = result.forecast?.forecastday[0]?.day?.condition?.icon!!
            ),
            hourly = result.forecast?.forecastday[0]?.hour!!.map { hour ->
                HourInfo(
                    temp = round(hour.tempC!!).roundToInt().toString() + "℃",
                    time = hour.time!!.split(" ")[1],
                    icon = hour.condition?.icon.toString()
                )
            },
            futureDaysTemps = result.forecast?.forecastday!!.map { forecastDay ->
                FutureDayTemps(
                    date = forecastDay.date!!.split("-").takeLast(2).reversed().joinToString("."),
                    maxTemp =  round(forecastDay.day?.mintempC!!).roundToInt().toString() + "℃",
                    minTemp =  round(forecastDay.day?.maxtempC!!).roundToInt().toString() + "℃",
                    icon = forecastDay.day?.condition?.icon!!
                )
            }
        )
    }
}