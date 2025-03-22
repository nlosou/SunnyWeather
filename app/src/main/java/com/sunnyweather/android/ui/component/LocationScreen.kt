package com.sunnyweather.android.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.baidu.mapapi.map.MapView
import com.sunnyweather.android.SunnyWeatherApplication.Companion.context
import com.sunnyweather.android.logic.GMS.LocationCollector
import com.sunnyweather.android.logic.GMS.LocationState

// Compose界面
@Composable
fun LocationScreen(Modifier: Modifier) {
    var mapView by remember { mutableStateOf<MapView?>(null) }
    var location2 by remember { mutableStateOf(LocationState()) }

    /*
    mapView?.let {
        LocationCollector(context, it) { newLocation ->
        location2 = newLocation
    }
    }
     */

        AndroidView(
            factory = { context ->
                MapView(context).apply {
                    map.isMyLocationEnabled = true
                }.also { mapView = it }
            },
            modifier = Modifier.fillMaxSize(),
            update = { view ->
                view.onResume() // 根据需要调用生命周期方法
            }
        )

    DisposableEffect(Unit) {
        onDispose {
            mapView?.onDestroy()
        }
    }
}

@Preview(showBackground=true)
@Composable
fun b()
{
    LocationScreen(Modifier)
}
