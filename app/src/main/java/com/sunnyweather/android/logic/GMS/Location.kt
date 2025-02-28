package com.sunnyweather.android.logic.GMS

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.sunnyweather.android.SunnyWeatherApplication.Companion.context
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.sunnyweather.android.log
import com.sunnyweather.android.ui.weather.WeatherViewModel


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationUpdates(
    fusedLocationClient: FusedLocationProviderClient,
    WeatherViewModel: WeatherViewModel
) {
    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    if (locationPermissionState.allPermissionsGranted) {
        // 定位权限已授予
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        if (WeatherViewModel.isRefreshRequested.value) {
            // 获取最新的定位信息
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    val latitude = it.latitude
                    val longitude = it.longitude
                    WeatherViewModel.SeacherWeather(longitude.toString(),latitude.toString())
                    "lastLocation".log(it.toString())
                    // 重置刷新状态
                    WeatherViewModel.isRefreshRequested.value=false
                }
            }
        }
    } else {
        // 请求定位权限
        LocationPermissionRequest(
            locationPermissionState = locationPermissionState
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun LocationPermissionRequest(
    locationPermissionState: MultiplePermissionsState
) {
    if (!locationPermissionState.allPermissionsGranted) {
        // 弹出对话框提示请求权限
        AlertDialog(
            onDismissRequest = { },
            title = { Text("权限请求") },
            text = { Text("需要定位权限以获取当前位置") },
            confirmButton = {
                Button(
                    onClick = { locationPermissionState.launchMultiplePermissionRequest() }
                ) {
                    Text("请求权限")
                }
            }
        )
    } else {
        // 权限已授予，显示定位信息
    }
}