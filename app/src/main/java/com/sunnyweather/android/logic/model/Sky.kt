package com.sunnyweather.android.logic.model

import androidx.compose.runtime.Composable
import com.sunnyweather.android.data.BgColors
import com.sunnyweather.android.data.Weather
import com.sunnyweather.android.data.WeatherAnimatableIcon.CloudyAnimatableIcon
import com.sunnyweather.android.data.WeatherAnimatableIcon.RainAnimatableIcon

import com.sunnyweather.android.data.WeatherAnimatableIcon.SunnyAnimatableIcon
import com.sunnyweather.android.data.WeatherBackground.CloudyBg
import com.sunnyweather.android.data.WeatherBackground.RainBg
import com.sunnyweather.android.data.WeatherBackground.SunnyBg
import com.sunnyweather.android.data.WeatherIcon.CloudyIcon
import com.sunnyweather.android.data.WeatherIcon.RainIcon
import com.sunnyweather.android.data.WeatherIcon.SunnyIcon

class Sky(
    val info: String,
    val icon: @Composable () -> Unit,
    val anime_icon: @Composable () -> Unit,
    val bg: BgColors
)

object WeatherCodeConverter {
    private val sky = mapOf(
        "CLEAR_DAY" to Sky("晴", SunnyIcon, SunnyAnimatableIcon, SunnyBg),
        "CLEAR_NIGHT" to Sky("晴", Weather.Sunny.icon, Weather.Sunny.animatableIcon, Weather.Sunny.background),
        "PARTLY_CLOUDY_DAY" to Sky("多云", Weather.Cloudy.icon, Weather.Cloudy.animatableIcon, Weather.Cloudy.background),
        "PARTLY_CLOUDY_NIGHT" to Sky("多云", Weather.Cloudy.icon, Weather.Cloudy.animatableIcon, Weather.Cloudy.background),
        "CLOUDY" to Sky("阴", Weather.Cloudy.icon, Weather.Cloudy.animatableIcon, Weather.Cloudy.background),
        "LIGHT_HAZE" to Sky("轻度雾霾", CloudyIcon, CloudyAnimatableIcon, CloudyBg),
        "MODERATE_HAZE" to Sky("中度雾霾", CloudyIcon, CloudyAnimatableIcon, CloudyBg),
        "HEAVY_HAZE" to Sky("重度雾霾", CloudyIcon, CloudyAnimatableIcon, CloudyBg),
        "LIGHT_RAIN" to Sky("小雨", RainIcon, RainAnimatableIcon, RainBg),
        "MODERATE_RAIN" to Sky("中雨", RainIcon, RainAnimatableIcon, RainBg),
        "HEAVY_RAIN" to Sky("大雨", RainIcon, RainAnimatableIcon, RainBg),
        "STORM_RAIN" to Sky("暴雨", RainIcon, RainAnimatableIcon, RainBg),
        "FOG" to Sky("雾", CloudyIcon, CloudyAnimatableIcon, CloudyBg),
        "LIGHT_SNOW" to Sky("小雪", Weather.Snowy.icon, Weather.Snowy.animatableIcon, Weather.Snowy.background),
        "MODERATE_SNOW" to Sky("中雪", Weather.Snowy.icon, Weather.Snowy.animatableIcon, Weather.Snowy.background),
        "HEAVY_SNOW" to Sky("大雪", Weather.Snowy.icon, Weather.Snowy.animatableIcon, Weather.Snowy.background),
        "STORM_SNOW" to Sky("暴雪", Weather.Snowy.icon, Weather.Snowy.animatableIcon, Weather.Snowy.background),
        "DUST" to Sky("浮尘", CloudyIcon, CloudyAnimatableIcon, CloudyBg),
        "SAND" to Sky("沙尘", CloudyIcon, CloudyAnimatableIcon, CloudyBg),
        "WIND" to Sky("大风", CloudyIcon, CloudyAnimatableIcon, CloudyBg)
    )

    fun getSky(skycon: String): Sky {
        return sky[skycon]?: sky["CLEAR_DAY"]!!
    }
}