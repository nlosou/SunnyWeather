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
import androidx.compose.runtime.collectAsState
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
    val weatherState by WeatherViewModel.state.collectAsState()
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
            WeatherViewModel.SeacherWeather("","")
            WeatherViewModel.SeacherWeather(weatherState.locationLng,weatherState.locationLat)
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
            if (weatherState.temp.isNotEmpty()) {
                weatherState.temp[0].result.realtime.temperature.toInt() ?: 0
            } else {
                0
            }
        )
        Row {
            Text(
                if (weatherState.temp.isNotEmpty()) {
                    WeatherCodeConverter.getSky(weatherState.temp[0].result.realtime.skycon).info
                } else {
                    "天气"
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))
            if (weatherState.temp.isNotEmpty()) {
                Text("最高${weatherState.temp[0].result.daily.temperature[0].max?.toInt()}°")
            } else {
                Text("最高气温")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            if (weatherState.temp.isNotEmpty()) {
                Text("最低${weatherState.temp[0].result.daily.temperature[0].min?.toInt()}°")
            } else {
                Text("最低气温")
            }

        }
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.LightGray.copy(0.5f)
        ) {
            Row(verticalAlignment=Alignment.CenterVertically) {
                Icon(MyIconPack.Leaf, contentDescription = "")
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    if (weatherState.temp.isNotEmpty()) {
                        when (weatherState.temp[0].result.realtime.air_quality.aqi.chn) {
                            in 0..50 -> "优"
                            in 51..100 -> "良"
                            in 101..150->"轻度污染"
                            in 151..200->"中度污染"
                            in 201..300->"重度污染"
                            else ->"严重污染"
                        }
                    } else {
                        ""
                    }
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(if (weatherState.temp.isNotEmpty()) weatherState.temp[0].result.realtime.air_quality.aqi.chn.toString() else{"缺少数据"})
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