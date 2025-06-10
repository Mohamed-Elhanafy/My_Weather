package com.example.myweather.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyDTO(
    @SerialName("time") val time: List<String>? = null,
    @SerialName("weather_code") val weatherCode: List<Int>? = null,
    @SerialName("temperature_2m_max") val temperatureMax: List<Double>? = null,
    @SerialName("temperature_2m_min") val temperatureMin: List<Double>? = null
)
