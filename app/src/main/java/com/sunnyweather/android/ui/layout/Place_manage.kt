package com.sunnyweather.android.ui.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sunnyweather.android.ui.component.City_Edit
import com.sunnyweather.android.ui.component.SearchBar
import com.sunnyweather.android.ui.component.SearchBar_Onclick
import com.sunnyweather.android.ui.layout.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.place.PlaceViewModel
import com.sunnyweather.android.ui.weather.WeatherViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Place_manage(navController: NavController, PlaceViewModel:PlaceViewModel, WeatherViewModel:WeatherViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                            AnimatedVisibility(
                                visible = PlaceViewModel.show_edit.targetState,
                                enter = fadeIn(animationSpec = tween(durationMillis = 600)), // 进入时淡入
                                exit = fadeOut(animationSpec = tween(durationMillis = 600)) // 退出时淡出
                            ) {
                                IconButton(onClick = {
                                    PlaceViewModel.show_edit.targetState = false
                                }) {
                                    Icon(Icons.Filled.Clear, contentDescription = "")
                                }
                            }
                            AnimatedVisibility(
                                visible = !PlaceViewModel.show_edit.targetState,
                                enter = fadeIn(animationSpec = tween(durationMillis = 600)), // 进入时淡入
                                exit = fadeOut(animationSpec = tween(durationMillis = 600)) // 退出时淡出
                            ){
                                IconButton(onClick = {
                                }, modifier = Modifier.padding(horizontal = 0.dp)) {
                                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                                }
                            }
                }
            )

        },
    ) { contentPadding ->
        // 主内容区域
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Column(){
                Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                    Box(modifier = Modifier) {
                        Text("城市管理",fontSize=35.sp)
                    }
                    Spacer(modifier = Modifier.padding(vertical = 10.dp))
                    SearchBar_Onclick(PlaceViewModel,WeatherViewModel,Modifier.fillMaxWidth())
                }
                LazyColumn(modifier = Modifier.padding(15.dp)) {
                    items(PlaceViewModel.place_num.value){
                        City_Edit(PlaceViewModel,WeatherViewModel,it)
                        Spacer(Modifier.padding(vertical = 6.dp))
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview10() {
    SunnyWeatherTheme {
        val navController = rememberNavController()
        val a= remember { PlaceViewModel() }
        val b= remember { WeatherViewModel() }
        Place_manage(navController,a,b)
    }
}