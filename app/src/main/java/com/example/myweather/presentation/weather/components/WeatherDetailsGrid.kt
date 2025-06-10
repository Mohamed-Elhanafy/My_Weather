package com.example.myweather.presentation.weather.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myweather.R
import com.example.myweather.presentation.weather.WeatherDetailItem

@Composable
fun WeatherDetailsGrid(
    items: List<WeatherDetailItem>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        val itemsPerRow = 3
        val rows = (items.size + itemsPerRow - 1) / itemsPerRow
        val rowHeight = 128.dp
        val spacing = 6.dp
        val totalHeight = rowHeight * rows + spacing * (rows - 1)

        Box(modifier = Modifier.height(totalHeight)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                userScrollEnabled = false
            ) {
                itemsIndexed(items) { index, item ->
                    WeatherDetailsCard(icon = item.icon, text = item.text, label = item.label)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF87CEFA)
@Composable
private fun WeatherDetailsGridPreview() {
    val sampleItems = listOf(
        WeatherDetailItem(R.drawable.ic_wind, "13 KM/h", "Wind"),
        WeatherDetailItem(R.drawable.ic_humidity, "24%", "Humidity"),
        WeatherDetailItem(R.drawable.ic_rain, "2%", "Rain"),
        WeatherDetailItem(R.drawable.ic_uv, "2", "UV Index"),
        WeatherDetailItem(R.drawable.ic_pressure, "1012 hPa", "Pressure"),
        WeatherDetailItem(R.drawable.ic_temp, "22°C", "Feels like")
    )

    Surface {
        WeatherDetailsGrid(items = sampleItems, modifier = Modifier.padding(16.dp))
    }
}
