package com.example.myweather.domain.model

data class CurrentWeather(
    val time: String,
    val temperature: Measurement,
    val apparentTemperature: Measurement,
    val windSpeed: Measurement,
    val pressure: Measurement,
    val humidity: Measurement,
    val precipitationProbability: Measurement,
    val isDay: Boolean,
    val rain: Measurement,
    val uvIndex: Measurement,
    val weatherCondition: WeatherCondition
){
    constructor(): this(
        time = "",
        temperature = Measurement(),
        apparentTemperature = Measurement(),
        windSpeed = Measurement(),
        pressure = Measurement(),
        humidity = Measurement(),
        precipitationProbability = Measurement(),
        isDay = true,
        rain = Measurement(),
        uvIndex = Measurement(),
        weatherCondition = WeatherCondition.UNKNOWN
    )
}
