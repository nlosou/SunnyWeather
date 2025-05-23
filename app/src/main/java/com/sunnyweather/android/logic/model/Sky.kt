package com.sunnyweather.android.logic.model

import androidx.compose.runtime.Composable
import com.sunnyweather.android.logic.model.data.Weather
import com.sunnyweather.android.logic.model.data.WeatherAnimatableIcon.CloudyAnimatableIcon
import com.sunnyweather.android.logic.model.data.WeatherAnimatableIcon.RainAnimatableIcon

import com.sunnyweather.android.logic.model.data.WeatherAnimatableIcon.SunnyAnimatableIcon
import com.sunnyweather.android.logic.model.data.WeatherBackground.CloudyBg
import com.sunnyweather.android.logic.model.data.WeatherBackground.RainBg
import com.sunnyweather.android.logic.model.data.WeatherBackground.SunnyBg
import com.sunnyweather.android.logic.model.data.WeatherIcon.CloudyIcon
import com.sunnyweather.android.logic.model.data.WeatherIcon.RainIcon
import com.sunnyweather.android.logic.model.data.WeatherIcon.SunnyIcon
import com.sunnyweather.android.ui.theme.WeatherType

class Sky(
    val info: String,
    val icon: @Composable () -> Unit,
    val anime_icon: @Composable () -> Unit,
    val bg: WeatherType
)

object WeatherCodeConverter {
    private val sky = mapOf(
        "CLEAR_DAY" to Sky("晴", SunnyIcon, SunnyAnimatableIcon, Weather.Sunny.background),
        "CLEAR_NIGHT" to Sky("晴", Weather.Sunny.icon, Weather.Sunny.animatableIcon, Weather.Sunny.background),
        "PARTLY_CLOUDY_DAY" to Sky("多云", Weather.Cloudy.icon, Weather.Cloudy.animatableIcon, Weather.Cloudy.background),
        "PARTLY_CLOUDY_NIGHT" to Sky("多云", Weather.Cloudy.icon, Weather.Cloudy.animatableIcon, Weather.Cloudy.background),
        "CLOUDY" to Sky("阴", Weather.Cloudy.icon, Weather.Cloudy.animatableIcon, Weather.Cloudy.background),
        "LIGHT_HAZE" to Sky("轻度雾霾", CloudyIcon, CloudyAnimatableIcon, Weather.Cloudy.background),
        "MODERATE_HAZE" to Sky("中度雾霾", CloudyIcon, CloudyAnimatableIcon, Weather.Cloudy.background),
        "HEAVY_HAZE" to Sky("重度雾霾", CloudyIcon, CloudyAnimatableIcon, Weather.Cloudy.background),
        "LIGHT_RAIN" to Sky("小雨", RainIcon, RainAnimatableIcon, Weather.Snowy.background),
        "MODERATE_RAIN" to Sky("中雨", RainIcon, RainAnimatableIcon, Weather.Snowy.background),
        "HEAVY_RAIN" to Sky("大雨", RainIcon, RainAnimatableIcon, Weather.Snowy.background),
        "STORM_RAIN" to Sky("暴雨", RainIcon, RainAnimatableIcon, Weather.Snowy.background),
        "FOG" to Sky("雾", CloudyIcon, CloudyAnimatableIcon, Weather.Cloudy.background),
        "LIGHT_SNOW" to Sky("小雪", Weather.Snowy.icon, Weather.Snowy.animatableIcon, Weather.Snowy.background),
        "MODERATE_SNOW" to Sky("中雪", Weather.Snowy.icon, Weather.Snowy.animatableIcon, Weather.Snowy.background),
        "HEAVY_SNOW" to Sky("大雪", Weather.Snowy.icon, Weather.Snowy.animatableIcon, Weather.Snowy.background),
        "STORM_SNOW" to Sky("暴雪", Weather.Snowy.icon, Weather.Snowy.animatableIcon, Weather.Snowy.background),
        "DUST" to Sky("浮尘", CloudyIcon, CloudyAnimatableIcon, Weather.Snowy.background),
        "SAND" to Sky("沙尘", CloudyIcon, CloudyAnimatableIcon, Weather.Snowy.background),
        "WIND" to Sky("大风", CloudyIcon, CloudyAnimatableIcon, Weather.Snowy.background)
    )
    fun getSky(skycon: String): Sky {
        return sky[skycon] ?: Sky("未知", SunnyIcon, SunnyAnimatableIcon, Weather.Sunny.background)
    }
}