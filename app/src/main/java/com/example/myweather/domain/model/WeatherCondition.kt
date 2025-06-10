package com.example.myweather.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.myweather.R

enum class WeatherCondition(
    @StringRes val stringResource: Int, @DrawableRes val dayIconResource: Int, @DrawableRes val nightIconResource: Int
) {
    CLEAR_SKY(
        R.string.weather_clear_sky, R.drawable.img_clear_sky_day, R.drawable.img_clear_sky_night
    ),
    MAINLY_CLEAR(
        R.string.weather_mainly_clear, R.drawable.img_mainly_clear_day, R.drawable.img_mainly_clear_night
    ),
    PARTLY_CLOUDY(
        R.string.weather_partly_cloudy, R.drawable.img_partly_cloudy_light, R.drawable.img_partly_cloudy_night
    ),
    OVERCAST(
        R.string.weather_overcast, R.drawable.img_overcast, R.drawable.img_overcast
    ),
    FOG(
        R.string.weather_fog, R.drawable.img_fog_day, R.drawable.img_fog_night
    ),
    DRIZZLE(R.string.weather_drizzle, R.drawable.img_drizzle_light_day, R.drawable.img_drizzle_light_day), LIGHT_FREEZING_DRIZZLE(
        R.string.weather_light_freezing_drizzle, R.drawable.img_freezing_drizzle_light_day, R.drawable.img_freezing_drizzle_light_day
    ),
    SLIGHT_RAIN(
        R.string.weather_slight_rain, R.drawable.img_rain_slight_day, R.drawable.img_rain_slight_night
    ),
    MODERATE_RAIN(
        R.string.weather_moderate_rain, R.drawable.img_rain_moderate_day, R.drawable.img_rain_moderate_night
    ),
    HEAVY_INTENSITY_RAIN(
        R.string.weather_heavy_rain, R.drawable.img_rain_intensity_day, R.drawable.img_rain_intensity_night
    ),
    LIGHT_FREEZING_RAIN(
        R.string.weather_light_freezing_rain, R.drawable.img_freezing_drizzle_light_day, R.drawable.img_freezing_drizzle_light_day
    ),
    HEAVY_INTENSITY_FREEZING_RAIN(
        R.string.weather_heavy_freezing_rain, R.drawable.img_freezing_drizzle_light_day, R.drawable.img_freezing_drizzle_light_day
    ),
    SLIGHT_SNOW_FALL(
        R.string.weather_light_snow, R.drawable.img_snow_fall_day, R.drawable.img_snow_fall_light_night
    ),
    MODERATE_SNOW_FALL(
        R.string.weather_moderate_snow, R.drawable.img_snow_fall_moderate_day, R.drawable.img_snow_fall_moderate_night
    ),
    HEAVY_INTENSITY_SNOW_FALL(
        R.string.weather_heavy_snow, R.drawable.snow_fall_intensity, R.drawable.snow_fall_intensity
    ),
    SNOW_GRAINS(
        R.string.weather_snow_grains, R.drawable.img_snow_grains, R.drawable.img_snow_grains
    ),
    SLIGHT_RAIN_SHOWERS(
        R.string.weather_light_rain_showers, R.drawable.img_rain_shower_slight_day, R.drawable.img_rain_shower_slight_night
    ),
    MODERATE_RAIN_SHOWERS(
        R.string.weather_moderate_rain_showers, R.drawable.img_rain_shower_moderate_day, R.drawable.img_rain_shower_moderate_night
    ),
    VIOLENT_RAIN_SHOWERS(
        R.string.weather_violent_rain_showers, R.drawable.img_rain_shower_violent_day, R.drawable.img_rain_shower_violent_night
    ),
    SLIGHT_SNOW_SHOWERS(
        R.string.weather_light_snow_showers, R.drawable.img_snow_shower_slight, R.drawable.img_snow_shower_slight
    ),
    HEAVY_SNOW_SHOWERS(
        R.string.weather_heavy_snow_showers, R.drawable.img_snow_shower_heavy, R.drawable.img_snow_shower_heavy
    ),
    SLIGHT_THUNDERSTORM(
        R.string.weather_slight_thunderstorm, R.drawable.img_thunderstrom_slight_or_moderate, R.drawable.img_thunderstrom_slight_or_moderate
    ),
    SLIGHT_THUNDERSTORM_WITH_HAIL(
        R.string.weather_thunderstorm_with_hail, R.drawable.img_thunderstrom_with_slight_hail, R.drawable.img_thunderstrom_with_heavy_hail
    ),
    UNKNOWN(
        R.string.weather_unknown, R.drawable.img_partly_cloudy_light, R.drawable.img_partly_cloudy_night
    );

    fun getIconResource(isDay: Boolean): Int = if (isDay) dayIconResource else nightIconResource
}
