package com.sunnyweather.android.ui.component

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.LocationManager
import android.provider.Settings
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.location.FusedLocationProviderClient
import com.sunnyweather.android.SunnyWeatherApplication.Companion.context
import com.sunnyweather.android.log
import com.sunnyweather.android.logic.GMS.LocationUpdates
import com.sunnyweather.android.logic.model.WeatherCodeConverter
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.myiconpack.Leaf
import com.sunnyweather.android.ui.myiconpack.Point
import com.sunnyweather.android.ui.place.PlaceViewModel
import com.sunnyweather.android.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.weather.WeatherViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Weather_location_easy_information(
    modifier: Modifier,
    fusedLocationClient: FusedLocationProviderClient,
    WeatherViewModel: WeatherViewModel,
    mainViewModel: PlaceViewModel
) {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    // 是否授权了位置权限
    val isLocationPermissionGranted = remember {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
    // 是否开启了位置服务
    var isLocationEnabled by remember {
        mutableStateOf(
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                    locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        )
    }
    val state = rememberPullToRefreshState()
    if (state.isRefreshing) {
        LaunchedEffect(true) {
            // fetch something
           //delay(1500)
            WeatherViewModel.SeacherWeather("","")
            WeatherViewModel.SeacherWeather(WeatherViewModel.locationLng.value,WeatherViewModel.locationLat.value)
            "LaunchedEffect".log("start")
            state.endRefresh()
        }
    }
    Column(modifier) {
        Box() {
            Column() {
                Text(
                    if (mainViewModel._placeList.isNotEmpty()) {
                        mainViewModel.place_name.value
                    } else if (mainViewModel.getSavedPlace().isNotEmpty()) {
                        mainViewModel.place_name.value
                    } else {
                        "地址"

                    },
                    style = TextStyle(
                        fontSize = 24.sp,
                    )
                )
                /*
                        AnimatedVisibility(
                            visible = !isLocationEnabled, // 控制是否显示
                            enter = fadeIn(), // 渐显动画
                            exit = fadeOut()  // 渐隐动画
                        ) {
                            Row(modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.clickable {
                                        // 点击事件的逻辑处理

                                        // 跳转到设置页面开启定位服务
                                        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)

                                        context.startActivity(intent)
                                    }) {
                                    Text("开启位置服务，获得当前位置天气",fontSize = 10.sp,)
                                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight,  modifier = Modifier.size(10.dp),contentDescription = "")
                                }
                            }
                        }
                         */

                if (isLocationEnabled) {
                    // 显示定位信息
                    LocationUpdates(
                        fusedLocationClient = fusedLocationClient,
                        WeatherViewModel
                    )
                } else {
                    // 提示用户开启位置服务
                }
            }
        }
        Box(Modifier.nestedScroll(state.nestedScrollConnection).clipToBounds()) {
            LazyColumn(
                Modifier
            ) {
                if (!state.isRefreshing) {
                    items(1) {
                        LazyRow { items(mainViewModel.place_num.value){

                            Icon(MyIconPack.Point, contentDescription = "",modifier=Modifier.size(15.dp), tint = if(mainViewModel.place_current.value==it) Color.White else Color.Black)
                        }

                        }
                    }
                }
            }
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = state,
            )
        }

        TemperatureDisplay(
            if (WeatherViewModel.temp.value.isNotEmpty()) {
                WeatherViewModel.temp.value[0].result.realtime.temperature.toInt() ?: 0
            } else {
                0
            }
        )
        Row {
            Text(
                if (WeatherViewModel.temp.value.isNotEmpty()) {
                    WeatherCodeConverter.getSky(WeatherViewModel.temp.value[0].result.realtime.skycon).info
                } else {
                    "天气"
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))
            if (WeatherViewModel.temp.value.isNotEmpty()) {
                Text("最高${WeatherViewModel.temp.value[0].result.daily.temperature[0].max?.toInt()}°")
            } else {
                Text("最高气温")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            if (WeatherViewModel.temp.value.isNotEmpty()) {
                Text("最低${WeatherViewModel.temp.value[0].result.daily.temperature[0].min?.toInt()}°")
            } else {
                Text("最低气温")
            }

        }
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.LightGray.copy(0.5f)
        ) {
            Row() {
                Icon(MyIconPack.Leaf, contentDescription = "")
                Spacer(modifier = Modifier.padding(2.dp))
                Text("空气质量")
                Spacer(modifier = Modifier.padding(2.dp))
                Text("空气质量的数字")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    SunnyWeatherTheme {
        // Weather_location_easy_information( Modifier,FusedLocationProviderClient)
    }
}