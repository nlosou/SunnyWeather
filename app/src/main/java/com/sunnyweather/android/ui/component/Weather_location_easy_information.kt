package com.sunnyweather.android.ui.component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.myiconpack.Leaf
import com.sunnyweather.android.ui.theme.SunnyWeatherTheme

class Weather_location_easy_information : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SunnyWeatherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting3(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Column {
        Text("地址")
        Box{

            Row {
                Text("开启位置服务，获得当前位置天气")
                Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "")
            }
        }
        Text("dhd\n" +
                "dhdh\n" +
                "dhd\n" +
                "\n")
        Row {
            Text("天气")
            Spacer(modifier = Modifier.padding(10.dp))
            Text("最高气温")
            Spacer(modifier = Modifier.padding(10.dp))
            Text("最低气温")
        }
        Button(onClick = {

        }) {
            Row {
                Icon(MyIconPack.Leaf, contentDescription = "")
                Spacer(modifier = Modifier.padding(2.dp))
                Text("空气质量")
                Spacer(modifier = Modifier.padding(2.dp))
                Text("空气质量的数字")
            }
        }

    }
}
@Composable
fun AnimatedLeaf() {
    // 无限循环动画控制器
    val infiniteTransition = rememberInfiniteTransition()

    // Y轴偏移动画（模拟飘动）
    val yOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 50f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 2000
                0f at 0 with LinearEasing
                25f at 500 with FastOutSlowInEasing
                50f at 1000
                25f at 1500
                0f at 2000
            },
            repeatMode = RepeatMode.Reverse
        )
    )

    // 旋转动画（模拟旋转飘落）
    val rotation by infiniteTransition.animateFloat(
        initialValue = -15f,
        targetValue = 15f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // 缩放动画（增强立体感）
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
    ) {
        Image(
            imageVector = MyIconPack.Leaf,
            contentDescription = "飘动的树叶",
            modifier = Modifier
                .size(48.dp)
                .offset(y = yOffset.dp)
                .rotate(rotation)
                .scale(scale)
                .graphicsLayer {
                    // 优化动画性能
                    renderEffect = null
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    SunnyWeatherTheme {
        Greeting3("Android")
        AnimatedLeaf()
    }
}