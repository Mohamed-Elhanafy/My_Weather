package com.example.myweather.data.mapper

import com.example.myweather.data.model.CurrentDTO
import com.example.myweather.data.model.CurrentUnitsDTO
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
import com.example.myweather.domain.model.WeatherCondition
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun CurrentDTO.toCurrentWeather(units: CurrentUnitsDTO?): CurrentWeather {
    val weatherCode = this.weatherCode ?: -1

    return CurrentWeather(
        time = this.time ?: "",
        temperature = Measurement(this.temperature ?: 0.0, units?.temperature.orEmpty()),
        apparentTemperature = Measurement(this.apparentTemperature ?: 0.0, units?.apparentTemperature.orEmpty()),
        windSpeed = Measurement(this.windSpeed ?: 0.0, units?.windSpeed.orEmpty()),
        pressure = Measurement(this.pressure ?: 0.0, units?.pressure.orEmpty()),
        humidity = Measurement(this.relativeHumidity?.toDouble() ?: 0.0, units?.relativeHumidity ?: "%"),
        precipitationProbability = Measurement(this.precipitationProbability?.toDouble() ?: 0.0, units?.precipitationProbability ?: "%"),
        isDay = (this.isDay ?: 1) == 1,
        rain = Measurement(this.rain ?: 0.0, units?.rain.orEmpty()),
        uvIndex = Measurement(this.uvIndex ?: 0.0, units?.uvIndex.orEmpty()),
        weatherCondition = mapWeatherCodeToCondition(weatherCode)
    )
}

fun HourlyDTO.toHourlyForecast(units: HourlyUnitsDTO?): List<HourlyWeather> {
    val times = this.time ?: return emptyList()
    val temperatures = this.temperature ?: return emptyList()
    val weatherCodes = this.weatherCode ?: List(times.size) { -1 }
    val isDayValues = this.isDay ?: List(times.size) { 1 }
    val tempUnit = units?.temperature.orEmpty()

    val today = kotlinx.datetime.Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    return times.indices.mapNotNull { index ->
        val timeStr = times.getOrNull(index) ?: return@mapNotNull null
        val tempValue = temperatures.getOrNull(index) ?: return@mapNotNull null
        val weatherCode = weatherCodes.getOrNull(index) ?: -1
        val isDay = isDayValues.getOrNull(index) ?: 1

        val dateTime = LocalDateTime.parse(timeStr)
        if (dateTime.date == today) {
            HourlyWeather(
                dateTime = dateTime,
                temperature = Measurement(value = tempValue, unit = tempUnit),
                weatherCondition = mapWeatherCodeToCondition(weatherCode),
                isDay = isDay == 1
            )
        } else {
            null
        }
    }
}

fun DailyDTO.toDailyForecasts(units: DailyUnitsDTO?): List<DailyForecast> {
    val times = this.time ?: return emptyList()
    val weatherCodes = this.weatherCode ?: return emptyList()
    val maxTemps = this.temperatureMax ?: return emptyList()
    val minTemps = this.temperatureMin ?: return emptyList()

    val maxTempUnit = units?.temperatureMax.orEmpty()
    val minTempUnit = units?.temperatureMin.orEmpty()

    return times.indices.mapNotNull { index ->
        val weatherCode = weatherCodes.getOrNull(index) ?: 0
        DailyForecast(
            date = LocalDate.parse(times[index]),
            weatherCondition = mapWeatherCodeToCondition(weatherCode),
            maxTemperature = Measurement(maxTemps.getOrNull(index) ?: 0.0, maxTempUnit),
            minTemperature = Measurement(minTemps.getOrNull(index) ?: 0.0, minTempUnit)
        )
    }
}

fun WeatherDTO.toWeather(): Weather {
    val currentWeather = this.current?.toCurrentWeather(this.currentUnits) ?: CurrentWeather()
    val hourlyForecast = this.hourly?.toHourlyForecast(this.hourlyUnits) ?: emptyList()
    val dailyForecasts = this.daily?.toDailyForecasts(this.dailyUnits) ?: emptyList()

    return Weather(
        currentWeather = currentWeather,
        latitude = this.latitude ?: 0.0,
        longitude = this.longitude ?: 0.0,
        timezone = this.timezone ?: "Unknown",
        elevation = this.elevation ?: 0.0,
        hourlyForecast = hourlyForecast,
        dailyForecasts = dailyForecasts
    )
}


fun mapWeatherCodeToCondition(code: Int): WeatherCondition {
    return when (code) {
        0 -> WeatherCondition.CLEAR_SKY
        1 -> WeatherCondition.MAINLY_CLEAR
        2 -> WeatherCondition.PARTLY_CLOUDY
        3 -> WeatherCondition.OVERCAST
        45, 48 -> WeatherCondition.FOG
        51, 53, 55 -> WeatherCondition.DRIZZLE
        56, 57 -> WeatherCondition.LIGHT_FREEZING_DRIZZLE
        61 -> WeatherCondition.SLIGHT_RAIN
        63 -> WeatherCondition.MODERATE_RAIN
        65 -> WeatherCondition.HEAVY_INTENSITY_RAIN
        66 -> WeatherCondition.LIGHT_FREEZING_RAIN
        67 -> WeatherCondition.HEAVY_INTENSITY_FREEZING_RAIN
        71 -> WeatherCondition.SLIGHT_SNOW_FALL
        73 -> WeatherCondition.MODERATE_SNOW_FALL
        75 -> WeatherCondition.HEAVY_INTENSITY_SNOW_FALL
        77 -> WeatherCondition.SNOW_GRAINS
        80 -> WeatherCondition.SLIGHT_RAIN_SHOWERS
        81 -> WeatherCondition.MODERATE_RAIN_SHOWERS
        82 -> WeatherCondition.VIOLENT_RAIN_SHOWERS
        85 -> WeatherCondition.SLIGHT_SNOW_SHOWERS
        86 -> WeatherCondition.HEAVY_SNOW_SHOWERS
        95 -> WeatherCondition.SLIGHT_THUNDERSTORM
        96, 99 -> WeatherCondition.SLIGHT_THUNDERSTORM_WITH_HAIL
        else -> WeatherCondition.UNKNOWN
    }
}
