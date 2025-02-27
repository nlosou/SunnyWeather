package com.sunnyweather.android.ui.theme

import android.graphics.RadialGradient
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

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

// 波浪层配置
data class WaveLayerConfig(
    val amplitudeRatio: Float,  // 振幅比例
    val speed: Float,           // 速度系数
    val colors: List<Color>,    // 渐变颜色
    val startAngle: Float = 0f  // 初始角度
)

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

    // 波浪层配置（根据天气类型调整参数）
    val waveLayers = remember(weatherType) {
        when (weatherType) {
            WeatherType.SUNNY -> listOf(
                WaveLayerConfig(
                    amplitudeRatio = 0.4f,
                    speed = 1.2f,
                    colors = listOf(
                        colors.secondary.copy(alpha = 0.6f),
                        colors.accent.copy(alpha = 0.3f),
                        Color.Transparent
                    ),
                    startAngle = 45f
                ),
                WaveLayerConfig(
                    amplitudeRatio = 0.3f,
                    speed = 0.8f,
                    colors = listOf(
                        colors.accent.copy(alpha = 0.4f),
                        colors.primary.copy(alpha = 0.2f),
                        Color.Transparent
                    )
                )
            )
            WeatherType.RAINY -> listOf(
                WaveLayerConfig(
                    amplitudeRatio = 0.5f,
                    speed = 2f,
                    colors = listOf(
                        colors.secondary.copy(alpha = 0.5f),
                        colors.accent.copy(alpha = 0.3f),
                        Color.Transparent
                    )
                )
            )
            WeatherType.SNOWY -> listOf(
                WaveLayerConfig(
                    amplitudeRatio = 0.3f,
                    speed = 1.5f,
                    colors = listOf(
                        colors.particle.copy(alpha = 0.4f),
                        colors.primary.copy(alpha = 0.2f),
                        Color.Transparent
                    )
                )
            )
            WeatherType.CLOUDY -> listOf(
                WaveLayerConfig(
                    amplitudeRatio = 0.4f,
                    speed = 1f,
                    colors = listOf(
                        colors.secondary.copy(alpha = 0.5f),
                        colors.accent.copy(alpha = 0.3f),
                        Color.Transparent
                    )
                )
            )
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        // 背景渐变
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(colors.primary, colors.secondary),
                        startY = 0f,
                        endY = LocalDensity.current.run { 800.dp.toPx() }
                    )
                )
        ) {
            // 绘制天气特效
            when (weatherType) {
                WeatherType.SUNNY -> drawSunEffects(colors)
                WeatherType.RAINY -> drawRainEffects(colors)
                WeatherType.SNOWY -> drawSnowEffects(colors)
                WeatherType.CLOUDY -> drawCloudEffects(colors)
            }
        }

        // 动态波浪层
        WaveGradient(waveLayers = waveLayers)
    }
}

@Composable
private fun WaveGradient(waveLayers: List<WaveLayerConfig>) {
    val infiniteTransition = rememberInfiniteTransition()
    val animationProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
    ) {
        waveLayers.forEach { layer ->
            drawWaveLayer(
                progress = animationProgress,
                config = layer,
                size = size
            )
        }
    }
}

private fun DrawScope.drawWaveLayer(
    progress: Float,
    config: WaveLayerConfig,
    size: Size
) {
    // 计算动画参数
    val amplitude = size.height * config.amplitudeRatio
    val phase = progress * 2 * PI.toFloat() * config.speed
    val angle = config.startAngle * PI.toFloat() / 180f

    // 创建波浪路径
    val path = Path().apply {
        moveTo(0f, size.height)
        lineTo(0f, size.height / 2)

        for (x in 0..size.width.toInt()) {
            val y = size.height / 2 +
                    amplitude * sin(phase + (x * 0.01f + angle))
            if (x == 0) moveTo(x.toFloat(), y)
            else lineTo(x.toFloat(), y)
        }

        lineTo(size.width, size.height)
        close()
    }

    // 绘制波浪渐变
    drawPath(
        path = path,
        brush = Brush.linearGradient(
            colors = config.colors,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            tileMode = TileMode.Mirror
        ),
        alpha = 0.6f
    )
}

// 晴天特效
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
}

// 其他天气特效（示例）
private fun DrawScope.drawRainEffects(colors: WeatherColors) {
    // 雨滴绘制逻辑
}

private fun DrawScope.drawSnowEffects(colors: WeatherColors) {
    // 雪花绘制逻辑
}

private fun DrawScope.drawCloudEffects(colors: WeatherColors) {
    // 云层绘制逻辑
}

@Preview
@Composable
fun PreviewSunnyWallpaper() {
    WeatherWallpaper(weatherType = WeatherType.SUNNY)
}

@Preview
@Composable
fun PreviewRainyWallpaper() {
    WeatherWallpaper(weatherType = WeatherType.RAINY)
}