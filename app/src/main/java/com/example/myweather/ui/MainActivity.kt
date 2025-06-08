package com.example.myweather.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myweather.domain.model.Location
import com.example.myweather.domain.usecase.GetWeatherUseCase
import com.example.myweather.ui.theme.MyWeatherTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val getWeatherUseCase: GetWeatherUseCase by inject()

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        GlobalScope.launch {
            val testLocation = Location(30.033333, -31.233334)
            val weatherResult = getWeatherUseCase.getWeather(testLocation)

            Log.d("WeatherTest", "Weather data retrieved successfully")
            Log.d(
                "WeatherTest",
                "Current temp: ${weatherResult.currentWeather.temperature.value} ${weatherResult.currentWeather.temperature.unit}"
            )

        }

        setContent {
            MyWeatherTheme {
                /*       Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                       }*/
            }
        }
    }
}
