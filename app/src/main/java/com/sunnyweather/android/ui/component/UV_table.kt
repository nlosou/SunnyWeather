package com.sunnyweather.android.ui.component

import android.graphics.Color.rgb
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme


@Composable
fun UV_table(name: String, modifier: Modifier = Modifier) {
    var sweeAngle by remember { mutableStateOf(162f) }
    val colorStops = listOf(
        0.1f to Color(rgb(62, 224, 182)), // 浅绿色
        0.2f to Color(rgb(25, 228, 120)), // 绿色
        0.3f to Color(rgb(227, 223, 24)), // 浅黄色
        0.4f to Color(0xFFFFEB3B), // 黄色
        0.5f to Color(0xFFFF9800), // 橙色
        0.6f to Color(rgb(255, 100, 85)), // 橙红色
        0.7f to Color(0xFFF44336), // 红色
        0.8f to Color(rgb(241, 82, 172)), // 粉紫色
        0.9f to Color(0xFF9C27B0), // 紫色
        1.0f to Color(rgb(139, 102, 243)) // 蓝紫色
    )

    Box(contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "4",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            Canvas(modifier = Modifier
                .fillMaxSize().rotate(90f)) {
                // 手动计算中心点
                val center = Offset(drawContext.size.width / 2f, drawContext.size.height / 2f)
                val radius = drawContext.size.minDimension / 2f - 50.dp.toPx()
                // 定义渐变刷子
                // 绘制进度弧
                drawArc(
                    brush  = Brush.sweepGradient(
                        colorStops = colorStops.toTypedArray(),
                        center = center,
                    ), // 使用渐变色
                    startAngle = 45f,
                    sweepAngle = 270f,
                    useCenter = false,
                    topLeft = Offset(drawContext.size.width / 2f - drawContext.size.minDimension / 2 + 10.dp.toPx(), drawContext.size.height / 2f - drawContext.size.minDimension / 2 + 10.dp.toPx()),
                    size = Size(drawContext.size.minDimension - 20.dp.toPx(), drawContext.size.minDimension - 20.dp.toPx()),
                    style = Stroke(width = 25.dp.toPx(), cap = StrokeCap.Round)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview12() {
    SunnyWeatherTheme {
        UV_table("Android")
    }
}