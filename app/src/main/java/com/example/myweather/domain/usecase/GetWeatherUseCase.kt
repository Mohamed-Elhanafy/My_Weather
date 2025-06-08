package com.example.myweather.domain.usecase

import com.example.myweather.domain.model.Location
import com.example.myweather.domain.repository.WeatherRepository

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun getWeather(location: Location) = weatherRepository.getCurrentWeather(location)
}