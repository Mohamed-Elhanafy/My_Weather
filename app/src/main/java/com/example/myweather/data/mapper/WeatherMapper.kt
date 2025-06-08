package com.example.myweather.data.mapper

import com.example.myweather.data.model.CurrentWeatherDTO
import com.example.myweather.data.model.CurrentWeatherUnitsDTO
import com.example.myweather.data.model.DailyDTO
import com.example.myweather.data.model.DailyUnitsDTO
import com.example.myweather.data.model.HourlyDTO
import com.example.myweather.data.model.HourlyUnitsDTO
import com.example.myweather.data.model.WeatherDTO
import com.example.myweather.domain.model.CurrentWeather
import com.example.myweather.domain.model.DailyForecast
import com.example.myweather.domain.model.HourlyWeather
import com.example.myweather.domain.model.Measurement
import com.example.myweather.domain.model.Weather
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

fun CurrentWeatherDTO.toCurrentWeather(units: CurrentWeatherUnitsDTO?): CurrentWeather {
    val dateTime = time?.let { LocalDateTime.parse(it) }

    return CurrentWeather(
        time = this.time ?: "",
        dateTime = dateTime,
        temperature = Measurement(
            value = this.temperature ?: 0.0,
            unit = units?.temperature ?: ""
        ),
        windSpeed = Measurement(
            value = this.windSpeed ?: 0.0,
            unit = units?.windSpeed ?: ""
        ),
        windDirection = this.windDirection ?: 0,
        weatherCode = this.weatherCode ?: 0,
        isDay = this.isDay == 1
    )
}

fun HourlyDTO.toHourlyForecast(units: HourlyUnitsDTO?): List<HourlyWeather> {
    val times = this.time ?: return emptyList()
    val temperatures = this.temperature ?: return emptyList()
    val tempUnit = units?.temperature ?: ""

    return times.zip(temperatures).map { (timeStr, tempValue) ->
        HourlyWeather(
            time = timeStr,
            dateTime = LocalDateTime.parse(timeStr),
            temperature = Measurement(value = tempValue, unit = tempUnit)
        )
    }
}

fun DailyDTO.toDailyForecasts(units: DailyUnitsDTO?): List<DailyForecast> {
    val times = this.time ?: return emptyList()
    val weatherCodes = this.weatherCode ?: return emptyList()
    val maxTemps = this.temperatureMax ?: return emptyList()
    val minTemps = this.temperatureMin ?: return emptyList()

    val maxTempUnit = units?.temperatureMax ?: ""
    val minTempUnit = units?.temperatureMin ?: ""

    return times.indices.mapNotNull { index ->
        DailyForecast(
            date = LocalDate.parse(times[index]), // Parse the string to LocalDate
            weatherCode = weatherCodes.getOrNull(index) ?: 0,
            maxTemperature = Measurement(maxTemps.getOrNull(index) ?: 0.0, maxTempUnit),
            minTemperature = Measurement(minTemps.getOrNull(index) ?: 0.0, minTempUnit)
        )
    }
}

fun WeatherDTO.toWeather(): Weather {
    val safeCurrentWeather = this.currentWeather?.toCurrentWeather(this.currentWeatherUnits)
        ?: CurrentWeather(
            time = "N/A",
            dateTime = null,
            temperature = Measurement(0.0, ""),
            windSpeed = Measurement(0.0, ""),
            windDirection = 0,
            weatherCode = 0,
            isDay = false
        )

    val hourlyForecast = this.hourly?.toHourlyForecast(this.hourlyUnits) ?: emptyList()
    val dailyForecasts = this.daily?.toDailyForecasts(this.dailyUnits) ?: emptyList()

    return Weather(
        currentWeather = safeCurrentWeather,
        latitude = this.latitude ?: 0.0,
        longitude = this.longitude ?: 0.0,
        timezone = this.timezone ?: "Unknown",
        elevation = this.elevation ?: 0.0,
        hourlyForecast = hourlyForecast,
        dailyForecasts = dailyForecasts
    )
}
