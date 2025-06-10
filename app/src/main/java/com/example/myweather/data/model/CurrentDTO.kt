package com.example.myweather.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentDTO(
    @SerialName("time") val time: String? = null,
    @SerialName("temperature_2m") val temperature: Double? = null,
    @SerialName("relative_humidity_2m") val relativeHumidity: Int? = null,
    @SerialName("apparent_temperature") val apparentTemperature: Double? = null,
    @SerialName("precipitation_probability") val precipitationProbability: Int? = null,
    @SerialName("pressure_msl") val pressure: Double? = null,
    @SerialName("wind_speed_10m") val windSpeed: Double? = null,
    @SerialName("is_day") val isDay: Int? = null,
    @SerialName("rain") val rain: Double? = null,
    @SerialName("uv_index") val uvIndex: Double? = null,
    @SerialName("weather_code") val weatherCode: Int? = null,
)
