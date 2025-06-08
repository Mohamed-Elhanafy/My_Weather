package com.example.myweather.presentation.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = Color.White,
    secondary = Color.White,
    background = backgroundDark,
    surface = surfaceDark,
    onBackground = Color.White,
    surfaceVariant = Color(0xFF0B0917),
    outline = boarderDark,
    // Add custom gradient colors to scheme
    tertiary = gradientDefaultEndColorDark,
    tertiaryContainer = gradientTargetEndColorDark
)

private val LightColorScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = Color.White,
    secondary = secondaryLight,
    background = backgroundLight,
    surfaceVariant = Color(0xFFc0e5fc),
    onBackground = primaryDark,
    surface = surfaceLight,
    outline = boarderLight,
    // Add custom gradient colors to scheme
    tertiary = gradientDefaultEndColorLight,
    tertiaryContainer = gradientTargetEndColorLight
)

@Composable
fun MyWeatherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
