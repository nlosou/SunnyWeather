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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.log
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.myiconpack.Water


@Composable
fun Somatosensory(name: String, modifier: Modifier = Modifier) {
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
        Icon(
            MyIconPack.Water,
            contentDescription = "",
            modifier = Modifier.size(iconSize)
        )

        Text(
            text = "舒适",
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
                sweepAngle = 180f,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun GreetingPreview19() {
    SunnyWeatherTheme {
        Somatosensory("Android")
    }
}