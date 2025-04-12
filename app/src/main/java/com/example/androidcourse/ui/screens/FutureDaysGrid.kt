package com.example.androidcourse.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.androidcourse.data.FutureDayTemps
import kotlin.text.replace

@Composable
fun FutureDaysGrid(
    futureDaysTemps: List<FutureDayTemps>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(300.dp),
        modifier = modifier
            .fillMaxWidth()
    ) { itemsIndexed(futureDaysTemps) { _, temps ->
        FutureDayCard(temps)
    } }
}

@Composable
fun FutureDayCard(
    futureDayTemps: FutureDayTemps,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(vertical =  3.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = futureDayTemps.date,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            )
            AsyncImage(
                model = "https:" + futureDayTemps.icon,
                contentDescription = "Weather Icon",
                modifier = Modifier
//                    .weight(1f)
                    .size(30.dp)
                    .padding(horizontal = 2.dp)
            )
            Row(
                modifier = Modifier
                    .requiredWidth(100.dp)
                    .padding(horizontal = 10.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = futureDayTemps.maxTemp!! + "/",
                    modifier = Modifier
                )
                Text(
                    text = futureDayTemps.minTemp!!,
                    modifier = Modifier
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FutureDayCardPreview() {
    FutureDayCard(
        FutureDayTemps(
            "12.04",
            "10",
            "0",
            ""
        )
    )
}