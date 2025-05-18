package com.example.androidcourse.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcourse.data.WeatherMapped

@Composable
fun HomeScreen(
    searchWidgetState: SearchWidgetState,
    weatherMapped: WeatherMapped,
    citiesUiState: CitiesUiState,
    onCityClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(horizontal = 10.dp)
        ) {
            weatherMapped.city?.let {
                Text(
                    text = weatherMapped.city,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(Alignment.CenterVertically),
                    fontSize = 70.sp,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Center
                )
            }
            weatherMapped.currentDayInfo?.let {
                TodayWeatherCard(
                    currentDayInfo = weatherMapped.currentDayInfo,
                    modifier = Modifier
                        .weight(1f)
                )
            }
            weatherMapped.hourly?.let {
                weatherMapped.tmrHourly?.let {
                    HourlyTempGrid(
                        weatherMapped.hourly,
                        weatherMapped.tmrHourly,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
            weatherMapped.futureDaysTemps?.let {
                FutureDaysGrid(
                    weatherMapped.futureDaysTemps,
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
        SearchWidgetState.OPENED -> {
            when (citiesUiState) {
                is CitiesUiState.Success -> CitiesGrid(
                    citiesUiState.cities,
                    onCityClicked,
                    modifier = modifier
                )
                is CitiesUiState.Error -> {}
                is CitiesUiState.Loading -> {}
            }
        }
    }
}