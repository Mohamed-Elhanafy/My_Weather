package com.example.myweather.data.mapper

import com.example.myweather.data.model.CurrentWeatherDTO
import com.example.myweather.data.model.CurrentWeatherUnitsDTO
import com.example.myweather.data.model.WeatherDTO
import com.example.myweather.domain.model.CurrentWeather
import com.example.myweather.domain.model.Measurement
import com.example.myweather.domain.model.Weather
import kotlinx.datetime.LocalDateTime

fun CurrentWeatherDTO.toCurrentWeather(units: CurrentWeatherUnitsDTO?): CurrentWeather {
    val dateTime = time?.let { LocalDateTime.parse(it) }

    return CurrentWeather(
        time = this.time ?: "",
        dateTime = dateTime,
        temperature = Measurement(
            value = this.temperature ?: 0.0,
            unit = units?.temperature ?: ""
        ),
        windSpeed = Measurement(
            value = this.windSpeed ?: 0.0,
            unit = units?.windSpeed ?: ""
        ),
        windDirection = this.windDirection ?: 0,
        weatherCode = this.weatherCode ?: 0,
        isDay = this.isDay == 1
    )
}

fun WeatherDTO.toWeather(): Weather {
    val safeCurrentWeather = this.currentWeather?.toCurrentWeather(this.currentWeatherUnits)
        ?: CurrentWeather(
            time = "N/A",
            dateTime = null,
            temperature = Measurement(0.0, ""),
            windSpeed = Measurement(0.0, ""),
            windDirection = 0,
            weatherCode = 0,
            isDay = false
        )

    return Weather(
        currentWeather = safeCurrentWeather,
        latitude = this.latitude ?: 0.0,
        longitude = this.longitude ?: 0.0,
        timezone = this.timezone ?: "Unknown",
        elevation = this.elevation ?: 0.0
    )
}
