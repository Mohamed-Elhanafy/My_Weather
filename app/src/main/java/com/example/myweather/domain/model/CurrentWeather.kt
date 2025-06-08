package com.example.myweather.domain.model

import kotlinx.datetime.LocalDateTime

data class CurrentWeather(
    val time: String,
    val dateTime: LocalDateTime?,
    val temperature: Measurement,
    val windSpeed: Measurement,
    val windDirection: Int,
    val weatherCode: Int,
    val isDay: Boolean
)
