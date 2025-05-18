package com.example.androidcourse.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil3.network.HttpException
import com.example.androidcourse.WeatherApplication
import com.example.androidcourse.data.CurrentDayInfo
import com.example.androidcourse.data.FutureDayTemps
import com.example.androidcourse.data.HourInfo
import com.example.androidcourse.data.WeatherMapped
import com.example.androidcourse.data.WeatherRepository
import com.example.androidcourse.network.WeatherApi
import com.example.androidcourse.network.model.City
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.math.round
import kotlin.math.roundToInt

sealed interface WeatherUiState {
    data class Success(val weatherMapped: WeatherMapped) : WeatherUiState
    object Loading : WeatherUiState
    object Error : WeatherUiState
}

sealed interface CitiesUiState {
    data class Success(val cities: List<City>) : CitiesUiState
    object Loading : CitiesUiState
    object Error : CitiesUiState
}

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WeatherApplication)
                val weatherRepository = application.container.weatherRepository
                WeatherViewModel(weatherRepository = weatherRepository)
            }
        }
    }

    var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    var citiesUiState: CitiesUiState by mutableStateOf(CitiesUiState.Loading)

    var searchWidgetState by mutableStateOf(SearchWidgetState.CLOSED)

    var searchTextState by mutableStateOf(value = "")

    init {
        getWeatherData("Izhevsk")
    }

    fun getWeatherData(city: String) {
        viewModelScope.launch {
            weatherUiState =
            try {
                WeatherUiState.Success(
                    weatherRepository.getWeather(city = city)
                )
            } catch (e: IOException) {
                WeatherUiState.Error
            } catch (e: retrofit2.HttpException) {
                WeatherUiState.Error
            }
        }
    }

    fun getCities(city: String) {
        viewModelScope.launch {
            citiesUiState =
                try {
                    CitiesUiState.Success(
                        weatherRepository.getCities(city = city)
                    )
                } catch (e: IOException) {
                    CitiesUiState.Error
                } catch (e: retrofit2.HttpException) {
                    CitiesUiState.Error
                }
        }
    }
}

enum class SearchWidgetState {
    OPENED,
    CLOSED
}