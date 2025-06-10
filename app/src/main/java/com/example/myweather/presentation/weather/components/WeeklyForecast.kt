package com.example.myweather.presentation.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.R
import com.example.myweather.domain.model.DailyForecast
import com.example.myweather.domain.model.Measurement
import com.example.myweather.domain.model.WeatherCondition
import com.example.myweather.presentation.common.theme.MyWeatherTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun WeeklyForecastColumn(weeklyForecast: List<DailyForecast>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(horizontal = 12.dp)) {
        Text(
            text = stringResource(R.string.next_7_days),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier
        )
        Card(
            modifier = Modifier
                .padding(top = 12.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.08f),
                    shape = RoundedCornerShape(24.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
            ),
            shape = RoundedCornerShape(24.dp),
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)) {
                weeklyForecast.forEachIndexed { index, dailyForecast ->
                    WeeklyForecastItem(dailyForecast = dailyForecast)
                    if (index < weeklyForecast.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
private fun WeeklyForecastItem(dailyForecast: DailyForecast, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = dailyForecast.date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f),
            modifier = Modifier
                .padding(start = 16.dp)
                .width(120.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(80.dp)
        ) {
            Image(
                painter = painterResource(id = dailyForecast.weatherCondition.getIconResource(true)),
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp)
                    .padding(vertical = 8.dp),
                contentScale = ContentScale.Fit
            )
        }
        Row(
            Modifier
                .padding(end = 16.dp)
                .weight(1f)
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        ) {
            TemperatureRangeItem(
                icon = R.drawable.ic_arrow_up,
                text = dailyForecast.maxTemperature.toString(),
                colorAlpha = 0.87f,
                textSize = 14.sp
            )
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
            TemperatureRangeItem(
                icon = R.drawable.ic_arrow_down,
                text = dailyForecast.minTemperature.toString(),
                colorAlpha = 0.87f,
                textSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
private fun WeeklyForecastItemPreview() {
    MyWeatherTheme {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val weeklyForecast = List(7) { i ->
            DailyForecast(
                date = today.plus(i, kotlinx.datetime.DateTimeUnit.DAY),
                maxTemperature = Measurement(25.0 + i, "°C"),
                minTemperature = Measurement(20.0 + i, "°C"),
                weatherCondition = WeatherCondition.CLEAR_SKY
            )
        }
        WeeklyForecastColumn(
            weeklyForecast = weeklyForecast
        )
    }
}