package com.example.myweather.domain.model

import kotlinx.datetime.LocalDate

data class DailyForecast(
    val date: LocalDate,
    val weatherCondition: WeatherCondition,
    val maxTemperature: Measurement,
    val minTemperature: Measurement
)