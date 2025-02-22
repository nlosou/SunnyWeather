package com.sunnyweather.android.ui.component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme



@Composable
fun Surface_Card() {
        Surface(shape = RoundedCornerShape(8.dp),
            shadowElevation = 10.dp,
            modifier = Modifier.fillMaxWidth().height(100.dp).padding(horizontal = 15.dp, vertical = 7.dp),
            ){
                Column {

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween, // 水平两端对齐
                        verticalAlignment = Alignment.CenterVertically // 垂直居中
                    ){
                        Box(){
                            Text("City")
                        }
                        Box(){
                            IconButton(onClick = {

                            }) {
                                Icon(Icons.Filled.AddCircle, contentDescription = "", modifier = Modifier.size(100.dp))
                            }
                        }
                    }
                }


        }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    SunnyWeatherTheme {
        Surface_Card()
    }
}