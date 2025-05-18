package com.example.androidcourse.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidcourse.ui.screens.ErrorScreen
import com.example.androidcourse.ui.screens.HomeScreen
import com.example.androidcourse.ui.screens.LoadingScreen
import com.example.androidcourse.ui.screens.MainSearchBar
import com.example.androidcourse.ui.screens.SearchWidgetState
import com.example.androidcourse.ui.screens.WeatherUiState
import com.example.androidcourse.ui.screens.WeatherViewModel

@Composable
fun WeatherApp(modifier: Modifier = Modifier) {
    val weatherViewModel: WeatherViewModel = viewModel(factory = WeatherViewModel.Factory)

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MainSearchBar(
                searchWidgetState = weatherViewModel.searchWidgetState,
                searchTextState = weatherViewModel.searchTextState,
                onTextChange = { weatherViewModel.searchTextState = it },
                onSearchClicked = {
                    weatherViewModel.getCities(it)
                },
                onCloseClicked = { weatherViewModel.searchWidgetState = SearchWidgetState.CLOSED },
                onSearchTriggered = { weatherViewModel.searchWidgetState = SearchWidgetState.OPENED }
            )
        }
        ) { innerPadding ->
        when (weatherViewModel.weatherUiState) {
            is WeatherUiState.Success -> {
                HomeScreen(
                    searchWidgetState = weatherViewModel.searchWidgetState,
                    weatherMapped = (weatherViewModel.weatherUiState as WeatherUiState.Success).weatherMapped,
                    citiesUiState = weatherViewModel.citiesUiState,
                    onCityClicked = {
                        weatherViewModel.getWeatherData("id:$it")
                        weatherViewModel.searchWidgetState = SearchWidgetState.CLOSED
                        weatherViewModel.searchTextState = ""
                    },
                    modifier = modifier.padding(innerPadding)
                )
            }
            is WeatherUiState.Loading -> {
                LoadingScreen(modifier = modifier.padding(innerPadding))
            }
            is WeatherUiState.Error -> {
                ErrorScreen(
                    modifier = modifier.padding(innerPadding),
                    retryAction = {
                        weatherViewModel.searchWidgetState = SearchWidgetState.CLOSED
                        weatherViewModel.getWeatherData()
                    }
                )
            }
        }
    }
}