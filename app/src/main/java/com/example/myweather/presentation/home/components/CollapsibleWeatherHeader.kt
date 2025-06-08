package com.example.myweather.presentation.home.components

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.myweather.R
import com.example.myweather.presentation.common.theme.MyWeatherTheme

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

    // Create a transition to animate between expanded and collapsed states
    val transition = updateTransition(
        targetState = collapseProgress,
        label = "header_collapse_transition"
    )

    val imageWidth by transition.animateDp(label = "image_width") { progress ->
        lerp(220.dp, 124.dp, progress)
    }

    val imageHeight by transition.animateDp(label = "image_height") { progress ->
        lerp(200.dp, 112.dp, progress)
    }

    val imageHorizontalOffset by transition.animateDp(label = "image_horizontal_offset") { progress ->
        lerp(0.dp, (-100).dp, progress)
    }

    val temperatureHorizontalOffset by transition.animateDp(label = "temp_horizontal_offset") { progress ->
        lerp(0.dp, 100.dp, progress)
    }

    val temperatureVerticalOffset by transition.animateDp(label = "temp_vertical_offset") { progress ->
        lerp(62.dp, (-60).dp, progress)
    }

    val totalHeight by transition.animateDp(label = "total_height") { progress ->
        // When expanded: image height + padding + temperature content height
        // When collapsed: just enough height to fit both side by side
        if (progress < 0.5f) {
            imageHeight + 48.dp + 158.dp
        } else {
            imageHeight + 48.dp + 38.dp // Additional padding at the bottom in collapsed state
        }
    }

    // Animate alpha for better visual transition
    val imageAlpha by transition.animateFloat(label = "image_alpha") { progress ->
        if (progress > 0.9f) lerpFloat(1f, 0.95f, (progress - 0.9f) * 10) else 1f
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(totalHeight)
    ) {
        LocationInfo(
            address = address,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 12.dp)
        )

        Image(
            painter = painterResource(id = weatherIconRes),
            contentDescription = "Weather condition",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(x = imageHorizontalOffset, y = 48.dp)
                .size(width = imageWidth, height =  imageHeight)
                .alpha(imageAlpha),
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
            collapsedThreshold = 200f,
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
            collapsedThreshold = 200f,
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

// Helper function to linearly interpolate between two dp values
private fun lerp(start: Dp, end: Dp, fraction: Float): Dp {
    return Dp(start.value + ((end.value - start.value) * fraction))
}

// Helper function to linearly interpolate between two float values
private fun lerpFloat(start: Float, end: Float, fraction: Float): Float {
    return start + ((end - start) * fraction)
}
