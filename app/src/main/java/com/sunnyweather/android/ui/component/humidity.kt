package com.sunnyweather.android.ui.component


import android.graphics.Color.rgb
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.log
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.myiconpack.Water
import com.sunnyweather.android.ui.weather.WeatherState


@Composable
fun Humidity_table(name: String, modifier: Modifier = Modifier,weatherState: WeatherState) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier.background(Color.Transparent)
    ) {
        // 使用remember和mutableStateOf管理动态角度
        var targetAngle by remember { mutableStateOf(0f) }
        // 使用animateFloatAsState创建动画效果
        val animatedAngle by animateFloatAsState(
            targetValue = targetAngle+270*weatherState.temp[0].result.realtime.humidity,
            animationSpec = tween(durationMillis = 1000) // 动画时长1秒
        )
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
        val comfortIndexMap = mapOf(
            0 to "闷热",
            1 to "酷热",
            2 to "很热",
            3 to "热",
            4 to "温暖",
            5 to "舒适",
            6 to "凉爽",
            7 to "冷",
            8 to "很冷",
            9 to "寒冷",
            10 to "极冷",
            11 to "刺骨的冷",
            12 to "湿冷",
            13 to "干冷"
        )
        Icon(
            MyIconPack.Water,
            contentDescription = "",
            modifier = Modifier.size(iconSize)
        )

        Text(
            text = when (weatherState.temp[0].result.realtime.life_index.comfort.index) {
                0 -> "闷热"
                1 -> "酷热"
                2 -> "很热"
                3 -> "热"
                4 -> "温暖"
                5 -> "舒适"
                6 -> "凉爽"
                7 -> "冷"
                8 -> "很冷"
                9 -> "寒冷"
                10 -> "极冷"
                11 -> "刺骨的冷"
                12 -> "湿冷"
                13 -> "干冷"
                else -> ""
            },
            modifier = Modifier.offset(y = parentHeight * 0.3f),
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
            //背景
            drawArc(
                color = Color.LightGray,
                startAngle = 45f,
                sweepAngle = 270f,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
            )

            //进度
            drawArc(
                color = Color(rgb(11, 168, 254)),
                startAngle = 45f,
                sweepAngle = animatedAngle,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview13() {
    SunnyWeatherTheme {
       // Humidity_table("Android")
    }
}