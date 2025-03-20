package com.sunnyweather.android.ui.component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme


@Composable
fun TemperatureDisplay(temperature: Int,fontSize:TextUnit) {
    // 根据温度动态调整颜色


    // 显示温度
    Text(modifier = Modifier.offset(x=-7.dp),
        text = "$temperature°",
        fontSize = fontSize, // 字体大小

    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview9() {
    SunnyWeatherTheme {
        //TemperatureDisplay(9)
    }
}