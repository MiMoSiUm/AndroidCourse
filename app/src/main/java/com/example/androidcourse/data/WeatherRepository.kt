package com.example.androidcourse.data

import com.example.androidcourse.network.WeatherApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.transform
import kotlin.math.round
import kotlin.math.roundToInt

interface WeatherRepository {
    suspend fun getWeather(city: String): WeatherMapped
}

class NetworkWeatherRepository : WeatherRepository {
    override suspend fun getWeather(city: String): WeatherMapped {
        var hourCount = 0
        val result = WeatherApi.retrofitService.getWeather(city = city)
        return WeatherMapped(
            city = city,
            currentDayInfo = CurrentDayInfo(
                currentTemp = result.current?.tempC?.roundToInt().toString() + "℃",
                maxTemp = result.forecast?.forecastday[0]?.day?.maxtempC?.roundToInt().toString() + "℃",
                minTemp = result.forecast?.forecastday[0]?.day?.mintempC?.roundToInt().toString() + "℃",
                feelsLike = result.current?.feelslikeC?.roundToInt().toString() + "℃",
                icon = result.forecast?.forecastday[0]?.day?.condition?.icon
            ),
            hourly = result.forecast?.forecastday[0]?.hour?.asFlow()?.transform { hour ->
                if (hourCount < 24) {
                    if (hour.time!!.split(" ")[1].split(":")[0] >= result.location!!.localtime!!.split(" ")[1].split(":")[0]) {
                        ++hourCount
                        emit(HourInfo(
                                temp = hour.tempC?.roundToInt().toString() + "℃",
                                time = hour.time?.split(" ")[1],
                                icon = hour.condition?.icon.toString()
                            ))
                    }
                }
            }?.toList(),
            tmrHourly = result.forecast?.forecastday[1]?.hour?.asFlow()?.transform { hour ->
                if (hourCount < 24) {
                    ++hourCount
                    emit(HourInfo(
                        temp = hour.tempC?.roundToInt().toString() + "℃",
                        time = hour.time?.split(" ")[1],
                        icon = hour.condition?.icon.toString()
                    ))
                }
            }?.toList(),
            futureDaysTemps = result.forecast?.forecastday?.map { forecastDay ->
                FutureDayTemps(
                    date = forecastDay.date?.split("-")?.takeLast(2)?.reversed()?.joinToString("."),
                    maxTemp =  forecastDay.day?.mintempC?.roundToInt().toString() + "℃",
                    minTemp =  forecastDay.day?.maxtempC?.roundToInt().toString() + "℃",
                    icon = forecastDay.day?.condition?.icon
                )
            }
        )
    }
}