package com.sunnyweather.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sunnyweather.android.logic.model.data.Hourly_data
import com.sunnyweather.android.ui.layout.Greeting
import com.sunnyweather.android.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.layout.Greeting2
import com.sunnyweather.android.ui.layout.Place_manage
import com.sunnyweather.android.ui.place.PlaceViewModel
import com.sunnyweather.android.ui.weather.WeatherViewModel

class MainActivity : ComponentActivity() {
     val mainViewModel: PlaceViewModel by viewModels()
    private val WeatherViewModel:WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Hourly_data.initialize(WeatherViewModel)
        if(mainViewModel.isPlaceSaved()){
            val place= mainViewModel.getSavedPlace()[0]
            mainViewModel.place_num.value=mainViewModel.getSavedPlace().size
            "MainActivity".log(place.toString())
            mainViewModel.place_name.value=place.formatted_address
            WeatherViewModel.SeacherWeather(place.lng.toString(),place.lat.toString())
            WeatherViewModel.locationLat.value=place.lat.toString()
            WeatherViewModel.locationLng.value=place.lng.toString()

        }
        setContent {
            SunnyWeatherTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "greeting"  // 设置初始目的地
                ) {
                    composable("greeting") {  // 主页面
                        Greeting(navController,WeatherViewModel,mainViewModel)
                    }
                    composable("Place_manage") {  // 跳转页面
                        Place_manage(navController,mainViewModel,WeatherViewModel)
                    }
                }
            }
        }
        "MainActivity".log("onCreate")
    }

    override fun onStart() {
        super.onStart()
        "MainActivity".log("onStart")
    }

    override fun onPause() {
        super.onPause()
        "MainActivity".log("onPause")
    }

    override fun onStop() {
        super.onStop()
        "MainActivity".log("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        "MainActivity".log("onDestroy")
    }

    override fun onResume() {
        super.onResume()
        try {
            // 执行操作
        } catch (e: Exception) {
            Log.e("MainActivity", "Error in onResume: ${e.message}")
        }
    }
}

