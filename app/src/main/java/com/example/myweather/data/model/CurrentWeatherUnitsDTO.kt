package com.example.myweather.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherUnitsDTO(
    @SerialName("interval") val interval: String? = null,
    @SerialName("is_day") val isDay: String? = null,
    @SerialName("temperature") val temperature: String? = null,
    @SerialName("time") val time: String? = null,
    @SerialName("weathercode") val weatherCode: String? = null,
    @SerialName("winddirection") val windDirection: String? = null,
    @SerialName("windspeed") val windSpeed: String? = null
)