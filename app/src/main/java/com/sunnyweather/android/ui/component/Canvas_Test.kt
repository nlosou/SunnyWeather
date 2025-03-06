package com.sunnyweather.android.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.cuteweather.ui.theme.shapes
import com.sunnyweather.android.log
import com.sunnyweather.android.ui.theme.SunnyWeatherTheme


@Composable
fun LoadingProgressBar() {
    var sweeAngle by remember { mutableStateOf(162f) }
    Box(
        modifier = Modifier.size(375.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Loading",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.drawBehind {
                    drawRoundRect(
                        color = Color.LightGray,
                        topLeft = Offset.Zero,
                        size = Size(drawContext.size.width, drawContext.size.height),
                        cornerRadius= CornerRadius(50f)
                    )
                }
            )
            Text(
                text = "45%",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        androidx.compose.foundation.Canvas(modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)) {

            // 绘制背景圆
            drawCircle(
                color = Color(0xFF1E7171),
                center = Offset(drawContext.size.width / 2f, drawContext.size.height / 2f),
                radius = drawContext.size.minDimension / 2 - 10.dp.toPx(),
                style = Stroke(width = 20.dp.toPx())
            )

            // 绘制进度弧
            drawArc(
                color = Color(0xFF3BDCCE),
                startAngle = -90f,
                sweepAngle = sweeAngle,
                useCenter = false,
                topLeft = Offset(drawContext.size.width / 2f - drawContext.size.minDimension / 2 + 10.dp.toPx(), drawContext.size.height / 2f - drawContext.size.minDimension / 2 + 10.dp.toPx()),
                size = Size(drawContext.size.minDimension - 20.dp.toPx(), drawContext.size.minDimension - 20.dp.toPx()),
                style = Stroke(width = 20.dp.toPx(), cap = StrokeCap.Round)
            )
        }
    }
}

@Preview()
@Composable
fun g()
{
    SunnyWeatherTheme {
        LoadingProgressBar()
    }
}
