package com.sunnyweather.android.ui.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Preview
import com.sunnyweather.android.ui.layout.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.component.Surface_Card

@Composable
fun HotCityAndCurrentCity(modifier: Modifier) {
    Column(modifier=modifier.fillMaxSize()) {
        Text("当前城市")
        Button(onClick = {}, modifier = Modifier.fillMaxWidth(),
colors = ButtonDefaults.buttonColors(containerColor = Color(240,240,240),
    contentColor = Color(68,142,226))
            ) {
            Text("定位")
        }
        Text("热门城市")

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SunnyWeatherTheme {
        HotCityAndCurrentCity(modifier=Modifier)
    }
}