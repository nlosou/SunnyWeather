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
    "LocationUpdates".log("start")
    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

}
