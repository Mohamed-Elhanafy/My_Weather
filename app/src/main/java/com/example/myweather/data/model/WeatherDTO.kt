package com.example.myweather.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDTO(
    @SerialName("current") val current: CurrentDTO? = null,
    @SerialName("current_units") val currentUnits: CurrentUnitsDTO? = null,
    @SerialName("elevation") val elevation: Double? = null,
    @SerialName("generationtime_ms") val generationTimeMs: Double? = null,
    @SerialName("latitude") val latitude: Double? = null,
    @SerialName("longitude") val longitude: Double? = null,
    @SerialName("timezone") val timezone: String? = null,
    @SerialName("timezone_abbreviation") val timezoneAbbreviation: String? = null,
    @SerialName("utc_offset_seconds") val utcOffsetSeconds: Int? = null,
    @SerialName("hourly_units") val hourlyUnits: HourlyUnitsDTO? = null,
    @SerialName("hourly") val hourly: HourlyDTO? = null,
    @SerialName("daily") val daily: DailyDTO? = null,
    @SerialName("daily_units") val dailyUnits: DailyUnitsDTO? = null,
)