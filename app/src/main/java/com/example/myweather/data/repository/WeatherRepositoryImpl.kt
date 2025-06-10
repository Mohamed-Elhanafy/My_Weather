package com.example.myweather.data.repository

import com.example.myweather.data.mapper.toWeather
import com.example.myweather.domain.model.Location
import com.example.myweather.domain.model.Weather
import com.example.myweather.domain.repository.WeatherRepository
import com.example.myweather.domain.repository.remote.WeatherRemoteDataSource

class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override suspend fun getCurrentWeather(location: Location): Weather {
        val weatherDTO = weatherRemoteDataSource.fetchWeatherData(location)
        return weatherDTO.toWeather()
    }
}
