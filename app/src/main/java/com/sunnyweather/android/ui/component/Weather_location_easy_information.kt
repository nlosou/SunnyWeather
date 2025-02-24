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
import androidx.compose.material.icons.filled.List
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


@Composable
fun Weather_location_easy_information(modifier: Modifier) {
    Column(modifier) {
        Text("地址")
        Box{
            Column {
                Row {
                    Text("开启位置服务，获得当前位置天气")
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "")
                }

                Icon(Icons.Filled.List, contentDescription = "")

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

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    SunnyWeatherTheme {
        Weather_location_easy_information(modifier = Modifier)
    }
}