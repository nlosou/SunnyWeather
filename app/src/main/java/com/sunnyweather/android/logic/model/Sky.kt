package com.sunnyweather.android.logic.model

import androidx.compose.runtime.Composable
import com.sunnyweather.android.data.BgColors
import com.sunnyweather.android.data.WeatherAnimatableIcon.CloudyAnimatableIcon
import com.sunnyweather.android.data.WeatherAnimatableIcon.SunnyAnimatableIcon
import com.sunnyweather.android.data.WeatherBackground.CloudyBg
import com.sunnyweather.android.data.WeatherBackground.SnowBg
import com.sunnyweather.android.data.WeatherIcon.CloudyIcon
import com.sunnyweather.android.data.WeatherIcon.SunnyIcon

class Sky(
    val info:String,
    val icon: @Composable () -> Unit,
    val anime_icon: @Composable () -> Unit,
    val bg: BgColors
)
private val sky= mapOf(
    "CLEAR_DAY" to Sky("晴",SunnyIcon,SunnyAnimatableIcon,SnowBg),
    "CLEAR_NIGHT" to Sky("晴",SunnyIcon,SunnyAnimatableIcon,SnowBg),
    "PARTLY_CLOUDY_DAY" to Sky("多云",CloudyIcon,CloudyAnimatableIcon,CloudyBg)


)
