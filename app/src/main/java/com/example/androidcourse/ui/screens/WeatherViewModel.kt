package com.example.androidcourse.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.network.HttpException
import com.example.androidcourse.data.CurrentDayInfo
import com.example.androidcourse.data.FutureDayTemps
import com.example.androidcourse.data.HourInfo
import com.example.androidcourse.data.WeatherMapped
import com.example.androidcourse.network.WeatherApi
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.math.round
import kotlin.math.roundToInt

sealed interface WeatherUiState {
    data class Success(val weatherMapped: WeatherMapped) : WeatherUiState
    object Loading : WeatherUiState
    object Error : WeatherUiState
}

class WeatherViewModel : ViewModel() {
    var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    var searchWidgetState by mutableStateOf(SearchWidgetState.CLOSED)

    var searchTextState by mutableStateOf(value = "")

    init {
        getWeatherData("Izhevsk")
    }

    fun getWeatherData(city: String) {
        viewModelScope.launch {
            weatherUiState =
            try {
                val result = WeatherApi.retrofitService.getWeather(city = city)
                WeatherUiState.Success(
                    WeatherMapped(
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
                )
            } catch (e: IOException) {
                WeatherUiState.Error
            } catch (e: retrofit2.HttpException) {
                WeatherUiState.Error
            }
        }
    }
}

enum class SearchWidgetState {
    OPENED,
    CLOSED
}