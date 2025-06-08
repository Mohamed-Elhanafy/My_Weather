package com.example.myweather.domain.repository

import com.example.myweather.domain.model.Location
import com.example.myweather.domain.model.Weather


interface WeatherRepository {
    suspend fun getCurrentWeather(location: Location): Weather
}