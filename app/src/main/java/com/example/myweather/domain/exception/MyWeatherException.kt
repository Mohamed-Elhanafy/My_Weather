package com.example.myweather.domain.exception

abstract class MyWeatherException(message: String? = null) : Exception(message) {
    class NetworkException(message: String? = null) : MyWeatherException(message)
    class InvalidInputException(message: String? = null) : MyWeatherException(message)
    class NotFoundException(message: String? = null) : MyWeatherException(message)
    class LocationException(message: String? = null) : MyWeatherException(message)
}