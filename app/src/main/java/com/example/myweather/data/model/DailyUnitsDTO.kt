package com.example.myweather.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyUnitsDTO(
    @SerialName("temperature_2m_max") val temperatureMax: String? = null,
    @SerialName("temperature_2m_min") val temperatureMin: String? = null
)