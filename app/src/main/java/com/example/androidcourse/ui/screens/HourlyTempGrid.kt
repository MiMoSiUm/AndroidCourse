package com.example.androidcourse.ui.screens

import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.androidcourse.data.FutureDayTemps
import com.example.androidcourse.data.HourInfo
import com.example.androidcourse.data.WeatherMapped
import java.text.DateFormat
import java.time.LocalTime

@Composable
fun HourlyTempGrid(
    hourly: List<HourInfo>,
    tmrHourly: List<HourInfo>,
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Adaptive(100.dp),
        modifier = modifier.requiredHeight(120.dp)

    ) {
        itemsIndexed(hourly) { _, hourInfo ->
            hourInfo.time?.let {
                HourlyTempCard(hourInfo = hourInfo)
            }
        }
        if (tmrHourly.isNotEmpty()) {
            itemsIndexed(listOf("dummy")) { _, dummy ->
                VerticalDivider(
                    thickness = 2.dp,
                    modifier = Modifier.padding(2.dp)
                )
            }
            itemsIndexed(tmrHourly) { _, hourInfo ->
                hourInfo.time?.let {
                    HourlyTempCard(hourInfo = hourInfo)
                }
            }
        }
    }
}

@Composable
fun HourlyTempCard(
    hourInfo: HourInfo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .requiredWidth(60.dp)
            .height(100.dp)
            .padding(3.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            hourInfo.temp?.let {
                Text(
                    text = hourInfo.temp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(Alignment.CenterVertically)
                )
            }
            AsyncImage(
                model = "https:" + hourInfo.icon,
                contentDescription = "Weather Icon",
                modifier = Modifier
                    .size(30.dp)
                    .weight(1f)
                    .wrapContentHeight(Alignment.CenterVertically)
            )
            hourInfo.time?.let {
                Text(
                    text = hourInfo.time,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Preview
@Composable
fun HourlyTempCardPreview() {
    HourlyTempCard(
        hourInfo = HourInfo(
            "10.0℃",
            "10:00",
            "https://cdn.weatherapi.com/weather/64x64/night/113.png"
        )
    )
}