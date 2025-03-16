package com.sunnyweather.android.ui.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sunnyweather.android.log
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.City_Edit
import com.sunnyweather.android.ui.component.SearchBar_Onclick
import com.sunnyweather.android.ui.layout.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.myiconpack.ListSelect
import com.sunnyweather.android.ui.place.PlaceViewModel
import com.sunnyweather.android.ui.weather.WeatherViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Place_manage(navController: NavController, PlaceViewModel:PlaceViewModel, WeatherViewModel:WeatherViewModel) {
    val City_list_State by PlaceViewModel.__Place_State.collectAsState()
    val listState = rememberLazyListState()
    val shouldShowSearchBar by remember {
        derivedStateOf {
            // 如果正在滚动且第一条可见项不是第0条，则隐藏SearchBar
            listState.isScrollInProgress && listState.firstVisibleItemIndex != 0
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                            AnimatedVisibility(
                                visible = PlaceViewModel.show_edit.targetState,
                                enter = fadeIn(animationSpec = tween(durationMillis = 400)), // 进入时淡入
                                exit = fadeOut(animationSpec = tween(durationMillis = 400)) // 退出时淡出
                            ) {
                                IconButton(onClick = {
                                    PlaceViewModel.show_edit.targetState = false
                                }) {
                                    Icon(Icons.Filled.Clear, contentDescription = "")
                                }
                            }
                            AnimatedVisibility(
                                visible = !PlaceViewModel.show_edit.targetState,
                                enter = fadeIn(animationSpec = tween(durationMillis = 400)), // 进入时淡入
                                exit = fadeOut(animationSpec = tween(durationMillis = 400)) // 退出时淡出
                            ){
                                IconButton(onClick = {
                                    navController.popBackStack()
                                }, modifier = Modifier.padding(horizontal = 0.dp)) {
                                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                                }
                            }
                },
                actions = {
                    AnimatedVisibility(
                        visible = PlaceViewModel.show_edit.targetState,
                        enter = fadeIn(animationSpec = tween(durationMillis = 400)), // 进入时淡入
                        exit = fadeOut(animationSpec = tween(durationMillis = 400)) // 退出时淡出
                    ) {
                        IconButton(onClick = {

                        }) {
                            Icon(MyIconPack.ListSelect, contentDescription = "")
                        }
                    }
                }
            )
        },
        bottomBar = {
            AnimatedVisibility(
                visible = PlaceViewModel.show_edit.targetState,
                enter = slideInVertically(
                    initialOffsetY = {it},
                    animationSpec = tween(durationMillis = 400
                    )), // 进入时淡入
                exit = slideOutVertically(
                    targetOffsetY={it},
                    animationSpec = tween(durationMillis = 400
                    )) // 退出时淡出
            ){
                BottomAppBar(containerColor= Color.White) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center) {
                        IconButton(
                            onClick = {
                                City_list_State.Select_City.onEach {
                                    if(it.value)
                                    {
                                        PlaceViewModel.delete(it.key)
                                    }
                                }
                            },
                            modifier = Modifier.padding(0.dp) // 移除内边距
                        ) {
                            Icon(
                                Icons.Filled.Delete,
                                contentDescription = "Menu",
                                modifier = Modifier.padding(0.dp) // 确保 Icon 没有额外的内边距
                            )
                        }
                    }
                }
            }

        }
    ) { contentPadding ->
        // 主内容区域
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Column(){
                Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                    if(PlaceViewModel.show_edit.targetState){
                        Text("请选择项目",fontSize=35.sp)
                    }else{
                        Text("城市管理",fontSize=35.sp)
                    }
                    Spacer(modifier = Modifier.padding(vertical = 10.dp))
                    SearchBar_Onclick(navController,PlaceViewModel,WeatherViewModel,Modifier.fillMaxWidth())
                }
                LazyColumn(modifier = Modifier.padding(15.dp),
                    state = listState
                    ) {
                    items(PlaceViewModel.place_num.value){
                        City_Edit(navController,PlaceViewModel,WeatherViewModel,it)
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