package com.example.myweather.domain.location

import com.example.myweather.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationService {
    fun getCurrentLocation(): Flow<Location>
}