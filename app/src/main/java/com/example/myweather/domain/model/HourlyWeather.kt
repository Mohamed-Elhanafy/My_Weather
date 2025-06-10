package com.example.myweather.domain.model

import kotlinx.datetime.LocalDateTime

data class HourlyWeather(
    val dateTime: LocalDateTime?,
    val temperature: Measurement,
    val weatherCondition: WeatherCondition,
    val isDay: Boolean
)