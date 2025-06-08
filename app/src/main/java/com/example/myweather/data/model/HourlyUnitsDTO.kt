package com.example.myweather.data.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyUnitsDTO(
    @SerialName("time") val time: String? = null,
    @SerialName("temperature_2m") val temperature: String? = null
)