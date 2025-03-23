package com.sunnyweather.android.ui.layout

import Weather_other_info
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.sunnyweather.android.SunnyWeatherApplication.Companion.context
import com.sunnyweather.android.logic.model.data.WeatherDataProvider
import com.sunnyweather.android.log
import com.sunnyweather.android.logic.model.WeatherCodeConverter
import com.sunnyweather.android.ui.Anime.animateOffsetAndAlpha
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.AutoScrollText
import com.sunnyweather.android.ui.component.Future_Weather_Cards
import com.sunnyweather.android.ui.component.HourlyWeatherChart
import com.sunnyweather.android.ui.component.Weather_location_easy_information
import com.sunnyweather.android.ui.layout.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.myiconpack.Point
import com.sunnyweather.android.ui.place.PlaceViewModel
import com.sunnyweather.android.ui.theme.WeatherWallpaper
import com.sunnyweather.android.ui.weather.WeatherViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun Greeting(navController: NavController, WeatherViewModel:WeatherViewModel,mainViewModel: PlaceViewModel) {
    val weatherState by WeatherViewModel.state.collectAsState()
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()


    var isInitialLoading by remember { mutableStateOf(true) } // 新增加载状态

    // 添加一个状态变量来控制是否显示 Weather_other_info
    var showWeatherOtherInfo by remember { mutableStateOf(false) }

    // 当从 BaiduMap 返回时，通过导航回调更新这个状态
    LaunchedEffect(navController.currentBackStackEntry) {
        if (navController.currentBackStackEntry?.destination?.route == "greeting") {
            showWeatherOtherInfo = true
        }
    }


    val state = rememberPullToRefreshState()
    if (state.isRefreshing) {
        LaunchedEffect(true) {
            WeatherViewModel.SeacherWeather("","")
            WeatherViewModel.SeacherWeather(weatherState.locationLng,weatherState.locationLat)
            "LaunchedEffect".log("start")
            state.endRefresh()
        }
    }


    val updateIsExpanded = remember { mutableStateOf(false) }
    DisposableEffect(lifecycleOwner) {
        val job = scope.launch {
            WeatherViewModel.WeatherFlow
                .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { result ->
                    "WeatherFlow.collect".log("result: ${result.onSuccess { it.size }}")
                }
        }
        onDispose { job.cancel() }
    }

    LaunchedEffect(Unit) {
        if (mainViewModel.getSavedPlace().isNotEmpty()) {
            val place = mainViewModel.getSavedPlace()[0]
            mainViewModel.place_num.value = mainViewModel.getSavedPlace().size
            "MainActivity".log(place.toString())
            mainViewModel.place_name.value = place.formatted_address
            WeatherViewModel.SeacherWeather(place.lng.toString(), place.lat.toString())
            WeatherViewModel.fix_location(place.lat.toString(),place.lng.toString())
        } else {
            "MainActivity".log("没保存")
        }
        isInitialLoading=false
    }
    lateinit var fusedLocationClient: FusedLocationProviderClient
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    val (offset, alpha) = animateOffsetAndAlpha(weatherState.isExpanded)


    //左右滑页
    val pagerState = rememberPagerState( mainViewModel.place_current.value){mainViewModel.place_num.value} // 用于管理分页状态
    if(mainViewModel.getSavedPlace().isNotEmpty()){
        // 当页面改变时，更新 ViewModel 中的当前城市
        LaunchedEffect(pagerState.currentPage) {
            mainViewModel.place_current.value=pagerState.currentPage
            WeatherViewModel.SeacherWeather(mainViewModel.getSavedPlace()[pagerState.currentPage].lng.toString(),mainViewModel.getSavedPlace()[pagerState.currentPage].lat.toString())
            mainViewModel.place_name.value=mainViewModel.getSavedPlace()[pagerState.currentPage].formatted_address
        }
    }



    Box() {
        AnimatedVisibility(
            visible = isInitialLoading,
            enter = fadeIn(),
            exit = fadeOut(animationSpec = tween(durationMillis = 700))
        ){
            Loading()
        }
        AnimatedVisibility(
            visible = !isInitialLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ){
            Surface(
                modifier = Modifier
                    .fillMaxSize()
            ){
                if (weatherState.temp.isNotEmpty()) {
                    AnimatedContent(
                        targetState = weatherState.temp[0].result.realtime.skycon,
                        transitionSpec = {
                            // 淡入新内容，同时淡出旧内容
                            fadeIn(animationSpec = tween(400)) with fadeOut(animationSpec = tween(400))
                        }
                    ) { targetSkycon ->
                        WeatherWallpaper(WeatherCodeConverter.getSky(targetSkycon).bg)
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                )
                {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = Color.Transparent,
                        topBar = {
                            TopAppBar(
                                colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
                                title = {
                                    Box( // 使用 Box 包裹标题内容
                                        contentAlignment = Alignment.Center // 确保内容垂直居中
                                    ) {
                                        TopAppBarAddress(updateIsExpanded, mainViewModel, state)
                                    }
                                },
                                actions = {
                                    IconButton(onClick = {
                                        navController.navigate("Place_manage")
                                    }) {
                                        Icon(Icons.Filled.Add, contentDescription = "", modifier = Modifier.size(30.dp))
                                    }
                                    IconButton(onClick = {

                                    }) {
                                        Icon(Icons.Filled.MoreVert, contentDescription = "",modifier = Modifier.size(30.dp))
                                    }
                                }
                            )
                        },
                    ) { contentPadding ->
                        Box(modifier = Modifier.padding(contentPadding)
                            .pointerInput(Unit) {
                                detectVerticalDragGestures { change, dragAmount ->
                                    val verticalDragOffset = dragAmount
                                    // 根据垂直拖动的偏移量来控制weatherState.isExpanded的状态
                                    // 这里需要根据你的具体需求来实现
                                    if (verticalDragOffset > 0) {
                                        // 手指向上滑
                                        updateIsExpanded.value=false
                                    } else if (verticalDragOffset < 0) {
                                        // 手指向下滑
                                        updateIsExpanded.value=true
                                    }
                                }
                            }
                        ) {
                            AnimatedVisibility(
                                visible = !updateIsExpanded.value,
                                enter = fadeIn(animationSpec = tween(durationMillis = 400))+scaleIn(),
                                exit = fadeOut(animationSpec = tween(durationMillis = 400))+ scaleOut()
                            ){

                                Box()
                                {
                                    Column (
                                    ) {
                                        //天气实况
                                        HorizontalPager(
                                            state = pagerState,
                                            modifier = Modifier
                                        ){ page->
                                            WeatherViewModel.set_isSkycon(page)
                                            // 根据当前页面索引动态调整 visible 状态
                                            val isVisible2 = page == pagerState.currentPage
                                            WeatherViewModel.cacheWeather(isVisible2)
                                            AnimatedVisibility(
                                                visible = isVisible2,
                                                enter = fadeIn(animationSpec = tween(durationMillis = 400)) + scaleIn(initialScale = 0.5f),
                                                exit = fadeOut(animationSpec = tween(durationMillis = 400)) + scaleOut(targetScale = 0.5f)
                                            ){
                                                Weather_location_easy_information(
                                                    Modifier.padding(16.dp).wrapContentHeight(),
                                                    fusedLocationClient = fusedLocationClient,
                                                    WeatherViewModel,
                                                    mainViewModel
                                                )
                                            }

                                        }
                                        //每小时天气状况
                                        Card(
                                            modifier = Modifier
                                                .background(Color.Transparent)
                                                .alpha(if (weatherState.isExpanded) 0.1f else 1f),
                                            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                                            shape = RoundedCornerShape(0.dp)
                                        ) {

                                            // 获取模拟的 DailyWeather 数据
                                            if (weatherState.hourly.isNotEmpty()){
                                                val dailyWeather = WeatherDataProvider.dailyWeather.first()
                                                "dailyWeather".log(dailyWeather.toString())// 取第一个 DailyWeather
                                                HourlyWeatherChart(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    dailyWeather = dailyWeather,
                                                    WeatherViewModel
                                                )
                                            }else{

                                            }
                                        }
                                        //未来几天天气
                                        Card (
                                            Modifier
                                                .padding(3.dp),
                                            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                                            shape = RoundedCornerShape(0.dp)
                                        ){
                                            if (weatherState.daily.isNotEmpty()){
                                                val dailyWeather = WeatherDataProvider.dailyWeather.first()
                                                Future_Weather_Cards(WeatherViewModel,
                                                    dailyWeather
                                                )
                                            }else{

                                            }
                                        }
                                    }

                                    Box( // Box 布局，用于显示天气图标
                                        modifier = Modifier
                                            .align(Alignment.Center) // 图标水平居中
                                            .scale(6f)
                                            .offset(x=15.dp,y=-50.dp)
                                            .alpha(if (weatherState.isExpanded) 0f else 1f)
                                        // 图标缩放为 60%
                                    ){
                                        if (weatherState.temp.isNotEmpty()) {
                                            AnimatedContent(
                                                targetState = weatherState.temp[0].result.realtime.skycon,
                                                transitionSpec = {
                                                    // 淡入新内容，同时淡出旧内容
                                                    fadeIn(animationSpec = tween(300)) with fadeOut(animationSpec = tween(300))
                                                }
                                            ) { targetSkycon ->
                                                WeatherCodeConverter.getSky(targetSkycon).anime_icon()
                                            }
                                        }
                                    }
                                }
                            }
                            // 使用 HorizontalPager 实现左右滑动切换城市
                            AnimatedVisibility(
                                visible = updateIsExpanded.value,
                                enter = fadeIn(animationSpec = tween(durationMillis = 400))+slideIn { fullBounds ->
                                    IntOffset(0, fullBounds.height)
                                }  ,
                                exit = fadeOut(animationSpec = tween(durationMillis = 400))+slideOut { fullBounds ->
                                    IntOffset(0, fullBounds.height)
                                }
                            ){
                                HorizontalPager(
                                    state = pagerState,
                                    modifier = Modifier,
                                    flingBehavior = PagerDefaults.flingBehavior(
                                        state = pagerState,)
                                ){
                                    Weather_other_info(Modifier,navController,mainViewModel,WeatherViewModel)
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopAppBarAddress(
    updateIsExpanded: MutableState<Boolean>,
    mainViewModel: PlaceViewModel,
    state: PullToRefreshState
) {
    AnimatedVisibility(
        visible = updateIsExpanded.value,
        enter = fadeIn(animationSpec = tween(durationMillis = 400)),
        exit = fadeOut(animationSpec = tween(durationMillis = 400))
    ) {
        Column {
            Box() {
                Column() {
                    AutoScrollText(
                        text = if (mainViewModel._placeList.isNotEmpty()) {
                            mainViewModel.place_name.value
                        } else if (mainViewModel.getSavedPlace().isNotEmpty()) {
                            mainViewModel.place_name.value
                        } else {
                            "地址"
                        },
                        textSize = 24.toFloat() // 设置你的文本大小
                    )
                }
            }
            Box(
                Modifier
                    .nestedScroll(state.nestedScrollConnection)
                    .clipToBounds()) {
                LazyColumn(
                    Modifier
                ) {
                    if (!state.isRefreshing) {
                        items(1) {
                            LazyRow {
                                items(mainViewModel.place_num.value) {

                                    Icon(
                                        MyIconPack.Point,
                                        contentDescription = "",
                                        modifier = Modifier.size(15.dp),
                                        tint = if (mainViewModel.place_current.value == it) Color.White else Color.Black
                                    )
                                }
                            }
                        }
                    }
                }
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