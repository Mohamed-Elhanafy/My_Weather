package com.example.myweather.presentation.common.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val DarkColorScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = Color.White,
    secondary = Color.White,
    background = backgroundDark,
    surface = surfaceDark,
    onBackground = Color.White,
    surfaceVariant = Color(0xFF0B0917),
    outline = boarderDark,
    tertiary = gradientDefaultEndColorDark,
    tertiaryContainer = gradientTargetEndColorDark
)

val LightColorScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = Color.White,
    secondary = secondaryLight,
    background = backgroundLight,
    surfaceVariant = Color(0xFFc0e5fc),
    onBackground = primaryDark,
    surface = surfaceLight,
    outline = boarderLight,
    tertiary = gradientDefaultEndColorLight,
    tertiaryContainer = gradientTargetEndColorLight
)