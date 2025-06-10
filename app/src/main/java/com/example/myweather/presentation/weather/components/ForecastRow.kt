package com.example.myweather.presentation.weather.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.R
import com.example.myweather.domain.model.HourlyWeather
import com.example.myweather.domain.model.Measurement
import com.example.myweather.domain.model.WeatherCondition
import com.example.myweather.presentation.common.theme.MyWeatherTheme

@Composable
fun ForecastRow(hourlyWeather: List<HourlyWeather> ,modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = stringResource(R.string.today),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(start = 12.dp)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(132.dp),
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(hourlyWeather) { weather ->
                ForecastItem(
                    temperature = weather.temperature.toString(),
                    image = weather.weatherCondition.getIconResource(weather.isDay),
                    time = weather.dateTime?.time.toString()
                )
            }
        }   
    }
}

@Composable
fun ForecastItem(temperature: String, @DrawableRes image: Int, time: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        WeatherCard(
            temperature = temperature,
            time = time,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .height(58.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun WeatherCard(temperature: String, time: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(top = 12.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.08f),
                shape = RoundedCornerShape(20.dp)
            )
            .size(width = 88.dp, height = 120.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 58.dp, bottom = 16.dp)
        ) {
            Text(
                text = temperature,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.25.sp,
                color = MaterialTheme.colorScheme.outline
            )
            Text(
                text = time,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun ForecastItemPreview() {
    MyWeatherTheme {
        Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            ForecastItem(temperature = "25°", image = R.drawable.img_clear_sky_day, time = "12:00")
        }
    }
}

@PreviewLightDark
@Composable
private fun ForecastRowPreview() {
    val sampleHourlyWeather = listOf(
        HourlyWeather(
            dateTime = null,
            temperature = Measurement(25.0, "°"),
            weatherCondition = WeatherCondition.CLEAR_SKY,
            isDay = true
        ),
        HourlyWeather(
            dateTime = null,
            temperature = Measurement(27.0, "°"),
            weatherCondition = WeatherCondition.PARTLY_CLOUDY,
            isDay = true
        ),
        HourlyWeather(
            dateTime = null,
            temperature = Measurement(26.0, "°"),
            weatherCondition = WeatherCondition.PARTLY_CLOUDY,
            isDay = true
        )
    )

    MyWeatherTheme {
        ForecastRow(hourlyWeather = sampleHourlyWeather)
    }
}
