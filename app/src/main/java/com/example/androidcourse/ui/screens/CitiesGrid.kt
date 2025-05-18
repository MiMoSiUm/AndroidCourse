package com.example.androidcourse.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidcourse.network.model.City

@Composable
fun CitiesGrid(
    cities: List<City>,
    onCityClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(300.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        itemsIndexed(cities) { _, city ->
            CityCard(
                city = city,
                onCityClicked = onCityClicked
            )
        }
    }
}

@Composable
fun CityCard(
    city: City,
    onCityClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(vertical = 3.dp),
        onClick = {
            onCityClicked(city.id!!) },
        elevation = CardDefaults.cardElevation(5.dp)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = city.name + ", " + city.region + ", " + city.country,
                modifier = Modifier
            )
        }
    }
}