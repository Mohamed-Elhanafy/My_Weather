package com.example.myweather.di

import com.example.myweather.data.location.GoogleLocationService
import com.example.myweather.data.repository.WeatherRepositoryImpl
import com.example.myweather.data.repository.remote.WeatherRemoteDataSourceImpl
import com.example.myweather.domain.location.LocationService
import com.example.myweather.domain.repository.WeatherRepository
import com.example.myweather.domain.repository.remote.WeatherRemoteDataSource
import com.example.myweather.domain.usecase.GetLocationUseCase
import com.example.myweather.domain.usecase.GetWeatherUseCase
import com.example.myweather.presentation.weather.WeatherVM
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.logging.ANDROID
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient() {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.ANDROID
            }
        }
    }

    // Remote data source
    singleOf(::WeatherRemoteDataSourceImpl) { bind<WeatherRemoteDataSource>() }

    // Repository
    singleOf(::WeatherRepositoryImpl) { bind<WeatherRepository>() }
    singleOf(::GetWeatherUseCase)

    // Location services
    singleOf(::GoogleLocationService) { bind<LocationService>() }
    singleOf(::GetLocationUseCase)

    // ViewModels
    viewModelOf(::WeatherVM)
}
