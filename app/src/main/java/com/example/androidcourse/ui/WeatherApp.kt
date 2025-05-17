package com.example.androidcourse.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidcourse.ui.screens.HomeScreen
import com.example.androidcourse.ui.screens.MainSearchBar
import com.example.androidcourse.ui.screens.SearchWidgetState
import com.example.androidcourse.ui.screens.WeatherUiState
import com.example.androidcourse.ui.screens.WeatherViewModel

@Composable
fun WeatherApp(modifier: Modifier = Modifier) {
    val weatherViewModel: WeatherViewModel = viewModel()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MainSearchBar(
                searchWidgetState = weatherViewModel.searchWidgetState,
                searchTextState = weatherViewModel.searchTextState,
                onTextChange = { weatherViewModel.searchTextState = it },
                onSearchClicked = {
                    weatherViewModel.getWeatherData(it)
                    weatherViewModel.searchWidgetState = SearchWidgetState.CLOSED },
                onCloseClicked = { weatherViewModel.searchWidgetState = SearchWidgetState.CLOSED },
                onSearchTriggered = { weatherViewModel.searchWidgetState = SearchWidgetState.OPENED }
            )
        }
        ) { innerPadding ->
        when (weatherViewModel.weatherUiState) {
            is WeatherUiState.Success -> {
                HomeScreen(
                    weatherMapped = (weatherViewModel.weatherUiState as WeatherUiState.Success).weatherMapped,
                    modifier = modifier.padding(innerPadding)
                )
            }
            is WeatherUiState.Loading -> {
                Text(
                    text = "Loading",
                    modifier = modifier.padding(innerPadding)
                )
            }
            is WeatherUiState.Error -> {
                Text(
                    text = "Error",
                    modifier = modifier.padding(innerPadding)
                )
            }
        }
    }
}