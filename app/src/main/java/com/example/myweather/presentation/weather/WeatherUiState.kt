package com.example.myweather.presentation.weather

import androidx.annotation.DrawableRes
import com.example.myweather.R
import com.example.myweather.domain.model.Location
import com.example.myweather.domain.model.Weather

data class WeatherUiState(
    val location: Location = Location(),
    val weather: Weather = Weather(),
    val error: String? = null,
){
    val mainWeatherImage = weather.currentWeather.weatherCondition.getIconResource(weather.currentWeather.isDay)

    val currentTemperature = weather.currentWeather.temperature.toString()
    val weatherConditionResource = weather.currentWeather.weatherCondition.stringResource
    val highTemperature = weather.dailyForecasts.firstOrNull()?.maxTemperature?.toString() ?: "--"
    val lowTemperature = weather.dailyForecasts.firstOrNull()?.minTemperature?.toString() ?: "--"

    val weatherDetailItems = listOf(
        WeatherDetailItem(R.drawable.ic_wind, weather.currentWeather.windSpeed.toString(), "Wind"),
        WeatherDetailItem(R.drawable.ic_humidity, weather.currentWeather.humidity.toString(), "Humidity"),
        WeatherDetailItem(R.drawable.ic_rain, weather.currentWeather.rain.toString(), "Rain"),
        WeatherDetailItem(R.drawable.ic_uv, weather.currentWeather.uvIndex.toString(), "UV Index"),
        WeatherDetailItem(R.drawable.ic_pressure, weather.currentWeather.pressure.toString(), "Pressure"),
        WeatherDetailItem(R.drawable.ic_temp, weather.currentWeather.apparentTemperature.toString(), "Feels like")
    )
}

data class WeatherDetailItem(
    @DrawableRes val icon: Int,
    val text: String,
    val label: String
)