package com.sunnyweather.android.ui.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sunnyweather.android.ui.layout.ui.theme.SunnyWeatherTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sunnyweather.android.ui.component.LocationScreen
import com.sunnyweather.android.ui.place.PlaceViewModel
import com.sunnyweather.android.ui.weather.WeatherViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaiduMap(navController: NavController, mainViewModel: PlaceViewModel, WeatherViewModel: WeatherViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        IconButton(onClick = {
                            navController.popBackStack()
                            navController.navigate("greeting"){
                                launchSingleTop=true
                            }
                        }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                        }

                    }
                }
            )
        },
        floatingActionButton = {
        },
        bottomBar = {
        }
    ) { contentPadding ->
        BoxWithConstraints(modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)) {
            val parentHeight=maxHeight
            val parentWight=maxWidth
            // 主内容区域
            Box(

            ) {
                Column {
                    Text("降水预报", fontSize = (parentWight.value*0.08f).sp, modifier = Modifier.offset(x=parentWight*0.04f))
                    Text("地址",fontSize = (parentWight.value*0.05f).sp, modifier = Modifier.offset(x=parentWight*0.04f))
                    LocationScreen(Modifier)
                }

            }
        }

    }
}
