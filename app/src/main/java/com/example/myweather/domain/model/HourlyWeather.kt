package com.example.myweather.domain.model

import kotlinx.datetime.LocalDateTime

data class HourlyWeather(val time: String, val dateTime: LocalDateTime?, val temperature: Measurement)