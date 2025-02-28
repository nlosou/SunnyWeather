package com.sunnyweather.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.sunnyweather.android.ui.layout.Greeting
import com.sunnyweather.android.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.layout.Greeting2
import com.sunnyweather.android.ui.place.PlaceViewModel
import com.sunnyweather.android.ui.weather.WeatherViewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: PlaceViewModel by viewModels()
    private val WeatherViewModel:WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SunnyWeatherTheme {
                Greeting(WeatherViewModel)
            }
        }
    }
}

