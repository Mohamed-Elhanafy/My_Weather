package com.example.myweather.presentation.weather

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myweather.presentation.common.theme.MyWeatherTheme
import com.example.myweather.presentation.common.theme.backgroundGradientBrush
import com.example.myweather.presentation.weather.components.CollapsibleWeatherHeader
import com.example.myweather.presentation.weather.components.ForecastRow
import com.example.myweather.presentation.weather.components.TemperatureInfo
import com.example.myweather.presentation.weather.components.WeatherDetailsGrid
import com.example.myweather.presentation.weather.components.WeeklyForecastColumn
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherScreen(viewModel: WeatherVM = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(uiState.error) {
        uiState.error?.let { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    WeatherScreenContent(uiState = uiState)
}

@Composable
private fun WeatherScreenContent(uiState: WeatherUiState) {
    val lazyListState = rememberLazyListState()

    val maxScrollOffset = 200f

    val firstVisibleItemIndex by remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }

    val scrollOffset by remember {
        derivedStateOf {
            if (firstVisibleItemIndex > 0) {
                maxScrollOffset
            } else {
                lazyListState.firstVisibleItemScrollOffset.toFloat().coerceIn(0f, maxScrollOffset)
            }
        }
    }

    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradientBrush())
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            with(uiState) {
                CollapsibleWeatherHeader(
                    scrollOffset = scrollOffset,
                    address = location.name,
                    weatherIconRes = mainWeatherImage,
                    temperatureContent = {
                        TemperatureInfo(
                            temperature = currentTemperature,
                            weatherDescription = stringResource(weatherConditionResource),
                            heightTemp = highTemperature,
                            lowTemp = lowTemperature
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        item {
            WeatherDetailsGrid(items = uiState.weatherDetailItems, modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp))
        }

        item {
            ForecastRow(
                hourlyWeather = uiState.weather.hourlyForecast,
            )
        }

        item {
            WeeklyForecastColumn(
                weeklyForecast = uiState.weather.dailyForecasts,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun WeatherScreenPreview() {
    MyWeatherTheme {
        WeatherScreenContent(uiState = WeatherUiState())
    }
}
