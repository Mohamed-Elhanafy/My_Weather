package com.example.myweather.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myweather.R
import com.example.myweather.android.LocationPermissionHandler
import com.example.myweather.presentation.common.theme.MyWeatherTheme
import com.example.myweather.presentation.weather.WeatherScreen

class MainActivity : ComponentActivity() {

    private lateinit var locationPermissionHandler: LocationPermissionHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        locationPermissionHandler = LocationPermissionHandler(
            activity = this@MainActivity,
            onPermissionGranted = {  },
            onPermissionDenied = {
                Toast.makeText(
                    this,
                    getString(R.string.location_permission_message),
                    Toast.LENGTH_LONG
                ).show()
            }
        )
        locationPermissionHandler.checkAndRequestPermissions()

        setContent {
            MyWeatherTheme {
                WeatherScreen()
            }
        }
    }

}
