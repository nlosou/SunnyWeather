package com.sunnyweather.android.ui.component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import kotlinx.coroutines.delay


@Composable
fun RotatingArrow() {
    var angle by remember { mutableStateOf(0f) }
    val animatedAngle by animateFloatAsState(
        targetValue = angle,
        animationSpec = tween(durationMillis = 1000)
    )

    LaunchedEffect(Unit) {
        while (true) {
            angle = (angle + 360) % 360
            delay(1000)
        }
    }

    Canvas(
        modifier = Modifier
            .size(200.dp)
            .rotate(animatedAngle)
    ) {
        // 绘制箭头
        val arrowLength = 80.dp.toPx()
        val arrowHeadWidth = 20.dp.toPx()
        val arrowShaftWidth = 10.dp.toPx()

        // 绘制箭头主干
        drawLine(
            color = Color.Black,
            start = center,
            end = Offset(center.x + arrowLength, center.y),
            strokeWidth = arrowShaftWidth
        )

        // 绘制箭头头
        val arrowHeadPath = Path().apply {
            moveTo(center.x + arrowLength, center.y)
            lineTo(center.x + arrowLength - arrowHeadWidth, center.y - arrowHeadWidth)
            lineTo(center.x + arrowLength - arrowHeadWidth, center.y + arrowHeadWidth)
            close()
        }
        drawPath(
            path = arrowHeadPath,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview21() {
    SunnyWeatherTheme {
        RotatingArrow()
    }
}