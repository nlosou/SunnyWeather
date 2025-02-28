package com.sunnyweather.android.ui.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.sunnyweather.android.ui.Anime.AnimatableCloud
import com.sunnyweather.android.ui.Anime.AnimatableSun
import com.sunnyweather.android.ui.component.Alarm_Info
import com.sunnyweather.android.ui.component.Future_Weather_Cards
import com.sunnyweather.android.ui.component.Hour_Situation
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
    var isExpanded by remember { mutableStateOf(false) }
    val offset by animateDpAsState(targetValue = if (isExpanded) 250.dp else 0.dp, animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = ""
    )
    val valinscriptionarea by animateDpAsState(targetValue = if (isExpanded) 600.dp else 0.dp, animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = ""
    )
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ){
        WeatherWallpaper(WeatherType.SUNNY)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectVerticalDragGestures { _, verticalChange ->
                        if (verticalChange < 0) {
                            isExpanded = true
                        } else if (verticalChange > 0) {
                            isExpanded = false
                        }
                    }
                }
        )
        {

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
                        title = { },
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
                Box(modifier = Modifier.padding(contentPadding)) {
                    AnimatableSun(
                        modifier = Modifier.size(200.dp)
                            .align(Alignment.TopEnd)  // 主对齐控制[2](@ref)
                            .padding(end = 7.dp, bottom = 16.dp)
                    )   // 右侧留白)
                    ConstraintLayout(
                        modifier = Modifier.fillMaxWidth().offset(y = -offset)
                    ) {
                        val (BoxWith, Easy_Weather, Weather_Icon, future_caed, Alarm, hour_situation) = remember { createRefs() }
                        Weather_location_easy_information(
                            Modifier.padding(16.dp).constrainAs(Easy_Weather) {
                                top.linkTo(parent.top)

                            })
                        Card(
                            modifier = Modifier.background(Color.Transparent)
                                .constrainAs(hour_situation) {
                                    top.linkTo(Easy_Weather.bottom, margin = 50.dp)
                                }.alpha(if (isExpanded) 0.1f else 1f),
                            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                        ) {
                            LazyRow() {
                                items(24) { items ->
                                    Hour_Situation(Modifier)
                                }

                            }

                        }
                        BoxWithConstraints(
                            modifier = Modifier.constrainAs(BoxWith) {
                                top.linkTo(hour_situation.bottom, margin = 10.dp)
                                start.linkTo(parent.start) // 确保水平位置正确
                                end.linkTo(parent.end)
                            }.fillMaxSize()
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(maxWidth)
                                    .height(1.dp)
                                    .background(Color.LightGray.copy(alpha = 1f))
                            )
                        }

                        Card (Modifier.constrainAs(future_caed) {
                            bottom.linkTo(parent.bottom)
                        }.padding(3.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.5f))){
                            LazyRow() {
                                items(15) { item ->
                                    Future_Weather_Cards()
                                    // 在每个 Future_Weather_Cards 之后添加一个 Divider，除了最后一个
                                    if (item < 14) {
                                        Box(
                                            modifier = Modifier
                                                .height(250.dp) // 分割线高度
                                                .width(1.dp) // 分割线宽度
                                                .background(
                                                    brush = Brush.verticalGradient(
                                                        colors = listOf(
                                                            Color.LightGray.copy(alpha = 0.3f),
                                                            Color.LightGray,
                                                            Color.LightGray.copy(alpha = 0.3f)
                                                        )
                                                    )
                                                )
                                                .padding(horizontal = 1.dp) // 左右内边距
                                        )
                                    }


                                }


                            }
                        }

                    }

                }
                if (isExpanded){
                    Weather_other_info(Modifier.padding(contentPadding))
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