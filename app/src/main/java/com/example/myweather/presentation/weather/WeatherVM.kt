package com.example.myweather.presentation.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather.domain.exception.MyWeatherException
import com.example.myweather.domain.usecase.GetLocationUseCase
import com.example.myweather.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WeatherVM(
    private val getLocationUseCase: GetLocationUseCase,
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())

    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    init {
        getLocationAndWeather()
    }

    private fun getLocationAndWeather() {
        viewModelScope.launch {
            try {
                getLocationUseCase().collectLatest { location ->
                    try {
                        val weather = getWeatherUseCase.getWeather(location)
                        _uiState.value = _uiState.value.copy(
                            weather = weather,
                            location = location,
                            error = null
                        )
                    } catch (e: MyWeatherException) {
                        _uiState.value = _uiState.value.copy(
                            error = when (e) {
                                is MyWeatherException.NetworkException -> "Network error: ${e.message ?: "Failed to fetch weather data"}"
                                else -> "Error fetching weather: ${e.message ?: "Unknown error"}"
                            }
                        )
                    }
                }
            } catch (e: MyWeatherException.LocationException) {
                _uiState.value = _uiState.value.copy(error = "Location error: ${e.message ?: "Failed to get location"}")
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = "Unexpected error: ${e.message ?: "Unknown error"}")
            }
        }
    }
}
