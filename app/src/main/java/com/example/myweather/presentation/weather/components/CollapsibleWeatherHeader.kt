package com.example.myweather.presentation.weather.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.myweather.R
import com.example.myweather.presentation.common.theme.MyWeatherTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp

@Composable
fun CollapsibleWeatherHeader(
    scrollOffset: Float,
    address: String,
    weatherIconRes: Int,
    modifier: Modifier = Modifier,
    collapsedThreshold: Float = 200f,
    temperatureContent: @Composable () -> Unit
) {

    val collapseProgress = (scrollOffset / collapsedThreshold).coerceIn(0f, 1f)

    val startColor = MaterialTheme.colorScheme.primary
    val defaultEndColor = MaterialTheme.colorScheme.tertiary
    val targetEndColor = MaterialTheme.colorScheme.tertiaryContainer

    val endColor by animateColorAsState(targetValue = lerp(defaultEndColor, targetEndColor, collapseProgress))

    val animatedBackgroundBrush = verticalGradient(
        colors = listOf(startColor, endColor), startY = 0f, endY = Float.POSITIVE_INFINITY
    )

    val transition = updateTransition(targetState = collapseProgress)

    val imageWidth by transition.animateDp{ progress ->
        lerp(220.dp, 124.dp, progress)
    }

    val imageHeight by transition.animateDp{ progress ->
        lerp(200.dp, 112.dp, progress)
    }

    val imageHorizontalOffset by transition.animateDp{ progress ->
        lerp(0.dp, (-100).dp, progress)
    }

    val temperatureHorizontalOffset by transition.animateDp{ progress ->
        lerp(0.dp, 100.dp, progress)
    }

    val temperatureVerticalOffset by transition.animateDp { progress ->
        lerp(62.dp, (-60).dp, progress)
    }

    val totalHeight by transition.animateDp { progress ->
        if (progress < 0.5f) {
            imageHeight + 48.dp + 168.dp
        } else {
            imageHeight + 48.dp + 44.dp
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(totalHeight)
            .background(animatedBackgroundBrush)
    ) {
        LocationInfo(
            address = address,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 12.dp)
        )

        Image(
            painter = painterResource(id = weatherIconRes),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(x = imageHorizontalOffset, y = 48.dp)
                .size(width = imageWidth, height =  imageHeight),
            contentScale = ContentScale.Fit
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(x = temperatureHorizontalOffset, y = imageHeight + temperatureVerticalOffset)
        ) {
            temperatureContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CollapsibleWeatherHeaderExpandedPreview() {
    MyWeatherTheme {
        CollapsibleWeatherHeader(
            scrollOffset = 0f, // Fully expanded
            address = "Baghdad",
            weatherIconRes = R.drawable.img_partly_cloudy_light,
            temperatureContent = {
                TemperatureInfo(
                    temperature = "24°C",
                    weatherDescription = "Partly cloudy",
                    heightTemp = "32°C",
                    lowTemp = "20°C"
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CollapsibleWeatherHeaderCollapsedPreview() {
    MyWeatherTheme {
        CollapsibleWeatherHeader(
            scrollOffset = 200f, // Fully collapsed
            address = "Baghdad",
            weatherIconRes = R.drawable.img_partly_cloudy_light,
            temperatureContent = {
                TemperatureInfo(
                    temperature = "24°C",
                    weatherDescription = "Partly cloudy",
                    heightTemp = "32°C",
                    lowTemp = "20°C"
                )
            }
        )
    }
}

private fun lerp(start: Dp, end: Dp, fraction: Float): Dp {
    return Dp(start.value + ((end.value - start.value) * fraction))
}
