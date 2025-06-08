package com.example.myweather.presentation.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.myweather.R
import com.example.myweather.presentation.common.theme.MyWeatherTheme
import com.example.myweather.presentation.common.theme.backgroundGradientBrush
import com.example.myweather.presentation.home.components.CollapsibleWeatherHeader
import com.example.myweather.presentation.home.components.TemperatureInfo
import com.example.myweather.presentation.home.components.WeatherDetailItem
import com.example.myweather.presentation.home.components.WeatherDetailsGrid

@Composable
fun WeatherScreen(gridItems: List<WeatherDetailItem>, modifier: Modifier = Modifier) {
    val lazyListState = rememberLazyListState()

    val firstVisibleItemIndex by remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    var previousFirstVisibleItemIndex by remember { mutableIntStateOf(0) }

    val scrollOffset by remember {
        derivedStateOf {
            if (firstVisibleItemIndex > 0) {
                200f
            } else {
                lazyListState.firstVisibleItemScrollOffset.toFloat().coerceIn(0f, 200f)
            }
        }
    }

    if (firstVisibleItemIndex != previousFirstVisibleItemIndex) {
        previousFirstVisibleItemIndex = firstVisibleItemIndex
    }

    val startColor = MaterialTheme.colorScheme.primary
    val defaultEndColor = MaterialTheme.colorScheme.tertiary
    val targetEndColor = MaterialTheme.colorScheme.tertiaryContainer

    // Calculate animation progress based on scroll position
    val progress = scrollOffset / 200f
    val endColor by animateColorAsState(
        targetValue = lerp(defaultEndColor, targetEndColor, progress),
        label = "backgroundEndColor"
    )

    // Create a custom gradient brush with the animated color
    val animatedBackgroundBrush = verticalGradient(
        colors = listOf(startColor, endColor),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundGradientBrush())
                .statusBarsPadding()

        ) {
            stickyHeader {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(animatedBackgroundBrush)
                ) {
                    CollapsibleWeatherHeader(
                        scrollOffset = scrollOffset,
                        address = "Baghdad",
                        weatherIconRes = R.drawable.img_partly_cloudy_light,
                        collapsedThreshold = 200f,
                        temperatureContent = {
                            TemperatureInfo(
                                temperature = "24°C",
                                weatherDescription = "Partly cloudy",
                                heightTemp = "32°C",
                                lowTemp = "20°C"
                            )
                        }
                    )
                }
            }

            item {
                WeatherDetailsGrid(items = gridItems, modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp))
            }

            items(100) {
                Text(
                    text = "Item $it",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun WeatherScreenPreview() {
    MyWeatherTheme {
        val sampleItems = listOf(
            WeatherDetailItem(R.drawable.ic_wind, "13 KM/h", "Wind"),
            WeatherDetailItem(R.drawable.ic_humidity, "24%", "Humidity"),
            WeatherDetailItem(R.drawable.ic_rain, "2%", "Rain"),
            WeatherDetailItem(R.drawable.ic_uv, "2", "UV Index"),
            WeatherDetailItem(R.drawable.ic_pressure, "1012 hPa", "Pressure"),
            WeatherDetailItem(R.drawable.ic_temp, "22°C", "Feels like")
        )

        WeatherScreen(gridItems = sampleItems)
    }
}
