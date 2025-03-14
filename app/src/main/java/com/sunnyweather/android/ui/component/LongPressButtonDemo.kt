package com.sunnyweather.android.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.place.PlaceViewModel
import com.sunnyweather.android.ui.weather.WeatherViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LongPressButtonDemo() {
    var showNormalButtons by remember { mutableStateOf(true) }
    var showLongPressButtons by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .combinedClickable(
                onClick = {
                    // 单击事件
                    showNormalButtons = true
                    showLongPressButtons = false
                },
                onLongClick = {
                    // 长按事件
                    showNormalButtons = false
                    showLongPressButtons = true
                }
            )
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        ) {
            if (showNormalButtons) {
                Button(
                    onClick = { /* 按钮 1 的点击事件 */ },
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(text = "按钮 1", fontSize = 16.sp)
                }
                Button(
                    onClick = { /* 按钮 2 的点击事件 */ },
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(text = "按钮 2", fontSize = 16.sp)
                }
            }

            if (showLongPressButtons) {
                Button(
                    onClick = { /* 长按后出现的按钮 3 的点击事件 */ },
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(text = "按钮 3", fontSize = 16.sp)
                }
                Button(
                    onClick = { /* 长按后出现的按钮 4 的点击事件 */ }
                ) {
                    Text(text = "按钮 4", fontSize = 16.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview17() {
    SunnyWeatherTheme {
        LongPressButtonDemo()
    }
}