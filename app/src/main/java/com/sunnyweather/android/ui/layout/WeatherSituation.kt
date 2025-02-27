package com.sunnyweather.android.ui.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.sunnyweather.android.ui.Anime.AnimatableCloud
import com.sunnyweather.android.ui.component.Alarm_Info
import com.sunnyweather.android.ui.component.Future_Weather_Cards
import com.sunnyweather.android.ui.component.Weather_location_easy_information
import com.sunnyweather.android.ui.layout.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.theme.WeatherType
import com.sunnyweather.android.ui.theme.WeatherWallpaper

class WeatherSituation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SunnyWeatherTheme {

                    Greeting(

                    )

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting() {
    Box(
        modifier = Modifier.fillMaxSize() // 占据整个屏幕
    )
    {
        WeatherWallpaper(WeatherType.SUNNY)
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
                    title = {  },
                    actions = {
                        IconButton(onClick = {

                        }) {
                            Icon(Icons.Filled.Add, contentDescription = "")
                        }
                        IconButton(onClick = {

                        }) {
                            Icon(Icons.Filled.MoreVert, contentDescription = "")
                        }
                    }
                )
            },

            ) { contentPadding ->
            // 主内容区域
            Box (modifier = Modifier.padding(contentPadding)){
                AnimatableCloud(modifier = Modifier
                    .align(Alignment.TopEnd)  // 主对齐控制[2](@ref)
                    .padding(end = 7.dp, bottom = 16.dp)  )   // 右侧留白)
                ConstraintLayout(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val (Easy_Weather,Weather_Icon,future_caed,Alarm)= remember{createRefs()}
                    Weather_location_easy_information(Modifier.padding(16.dp).constrainAs(Easy_Weather){
                        top.linkTo(parent.top)

                    })
                    /*
                    Alarm_Info(Modifier.constrainAs(Alarm){
                        top.linkTo(Easy_Weather.bottom)
                    })


                    LazyRow(Modifier.align(Alignment.BottomStart)){items(15) {item->

                    Future_Weather_Cards()

                }
                     */




                    }


                }

            }

        }
    }


@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {

    SunnyWeatherTheme {
        Greeting()
    }
}