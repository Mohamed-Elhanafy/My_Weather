package com.example.myweather.domain.model

data class Weather(
    val currentWeather: CurrentWeather,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val elevation: Double,
    val hourlyForecast: List<HourlyWeather>,
    val dailyForecasts: List<DailyForecast>,
){
    constructor(): this(
        currentWeather = CurrentWeather(),
        latitude = 0.0,
        longitude = 0.0,
        timezone = "",
        elevation = 0.0,
        hourlyForecast = emptyList(),
        dailyForecasts = emptyList(),
    )
}