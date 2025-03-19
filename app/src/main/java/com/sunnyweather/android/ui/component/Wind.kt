package com.sunnyweather.android.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
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
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun Wind(name: String, modifier: Modifier = Modifier) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier.background(Color.Transparent)
    ) {
        val parentWidth = maxWidth
        val parentHeight = maxHeight
        val iconSize = parentWidth * 0.2f
        val textSize = (parentWidth.value * 0.15f).sp
        val strokeWidthDp = parentWidth * 0.07f
        val scaleNum=80
        Canvas(modifier = Modifier
            .fillMaxSize()
            .rotate(90f))
        {
            val strokeWidthPx = strokeWidthDp.toPx()
            val radius = size.minDimension / 2f - strokeWidthPx
            val center = Offset(drawContext.size.width / 2f, drawContext.size.height / 2f)
            // 基础圆弧[1](@ref)
            // 绘制刻度
            var color=Color.Black
            repeat(scaleNum) { index ->
                val angle = 360f / scaleNum * index
                val startAngle = angle - 90f // 调整起始角度，使刻度从顶部开始
                val startRadius = (radius - strokeWidthPx*0.5f).toDp()
                val endRadius = (radius+strokeWidthPx*0.5f).toDp()
                val startX = center.x + (startRadius * cos(startAngle.toRadians())).toPx()
                val startY = center.y + (startRadius * sin(startAngle.toRadians())).toPx()
                val endX = center.x + (endRadius * cos(startAngle.toRadians())).toPx()
                val endY = center.y + (endRadius * sin(startAngle.toRadians())).toPx()
                if(index==0||index==20||index==40||index==60)
                {
                    color=Color.Black
                }else{
                    color=Color.LightGray
                }
                drawLine(
                    color = color,
                    start = Offset(startX, startY),
                    end = Offset(endX, endY),
                    strokeWidth = strokeWidthPx*0.1f
                )
            }
        }
    }
}
private fun Float.toRadians(): Float {
    return Math.toRadians(this.toDouble()).toFloat()
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview14() {
    SunnyWeatherTheme {
        Wind("Android")
    }
}