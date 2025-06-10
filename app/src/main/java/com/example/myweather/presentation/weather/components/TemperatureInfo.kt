package com.example.myweather.presentation.weather.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.R
import com.example.myweather.presentation.common.theme.MyWeatherTheme

@Composable
fun TemperatureInfo(temperature: String, weatherDescription: String, heightTemp: String, lowTemp: String, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = temperature,
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 64.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.25.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = weatherDescription,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            Modifier
                .background(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.08f),
                    shape = CircleShape
                )
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        ) {
            TemperatureRangeItem(
                icon = R.drawable.ic_arrow_up,
                text = heightTemp,
            )
            VerticalDivider(
                modifier = Modifier.fillMaxHeight().width(1.dp),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
            TemperatureRangeItem (
                    icon = R.drawable.ic_arrow_down,
            text = lowTemp,
            )
        }
    }
}

@Composable
fun TemperatureRangeItem(@DrawableRes icon: Int, text: String, modifier: Modifier = Modifier, colorAlpha : Float =0.6f, textSize: TextUnit = 16.sp) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(3.5.dp)) {
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground.copy(alpha =colorAlpha),
            modifier = Modifier.size(12.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = textSize,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            letterSpacing = 0.25.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = colorAlpha)
        )
    }
}

@PreviewLightDark
@Composable
private fun TemperatureInfoPreview() {
    MyWeatherTheme {
        Surface {
            TemperatureInfo(
                temperature = "25°C",
                weatherDescription = "Sunny",
                heightTemp = "30°C",
                lowTemp = "20°C",
            )
        }
    }
}