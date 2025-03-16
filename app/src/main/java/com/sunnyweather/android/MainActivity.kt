package com.sunnyweather.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

        setContent {
            SunnyWeatherTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "greeting"  // 设置初始目的地
                ) {
                    composable("greeting",
                        enterTransition = {
                            scaleIn(animationSpec = tween(durationMillis = 600))+ fadeIn(animationSpec = tween(durationMillis = 600))
                        },
                        exitTransition = {
                            scaleOut(animationSpec = tween(durationMillis = 600))+ fadeOut(animationSpec = tween(durationMillis = 600))
                        },
                        popEnterTransition = {
                            scaleIn(animationSpec = tween(durationMillis = 600)) + fadeIn(animationSpec = tween(durationMillis = 600))
                        },
                        popExitTransition = {
                            scaleOut(animationSpec = tween(durationMillis = 600))+ fadeOut(animationSpec = tween(durationMillis = 600))
                        }
                        ) {  // 主页面
                        Greeting(navController,WeatherViewModel,mainViewModel)
                    }
                    composable("Place_manage",
                        enterTransition = {
                            scaleIn(animationSpec = tween(durationMillis = 600))+ fadeIn(animationSpec = tween(durationMillis = 600))
                        },
                        exitTransition = {
                            scaleOut(animationSpec = tween(durationMillis = 600))+ fadeOut(animationSpec = tween(durationMillis = 600))
                        },
                        popEnterTransition = {
                            scaleIn(animationSpec = tween(durationMillis = 600))+ fadeIn(animationSpec = tween(durationMillis = 600))
                        },
                        popExitTransition = {
                            scaleOut(animationSpec = tween(durationMillis = 600))+ fadeOut(animationSpec = tween(durationMillis = 600))
                        }
                        ) {  // 跳转页面
                        Place_manage(navController,mainViewModel,WeatherViewModel)
                    }
                    composable("Search_City",
                        enterTransition = {
                            scaleIn(animationSpec = tween(durationMillis = 600))+ fadeIn(animationSpec = tween(durationMillis = 600))
                        },
                        exitTransition = {
                            scaleOut(animationSpec = tween(durationMillis = 600))+ fadeOut(animationSpec = tween(durationMillis = 600))
                        },
                        popEnterTransition = {
                            scaleIn(animationSpec = tween(durationMillis = 600))+ fadeIn(animationSpec = tween(durationMillis = 600))
                        },
                        popExitTransition = {
                            scaleOut(animationSpec = tween(durationMillis = 600))+ fadeOut(animationSpec = tween(durationMillis = 600))
                        }
                    ) {  // 跳转页面
                        Greeting2(navController,mainViewModel,WeatherViewModel)
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

