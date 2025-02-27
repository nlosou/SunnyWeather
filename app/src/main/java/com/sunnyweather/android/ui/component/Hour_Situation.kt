package com.sunnyweather.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.myiconpack.Point


@Composable
fun Hour_Situation( modifier: Modifier) {
    Surface (modifier=Modifier.background(Color.Transparent).padding(5.dp),
        color = Color.Transparent ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box {
                Column {
                    Text("25°",style = TextStyle(
                        fontSize = 15.sp,
                    )
                    )
                    Icon(MyIconPack.Point, contentDescription = "",modifier = Modifier.size(10.dp),)
                }
            }
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.ic_clould),
                contentDescription = "Cloud icon"
            )
            Text("1 AM",style = TextStyle(
                fontSize = 13.sp,))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview10() {
    SunnyWeatherTheme {
        Box(
        modifier = Modifier.fillMaxSize()
        ){
            Hour_Situation(Modifier)
        }

    }
}