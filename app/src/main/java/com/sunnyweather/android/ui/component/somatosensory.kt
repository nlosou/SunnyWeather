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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.myiconpack.Water
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun somatosensory(name: String, modifier: Modifier = Modifier) {
    var sweeAngle by remember { mutableStateOf(162f) }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.background(Color.Transparent)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "舒适",
                fontSize = 40.sp,
                fontWeight = FontWeight.Light,
                color = Color.LightGray,
                modifier = Modifier.offset(y=70.dp)
            )
        }
        Column(
            modifier = Modifier.size(300.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Canvas(modifier = Modifier
                .fillMaxSize().rotate(90f)) {
                // 手动计算中心点
                val center = Offset(drawContext.size.width / 2f, drawContext.size.height / 2f)
                val radius = drawContext.size.minDimension / 2f - 50.dp.toPx()
                // 定义渐变刷子
                // 绘制进度弧
                // 绘制背景圆
                // 绘制指针
                val pointerLength = radius - 30.dp.toPx()
                val angleInRadians = sweeAngle * Math.PI / 180
                val endPoint = Offset(
                    center.x + pointerLength * cos(angleInRadians.toFloat()),
                    center.y - pointerLength * sin(angleInRadians.toFloat())
                )
                drawCircle(
                    color = Color.Black,
                    center = Offset(drawContext.size.width / 2f, drawContext.size.height / 2f),
                    radius = drawContext.size.minDimension / 2 - 135.dp.toPx(),
                    style = Stroke(width = 10.dp.toPx())
                )
                drawLine(
                    color = Color.Black,
                    start = center,
                    end = endPoint,
                    strokeWidth = 8.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawArc(
                    color = Color(rgb(11,168,254)),
                    startAngle = 45f,
                    sweepAngle = 130f,
                    useCenter = false,
                    topLeft = Offset(drawContext.size.width / 2f - drawContext.size.minDimension / 2 + 10.dp.toPx(), drawContext.size.height / 2f - drawContext.size.minDimension / 2 + 10.dp.toPx()),
                    size = Size(drawContext.size.minDimension - 20.dp.toPx(), drawContext.size.minDimension - 20.dp.toPx()),
                    style = Stroke(width = 25.dp.toPx(), cap = StrokeCap.Round)
                )
                drawArc(
                    color = Color(rgb(26,219,114)),
                    startAngle = 185f,
                    sweepAngle = 45f,
                    useCenter = false,
                    topLeft = Offset(drawContext.size.width / 2f - drawContext.size.minDimension / 2 + 10.dp.toPx(), drawContext.size.height / 2f - drawContext.size.minDimension / 2 + 10.dp.toPx()),
                    size = Size(drawContext.size.minDimension - 20.dp.toPx(), drawContext.size.minDimension - 20.dp.toPx()),
                    style = Stroke(width = 25.dp.toPx(), cap = StrokeCap.Round)
                )
                drawArc(
                    color = Color(rgb(254,156,59)),
                    startAngle = 240f,
                    sweepAngle = 75f,
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
fun GreetingPreview14() {
    SunnyWeatherTheme {
        somatosensory("Android")
    }
}