package com.sunnyweather.android.ui.component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun CircularScale() {
    val radius = 100.dp
    val numberOfTicks = 12
    val tickLength = 20.dp
    val tickWidth = 2.dp

    Canvas(
        modifier = Modifier
            .size(radius * 2)
            .padding(20.dp)
    ) {
        // 绘制刻度
        repeat(numberOfTicks) { index ->
            val angle = 360f / numberOfTicks * index
            val startAngle = angle - 90f // 调整起始角度，使刻度从顶部开始
            val startRadius = radius - tickLength
            val endRadius = radius
            val startX = center.x + (startRadius * cos(startAngle.toRadians())).toPx()
            val startY = center.y + (startRadius * sin(startAngle.toRadians())).toPx()
            val endX = center.x + (endRadius * cos(startAngle.toRadians())).toPx()
            val endY = center.y + (endRadius * sin(startAngle.toRadians())).toPx()

            drawLine(
                color = Color.Black,
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = tickWidth.toPx()
            )
        }

        // 绘制圆心
        drawCircle(
            color = Color.Black,
            radius = 5.dp.toPx(),
            center = center
        )
    }
}

private fun Float.toRadians(): Float {
    return Math.toRadians(this.toDouble()).toFloat()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview20() {
    SunnyWeatherTheme {
        CircularScale()
    }
}