package com.sunnyweather.android.logic.GMS

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption


// 定位状态类
data class LocationState(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val address: String = ""
)

// Compose 定位组件
@Composable
fun LocationCollector(
    context: Context,
   // mapView:MapView,
    onLocationUpdate: (LocationState) -> Unit

) {
    /*
     val locationClient = remember {
        LocationClient(context).apply {
            registerLocationListener { location ->
                val state = LocationState(
                    latitude = location.latitude,
                    longitude = location.longitude,
                    address = location.addrStr ?: "未知地址"
                )
                Log.d("registerLocationListener", location.street)

                onLocationUpdate(state)
                // 移动地图到当前位置
                /*
                mapView.map?.apply {
                    animateMapStatus(
                        MapStatusUpdateFactory.newLatLng(
                            LatLng(location.latitude, location.longitude)
                        )
                    )
                    MarkerOptions().position(LatLng(location.latitude, location.longitude))
                }
                 */

            }
        }
    }
     */

    val locationClient = remember {
        LocationClient(context).apply {
            // 使用新的监听器注册方式
            registerLocationListener(object : BDAbstractLocationListener() {
                override fun onReceiveLocation(location: BDLocation?) {
                    location?.let {
                        val state = LocationState(
                            latitude = it.latitude,
                            longitude = it.longitude,
                            address = it.addrStr ?: "未知地址"
                        )
                        Log.d("LocationUpdate", "坐标：${it.latitude}, ${it.longitude}")
                        Log.d("AddressInfo", "街道：${it}")
                        onLocationUpdate(state)
                    }
                }
            })
        }
    }

    // 处理权限
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        Log.d("permissions",permissions.toString())
        if (permissions.all { it.value }) {

            startLocation(locationClient)
        }
    }

    LaunchedEffect(Unit) {
        launcher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.INTERNET
            )
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            locationClient.stop()
        }
    }
}


// 启动定位
private fun startLocation(client: LocationClient) {
    val option = LocationClientOption().apply {
        setIsNeedAddress(true) // 必须开启地址解析
        locationMode = LocationClientOption.LocationMode.Hight_Accuracy
        setIsNeedLocationDescribe(true) // 补充地址描述信息
        setIsNeedLocationPoiList(true) // 获取周边POI信息
        openGps = true
        setScanSpan(20000)
    }
    client.locOption = option
    client.start()
}