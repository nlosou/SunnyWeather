package com.sunnyweather.android.ui.component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.ui.Anime.AnimatableSun
import com.sunnyweather.android.ui.Anime.Sun
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.myiconpack.Point

@Composable
fun Future_Weather_Cards( ) {
    Surface(color = Color.Transparent
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Text("今天")
            Text("2月27日")
            AnimatableSun(modifier = Modifier.size(50.dp))
            Spacer(modifier = Modifier.padding(25.dp))
            Box {
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Icon(MyIconPack.Point, contentDescription = "",modifier = Modifier.size(10.dp),)
                    Text("25°")

                }
            }
            Spacer(modifier = Modifier.padding(25.dp))
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