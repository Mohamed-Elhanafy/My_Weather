package com.example.myweather.presentation.common.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.myweather.R

val urbanist = FontFamily(
    Font(resId = R.font.urbanist, weight = FontWeight.Normal),
    Font(resId = R.font.urbanist_medium, weight = FontWeight.Medium),
    Font(resId = R.font.urbanist_semibold, weight = FontWeight.SemiBold),
    Font(resId = R.font.urbanist_bold, weight = FontWeight.Bold)
)

val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = urbanist),
    displayMedium = baseline.displayMedium.copy(fontFamily = urbanist),
    displaySmall = baseline.displaySmall.copy(fontFamily = urbanist),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = urbanist),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = urbanist),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = urbanist),
    titleLarge = baseline.titleLarge.copy(fontFamily = urbanist),
    titleMedium = baseline.titleMedium.copy(fontFamily = urbanist),
    titleSmall = baseline.titleSmall.copy(fontFamily = urbanist),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = urbanist),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = urbanist),
    bodySmall = baseline.bodySmall.copy(fontFamily = urbanist),
    labelLarge = baseline.labelLarge.copy(fontFamily = urbanist),
    labelMedium = baseline.labelMedium.copy(fontFamily = urbanist),
    labelSmall = baseline.labelSmall.copy(fontFamily = urbanist),
)
