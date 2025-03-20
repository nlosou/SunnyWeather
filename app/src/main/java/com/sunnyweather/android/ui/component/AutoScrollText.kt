package com.sunnyweather.android.ui.component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import kotlinx.coroutines.delay

@Composable
fun AutoScrollText(
    text: String,
    textSize: Float,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    var targetOffset by remember { mutableStateOf(0f) }
    val animatedOffset by animateFloatAsState(
        targetValue = targetOffset,
        animationSpec = tween(durationMillis = 1000)
    )

    // 获取文本宽度
    var textWidth by remember { mutableStateOf(0f) }
    // 获取容器宽度
    var containerWidth by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        // 如果文本宽度大于容器宽度，则启动自动滚动
        if (textWidth > containerWidth) {
            while (true) {
                targetOffset = -textWidth
                delay(1000) // 等待动画完成
                targetOffset = 0f
                delay(1000) // 等待动画完成
            }
        }
    }

    Text(
        text = text,
        style = TextStyle(
            fontSize = textSize.sp,
        ),
        maxLines = 1,
        overflow = TextOverflow.Visible,
        modifier = modifier
            .onSizeChanged { size ->
                containerWidth = size.width.toFloat()
            }
            .wrapContentSize(Alignment.TopStart)
            .horizontalScroll(scrollState)
            .onGloballyPositioned { layoutCoordinates ->
                textWidth = layoutCoordinates.size.width.toFloat()
            }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview26() {
    SunnyWeatherTheme {
        //AutoScrollText("Android")
    }
}