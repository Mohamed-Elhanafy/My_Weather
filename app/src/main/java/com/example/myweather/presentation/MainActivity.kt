package com.example.myweather.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.example.myweather.android.LocationPermissionHandler
import com.example.myweather.domain.usecase.GetLocationUseCase
import com.example.myweather.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val getLocationUseCase: GetLocationUseCase by inject()
    private val getWeatherUseCase: GetWeatherUseCase by inject()
    private lateinit var locationPermissionHandler: LocationPermissionHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        locationPermissionHandler = LocationPermissionHandler(
            activity = this@MainActivity,
            onPermissionGranted = { startLocationUpdates() },
            onPermissionDenied = {
                Toast.makeText(
                    this,
                    "Location permission is required to fetch the weather.",
                    Toast.LENGTH_LONG
                ).show()
            }
        )

        locationPermissionHandler.checkAndRequestPermissions()
    }

    private fun startLocationUpdates() {
        getLocationUseCase()

            .onEach { location ->
                val weatherResult = getWeatherUseCase.getWeather(location)

                Log.d("LocationTest", "startLocationUpdates: ${location.name}")

                Log.d("LocationTest", "currentWeather: ${weatherResult.currentWeather}")
                Log.d("LocationTest", "Location received - Latitude: ${location.latitude}, Longitude: ${location.longitude}")
            }
            .catch { e ->
                Log.e("LocationTest", "Error getting location: ${e.message}", e)
            }
            .launchIn(lifecycleScope)
    }
}
