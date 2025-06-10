package com.example.myweather.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentUnitsDTO(
    @SerialName("temperature_2m") val temperature: String? = null,
    @SerialName("relative_humidity_2m") val relativeHumidity: String? = null,
    @SerialName("apparent_temperature") val apparentTemperature: String? = null,
    @SerialName("precipitation_probability") val precipitationProbability: String? = null,
    @SerialName("pressure_msl") val pressure: String? = null,
    @SerialName("wind_speed_10m") val windSpeed: String? = null,
    @SerialName("rain") val rain: String? = null,
    @SerialName("uv_index") val uvIndex: String? = null,
)