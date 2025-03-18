package com.sunnyweather.android.ui.component

import android.graphics.Color.rgb
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.log
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.myiconpack.Water



@Composable
fun Wind(name: String, modifier: Modifier = Modifier) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier.background(Color.Transparent)
    ) {
        val parentWidth = maxWidth
        val iconSize = parentWidth * 0.2f
        val textSize = (parentWidth.value * 0.15f).sp
        val strokeWidthDp = parentWidth * 0.07f

        Canvas(modifier = Modifier
            .fillMaxSize()
            .rotate(90f))
        {
            val strokeWidthPx = strokeWidthDp.toPx()
            val radius = size.minDimension / 2f - strokeWidthPx
            val center = Offset(drawContext.size.width / 2f, drawContext.size.height / 2f)
            // 基础圆弧[1](@ref)
            drawArc(
                color = Color.LightGray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
            )

            // 坐标系调整[3](@ref)
            val centerOffset = Offset(center.x, center.y)
            rotate(degrees = -90f, pivot = centerOffset) {
                // 刻度参数
                val majorStep = 10f     // 主刻度间隔
                val minorStep = 2f      // 次刻度间隔
                val majorTickLength = strokeWidthPx * 2
                val minorTickLength = strokeWidthPx * 1.2f

                // 绘制刻度[2](@ref)
                for (degree in 0 until 360 step minorStep.toInt()) {
                    val isMajor = (degree % majorStep).toInt() == 0
                    val tickLength = if (isMajor) majorTickLength else minorTickLength
                    val color = if (isMajor) Color.White else Color.Gray.copy(alpha = 0.7f)

                    drawLine(
                        color = color,
                        start = Offset(radius - tickLength, 0f),
                        end = Offset(radius, 0f),
                        strokeWidth = if (isMajor) strokeWidthPx else strokeWidthPx * 0.6f,
                        cap = StrokeCap.Round
                    )
                    rotate(
                        degrees = minorStep,
                        pivot = center,
                        block = {}
                    ) // 旋转画布到下一刻度位置
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview14() {
    SunnyWeatherTheme {
        Wind("Android")
    }
}