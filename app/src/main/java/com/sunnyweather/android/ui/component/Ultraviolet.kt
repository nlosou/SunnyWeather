package com.sunnyweather.android.ui.component

import android.graphics.Color.rgb
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Brush
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
fun Ultraviolet(name: String, modifier: Modifier = Modifier,weatherState:WeatherState) {
    val index=weatherState.temp[0].result.realtime.life_index.ultraviolet.index
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
    // 使用remember和mutableStateOf管理动态角度
    var targetAngle by remember { mutableStateOf(45f) }
    // 使用animateFloatAsState创建动画效果
    val animatedAngle by animateFloatAsState(
        targetValue = targetAngle,
        animationSpec = tween(durationMillis = 1000) // 动画时长1秒
    )
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
        Text(
            text = weatherState.temp[0].result.realtime.life_index.ultraviolet.index.toString(),
            fontSize=(parentWidth.value * 0.2f).sp,
            color = Color.White
        )

        Text(
            text = "UV",
            modifier = Modifier.offset(y=parentHeight*0.3f),
            fontSize = textSize,
            color = Color.LightGray
        )

        Canvas(modifier = Modifier
            .fillMaxSize()
            .rotate(90f))
        {
            // 将线条宽度转换为像素
            val strokeWidthPx = strokeWidthDp.toPx()
            val radius = size.minDimension / 2f - strokeWidthPx
            // 计算动态角度（考虑Canvas的90度旋转）
            //val dynamicAngle = 45f//45到335

            // 转换为数学坐标系弧度（）
            val adjustedAngle = animatedAngle*(index*0.5)
            val radians = Math.toRadians(adjustedAngle.toDouble())

            // 计算圆心坐标
            val x = center.x + radius * cos(radians).toFloat()
            val y = center.y + radius * sin(radians).toFloat()
            // 阴影参数
            val shadowColor = Color.LightGray // 阴影颜色
            val shadowBlurRadius = 10f // 阴影模糊半径
            val shadowOffset = Offset(2f, 2f) // 阴影偏移量
            //圆
            drawCircle(
                color = shadowColor,
                radius = strokeWidthPx*0.8f+shadowBlurRadius,
                center= Offset(x,y)
            )
            //刻度
            drawArc(
                brush  = Brush.sweepGradient(
                    colorStops = colorStops.toTypedArray(),
                    center = center,
                ),
                startAngle = 45f,
                sweepAngle = 270f,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
            )
            drawCircle(
                color = shadowColor,
                radius = strokeWidthPx*0.8f+shadowBlurRadius,
                center= Offset(x,y)
            )
            //圆
            drawCircle(
                color = colorStops[0].second,
                radius = strokeWidthPx*0.8f,
                center= Offset(x,y)
            )
        }
        // 添加一个按钮来改变dynamicAngle
        /*
        Button(
            onClick = {
                targetAngle = (targetAngle + 15) % 360
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("Change Angle")
        }
         */

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview18() {
    SunnyWeatherTheme {
       // Ultraviolet("Android")
    }
}