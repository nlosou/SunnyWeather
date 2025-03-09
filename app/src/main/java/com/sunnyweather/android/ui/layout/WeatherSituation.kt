package com.sunnyweather.android.ui.layout

import Weather_other_info
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.sunnyweather.android.SunnyWeatherApplication.Companion.context
import com.sunnyweather.android.logic.model.data.WeatherDataProvider
import com.sunnyweather.android.log
import com.sunnyweather.android.logic.model.WeatherCodeConverter
import com.sunnyweather.android.ui.Anime.PagerState
import com.sunnyweather.android.ui.Anime.animateOffsetAndAlpha
import com.sunnyweather.android.ui.component.Future_Weather_Cards
import com.sunnyweather.android.ui.component.HourlyWeatherChart
import com.sunnyweather.android.ui.component.Weather_location_easy_information
import com.sunnyweather.android.ui.layout.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.place.PlaceViewModel
import com.sunnyweather.android.ui.theme.WeatherType
import com.sunnyweather.android.ui.theme.WeatherWallpaper
import com.sunnyweather.android.ui.weather.WeatherViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(navController: NavController, WeatherViewModel:WeatherViewModel,mainViewModel: PlaceViewModel) {
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                scope.launch {
                    WeatherViewModel.WeatherFlow.collect { result ->
                        try {
                            // 处理结果
                            "WeatherFlow.collect".log("result: ${result.onSuccess { it.size }}")
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    lateinit var fusedLocationClient: FusedLocationProviderClient
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    val (offset, alpha) = animateOffsetAndAlpha(WeatherViewModel.isExpanded.value)
    val pagerState = rememberPagerState( 0){mainViewModel.place_num.value} // 用于管理分页状态
    // 当页面改变时，更新 ViewModel 中的当前城市
    LaunchedEffect(pagerState.currentPage) {
        mainViewModel.place_current.value=pagerState.currentPage
        WeatherViewModel.SeacherWeather(mainViewModel.getSavedPlace()[pagerState.currentPage].lng.toString(),mainViewModel.getSavedPlace()[pagerState.currentPage].lat.toString())
    }
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
                            WeatherViewModel.isExpanded.value = true
                        } else if (verticalChange > 0) {
                            WeatherViewModel.isExpanded.value = false
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
                        title = {},
                        actions = {
                            IconButton(onClick = {
                                navController.navigate("greeting2")
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
                // 使用 HorizontalPager 实现左右滑动切换城市

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.padding(contentPadding),
                    flingBehavior = PagerDefaults.flingBehavior(state = pagerState)
                ){
                    Box(modifier = Modifier.alpha(if (WeatherViewModel.isExpanded.value) 0f else 1f))
                    {
                        if (WeatherViewModel.temp.value.isNotEmpty()){
                            Box( // Box 布局，用于显示天气图标
                                modifier = Modifier
                                    .align(Alignment.Center) // 图标水平居中
                                    .scale(6f)
                                    .offset(x=15.dp,y=-50.dp)
                                    .alpha(if (WeatherViewModel.isExpanded.value) 0f else 1f)

                                // 图标缩放为 60%
                            ){
                                WeatherCodeConverter.getSky(WeatherViewModel.temp.value[0].result.realtime.skycon).anime_icon()
                            }
                        }else{
                        }
                        // 右侧留白)
                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = -offset)
                        ) {
                            val (BoxWith, Easy_Weather, Weather_Icon, future_caed, Alarm, hour_situation) = remember { createRefs() }
                            Weather_location_easy_information(
                                Modifier
                                    .padding(16.dp)
                                    .constrainAs(Easy_Weather) {
                                        top.linkTo(parent.top)

                                    },
                                fusedLocationClient = fusedLocationClient,
                                WeatherViewModel,
                                mainViewModel
                            )
                            Card(
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .constrainAs(hour_situation) {
                                        top.linkTo(Easy_Weather.bottom, margin = 30.dp)
                                    }
                                    .alpha(if (WeatherViewModel.isExpanded.value) 0.1f else 1f),
                                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                            ) {
                                // 获取模拟的 DailyWeather 数据
                                if (WeatherViewModel.hourly.value.isNotEmpty()){
                                    val dailyWeather = WeatherDataProvider.dailyWeather.first()
                                    "dailyWeather".log(dailyWeather.toString())// 取第一个 DailyWeather
                                    HourlyWeatherChart(
                                        modifier = Modifier.fillMaxSize(),
                                        dailyWeather = dailyWeather,
                                        WeatherViewModel
                                    )
                                }else{

                                }
                            }
                            Card (
                                Modifier
                                    .constrainAs(future_caed) {
                                        bottom.linkTo(parent.bottom)
                                    }
                                    .padding(3.dp).offset(y=30.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                                shape = RoundedCornerShape(0.dp)
                            ){
                                if (WeatherViewModel.daily.value.isNotEmpty()){
                                    val dailyWeather = WeatherDataProvider.dailyWeather.first()
                                    Future_Weather_Cards(WeatherViewModel,
                                        dailyWeather
                                    )
                                }else{

                                }

                            }
                        }


                    }

                }
                //Weather_other_info(Modifier.padding(contentPadding) .alpha(alpha))
                        }
                    }
                }

            }
@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    val WeatherViewModel= remember { WeatherViewModel() }
    val mainViewModel= remember { PlaceViewModel() }
    val navController = rememberNavController()
    SunnyWeatherTheme {
        Greeting(navController,WeatherViewModel,mainViewModel)
    }

}