package com.example.androidcourse.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcourse.data.WeatherMapped

@Composable
fun HomeScreen(
    weatherMapped: WeatherMapped,
    modifier: Modifier = Modifier
) {
    Column(
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
}