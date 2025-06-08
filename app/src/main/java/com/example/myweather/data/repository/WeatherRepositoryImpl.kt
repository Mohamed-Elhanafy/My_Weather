package com.example.myweather.data.repository

import com.example.myweather.data.mapper.toWeather
import com.example.myweather.data.model.WeatherDTO
import com.example.myweather.domain.exception.MyWeatherException
import com.example.myweather.domain.model.Location
import com.example.myweather.domain.model.Weather
import com.example.myweather.domain.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get

class WeatherRepositoryImpl(private val httpClient: HttpClient) : WeatherRepository {

    override suspend fun getCurrentWeather(location: Location): Weather {
        return apiCall {
            val url = "$OPEN_METEO_URL?latitude=${location.latitude}&longitude=${location.longitude}&current_weather=true&hourly=temperature_2m&daily=weather_code,temperature_2m_max,temperature_2m_min&timezone=auto"
            val response = httpClient.get(url)
            val weatherDTO = response.body<WeatherDTO>()
            weatherDTO.toWeather()
        }
    }

    private suspend fun <T> apiCall(block: suspend () -> T): T {
        return try {
            block()
        } catch (e: Exception) {
            when (e) {
                is ResponseException -> {
                    val errorCode = e.response.status.value
                    throw MyWeatherException.NetworkException("response error: $errorCode - ${e.message}")
                }

                else -> throw MyWeatherException.NetworkException("response error: ${e.message}")
            }
        }
    }

    companion object {
        private const val OPEN_METEO_URL = "https://api.open-meteo.com/v1/forecast"
    }
}