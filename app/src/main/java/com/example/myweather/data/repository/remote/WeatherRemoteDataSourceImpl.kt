package com.example.myweather.data.repository.remote

import com.example.myweather.data.model.WeatherDTO
import com.example.myweather.domain.exception.MyWeatherException
import com.example.myweather.domain.model.Location
import com.example.myweather.domain.repository.remote.WeatherRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path
import kotlinx.coroutines.CancellationException

class WeatherRemoteDataSourceImpl(private val httpClient: HttpClient) : WeatherRemoteDataSource {

    override suspend fun fetchWeatherData(location: Location): WeatherDTO {
        return apiCall {
            httpClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = OPEN_METEO_URL
                    path("v1/forecast")
                    parameters.append("latitude", location.latitude.toString())
                    parameters.append("longitude", location.longitude.toString())
                    parameters.append("timezone", "auto")
                    parameters.append("current", "temperature_2m,relative_humidity_2m,apparent_temperature,precipitation_probability,pressure_msl,wind_speed_10m,is_day")
                    parameters.append("daily", "weather_code,temperature_2m_max,temperature_2m_min")
                    parameters.append("hourly", "temperature_2m,weather_code,is_day")
                }
            }.body<WeatherDTO>()
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

                is CancellationException -> throw e

                else -> throw MyWeatherException.NetworkException("response error: ${e.message}")
            }
        }
    }

    companion object {
        private const val OPEN_METEO_URL = "api.open-meteo.com"
    }
}