package com.sunnyweather.android.ui.component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme

@Composable
fun Future_Weather_Cards( ) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 10.dp,
        modifier = Modifier.width(100.dp).height(350.dp)
    ) {
        Column {
            Text("今天")
            Text("日期")
            Icon(Icons.Filled.Menu, contentDescription = "")
            Text("温度的上限")
            Spacer(modifier = Modifier.padding(50.dp))
            Text("温度的上限")
            Icon(Icons.Filled.Menu, contentDescription = "")
            Text("天气")
            Text("风速")
            Text("空气状况")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    SunnyWeatherTheme {
        Future_Weather_Cards()
    }
}