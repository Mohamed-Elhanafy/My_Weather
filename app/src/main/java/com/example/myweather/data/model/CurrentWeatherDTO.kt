package com.example.myweather.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDTO(
    @SerialName("interval") val interval: Int? = null,
    @SerialName("is_day") val isDay: Int? = null,
    @SerialName("temperature") val temperature: Double? = null,
    @SerialName("time") val time: String? = null,
    @SerialName("weathercode") val weatherCode: Int? = null,
    @SerialName("winddirection") val windDirection: Int?= null,
    @SerialName("windspeed") val windSpeed: Double? = null
)