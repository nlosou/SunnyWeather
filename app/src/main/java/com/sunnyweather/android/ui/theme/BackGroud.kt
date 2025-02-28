package com.sunnyweather.android.ui.theme


import android.graphics.RadialGradient
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

// 天气类型枚举
enum class WeatherType {
    SUNNY, RAINY, SNOWY, CLOUDY
}

// 颜色主题配置
data class WeatherColors(
    val primary: Color,
    val secondary: Color,
    val particle: Color,
    val accent: Color
)

// 核心壁纸组件
@Composable
fun WeatherWallpaper(
    weatherType: WeatherType,
    modifier: Modifier = Modifier
) {
    val colors = when (weatherType) {
        WeatherType.SUNNY -> WeatherColors(
            primary = Color(0xFFFFF3E0),
            secondary = Color(0xFFFFB300),
            particle = Color(0xFFFF6D00),
            accent = Color(0xFFFF1744)
        )
        WeatherType.RAINY -> WeatherColors(
            primary = Color(0xFFE3F2FD),
            secondary = Color(0xFF2196F3),
            particle = Color(0xFF0D47A1),
            accent = Color(0xFF1565C0)
        )
        WeatherType.SNOWY -> WeatherColors(
            primary = Color(0xFFE0F7FA),
            secondary = Color(0xFFB2EBF2),
            particle = Color(0xFFFFFFFF),
            accent = Color(0xFF80DEEA)
        )
        WeatherType.CLOUDY -> WeatherColors(
            primary = Color(0xFFECEFF1),
            secondary = Color(0xFFB0BEC5),
            particle = Color(0xFF78909C),
            accent = Color(0xFF455A64)
        )
    }

    // 背景渐变
    val gradient = Brush.linearGradient(
        colors = listOf(colors.primary, colors.secondary),
        start = Offset.Zero, // 左上角
        end = Offset.Infinite // 右下角
    )

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .background(gradient)) {

            // 绘制天气特效
            when (weatherType) {
                WeatherType.SUNNY -> drawSunEffects(colors)
                WeatherType.RAINY -> drawSunEffects(colors)
                WeatherType.SNOWY -> drawSunEffects(colors)
                WeatherType.CLOUDY -> drawSunEffects(colors)
            }
        }
    }
}

// 晴天特效：光晕和光线
private fun DrawScope.drawSunEffects(colors: WeatherColors) {
    // 太阳光晕
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(colors.secondary.copy(alpha = 0.3f), Color.Transparent),
            center = center,
            radius = size.minDimension * 0.5f
        ),
        radius = size.minDimension * 0.5f,
        center = Offset(size.width * 0.8f, size.height * 0.2f)
    )

    //
}
@Preview
@Composable
fun PreviewWeatherWallpaper() {
    WeatherWallpaper(weatherType = WeatherType.SUNNY)
}


@Preview
@Composable
fun PreviewSunnyWallpaper() {
    WeatherWallpaper(weatherType = WeatherType.RAINY)
}

@Preview
@Composable
fun PreviewRainyWallpaper() {
    WeatherWallpaper(weatherType = WeatherType.CLOUDY)
}

@Preview
@Composable
fun PreviewSonwWallpaper() {
    WeatherWallpaper(weatherType = WeatherType.SNOWY)
}