package com.example.myweather.domain.repository.remote

import com.example.myweather.data.model.WeatherDTO
import com.example.myweather.domain.model.Location

interface WeatherRemoteDataSource {
    suspend fun fetchWeatherData(location: Location): WeatherDTO
}
