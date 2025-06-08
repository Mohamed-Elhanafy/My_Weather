package com.example.myweather.domain.usecase

import com.example.myweather.domain.location.LocationService
import com.example.myweather.domain.model.Location
import kotlinx.coroutines.flow.Flow

class GetLocationUseCase(private val locationService: LocationService) {
    operator fun invoke(): Flow<Location> = locationService.getCurrentLocation()
}