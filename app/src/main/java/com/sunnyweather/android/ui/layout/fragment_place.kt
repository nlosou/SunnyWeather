package com.sunnyweather.android.ui.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sunnyweather.android.log
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.ui.component.SearchBar
import com.sunnyweather.android.ui.component.Surface_Card
import com.sunnyweather.android.ui.place.PlaceViewModel
import com.sunnyweather.android.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.weather.WeatherViewModel
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting2(navController: NavController, mainViewModel: PlaceViewModel, WeatherViewModel:WeatherViewModel) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                scope.launch {
                    mainViewModel.placeFlow.collect { result ->
                        // 这里可以处理结果，或者日志记录
                        "placeFlow.collect".log("result: $result")
                        // 你也可以在这里调用其他函数，例如更新状态等

                    }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                scope.launch {
                    WeatherViewModel.WeatherFlow.collect { result ->
                        "WeatherFlow.collect".log("result: $result")
                    }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(10.dp),
                title = {
                        SearchBar(mainViewModel,WeatherViewModel)
                },
                actions = {
                    Button(
                        onClick = { /* 按钮点击事件 */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0x0000000),   // 设置按钮背景颜色为白色
                            contentColor = Color(68,142,226)       // 设置按钮文本颜色为黑色
                        )
                    ) {
                        Text("取消")
                    }

                }
            )
        },



    ) { contentPadding ->
        // 主内容区域
        if(mainViewModel.text.value.isNotEmpty())
        {
            LazyColumn(modifier=Modifier.padding(contentPadding),
                horizontalAlignment = Alignment.CenterHorizontally // 设置所有子项水平居中
            ) {

                items(mainViewModel._placeList.size) { item ->

                    Surface_Card(item,mainViewModel,WeatherViewModel)
                }
            }
        }else{
            HotCityAndCurrentCity(Modifier.padding(contentPadding))
        }
        }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    val mainViewModel = remember { PlaceViewModel() }
    val WeatherViewModel= remember { WeatherViewModel() }
    val navController = rememberNavController()
    SunnyWeatherTheme {
        Greeting2(navController,mainViewModel,WeatherViewModel)
    }
}