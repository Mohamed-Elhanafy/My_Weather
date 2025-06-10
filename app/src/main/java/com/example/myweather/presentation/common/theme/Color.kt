package com.example.myweather.presentation.common.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val primaryLight = Color(0xFF87CEFA)
val primaryDark = Color(0xFF060414)
val boarderLight = Color(0xFF060414).copy(alpha = 0.8f)
val boarderDark = Color(0xFFFFFFFF).copy(alpha = 0.8f)
val secondaryLight = Color(0xFF323232)
val backgroundLight = Color(0xFF87CEFA)
val backgroundDark = Color(0xFF060414)

val surfaceLight = Color.White
val surfaceDark = Color(0xFF0D0C19)

// Custom gradient colors for WeatherScreen
val gradientDefaultEndColorLight = Color(0xFFc0e5fc)  // Light theme default end color
val gradientTargetEndColorLight = Color(0xFF8fd1fb)   // Light theme target end color
val gradientDefaultEndColorDark = Color(0xFF090716)   // Dark theme default end color
val gradientTargetEndColorDark = Color(0xFF070615)    // Dark theme target end color

@Composable
fun backgroundGradientBrush(): Brush =
    Brush.verticalGradient(colors = listOf(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.surface))
