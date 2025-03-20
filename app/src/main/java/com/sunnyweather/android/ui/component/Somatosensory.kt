package com.sunnyweather.android.ui.component

import android.graphics.Color.rgb
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.log
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.myiconpack.Water
import com.sunnyweather.android.ui.weather.WeatherState
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun Somatosensory(name: String, modifier: Modifier = Modifier,weatherState: WeatherState) {
    var sweeAngle by remember { mutableStateOf(-45f) }
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier.background(Color.Transparent)
    ) {
        // 获取父容器约束
        val parentWidth = maxWidth
        val parentHeight = maxHeight
        "parentWidth".log(parentWidth.value.toString())
        // 按父容器宽度 20% 设置图标尺寸
        val iconSize = parentWidth * 0.2f
        // 按父容器宽度 8% 设置字体大小
        val textSize = (parentWidth.value * 0.15f).sp
        // 按父容器宽度 3% 设置线条宽度
        val strokeWidthDp = parentWidth * 0.07f
        // 使用animateFloatAsState创建动画效果
        val animatedAngle by animateFloatAsState(
            targetValue = sweeAngle,
            animationSpec = tween(durationMillis = 1000) // 动画时长1秒
        )

        Text(
            text = "舒适",
            modifier = Modifier.offset(y=parentHeight*0.3f),
            fontSize = textSize,
            color = Color.Gray
        )

        Canvas(modifier = Modifier
            .fillMaxSize()
            .rotate(90f))
        {
            // 将线条宽度转换为像素
            val strokeWidthPx = strokeWidthDp.toPx()
            val radius = size.minDimension / 2f - strokeWidthPx
            val pointerLength = radius*0.7f
            val center = Offset(drawContext.size.width / 2f, drawContext.size.height / 2f)
            val angleInRadians = (animatedAngle+90f) * Math.PI / 180
            val endPoint = Offset(
                center.x + pointerLength * cos(angleInRadians.toFloat()),
                center.y - pointerLength * sin(angleInRadians.toFloat())
            )
            //进度
            drawCircle(
                color = Color.White,
                radius = strokeWidthPx*0.8f,
                style = Stroke(width = strokeWidthPx*0.4f)
            )
            drawLine(
                color = Color.White,
                start = center,
                end = endPoint,
                strokeWidth = strokeWidthPx*0.4f,
                cap = StrokeCap.Round
            )

            //进度
            drawArc(
                color = Color(rgb(11, 168, 254)),
                startAngle = 45f,
                sweepAngle = 135f,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
            )

            drawArc(
                color = Color(rgb(254,156,59)),
                startAngle = 240f,
                sweepAngle = 75f,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
            )
            drawArc(
                color = Color(rgb(26,219,114)),
                startAngle = 184f,
                sweepAngle = 60f,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Square)
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun GreetingPreview19() {
    SunnyWeatherTheme {
        //Somatosensory("Android")
    }
}