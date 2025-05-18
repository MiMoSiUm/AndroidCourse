package com.example.androidcourse.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.androidcourse.R
import com.example.androidcourse.data.CurrentDayInfo

@Composable
fun TodayWeatherCard(
    currentDayInfo: CurrentDayInfo,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .height(100.dp)
                .align(Alignment.Center),
            elevation = CardDefaults.cardElevation(5.dp)
        )
        {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    AsyncImage(
                        model = "https:" + currentDayInfo.icon,
                        contentDescription = "Weather Icon",
                        modifier = Modifier
                            .size(40.dp)
                    )
                    currentDayInfo.currentTemp?.let {
                        Text(
                            text = currentDayInfo.currentTemp,
                            fontSize = 40.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.feels_like) + " " + currentDayInfo.feelsLike
                    )
                    Text(
                        text = stringResource(R.string.maximum) + " " + currentDayInfo.maxTemp
                    )
                    Text(
                        text = stringResource(R.string.minimum) + " " + currentDayInfo.minTemp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TodayWeatherCardPreview() {
    TodayWeatherCard(
        CurrentDayInfo(
            "10℃",
            "15℃",
            "5℃",
            "8℃"
        )
    )
}