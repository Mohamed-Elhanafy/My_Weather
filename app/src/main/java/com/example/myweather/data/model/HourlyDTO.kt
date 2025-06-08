package com.example.myweather.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyDTO(
    @SerialName("time") val time: List<String>? = null,
    @SerialName("temperature_2m") val temperature: List<Double>? = null
)