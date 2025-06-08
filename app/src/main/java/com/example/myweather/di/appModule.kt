package com.example.myweather.di

import com.example.myweather.data.repository.WeatherRepositoryImpl
import com.example.myweather.domain.repository.WeatherRepository
import com.example.myweather.domain.usecase.GetWeatherUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
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
                level = LogLevel.INFO
                logger = object : io.ktor.client.plugins.logging.Logger {
                    override fun log(message: String) {
                        println("HTTP Client: $message")
                    }
                }
            }
        }
    }

    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    singleOf(::GetWeatherUseCase)

}
