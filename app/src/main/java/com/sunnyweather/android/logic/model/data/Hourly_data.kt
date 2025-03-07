package com.sunnyweather.android.logic.model.data

import android.os.Build
import androidx.activity.viewModels
import com.sunnyweather.android.logic.model.Sky
import com.sunnyweather.android.logic.model.WeatherCodeConverter
import com.sunnyweather.android.ui.weather.WeatherViewModel
import java.time.LocalDate

class Hourly_data {
    companion object {
        private lateinit var weatherViewModel: WeatherViewModel

        fun initialize(viewModel: WeatherViewModel) {
            weatherViewModel = viewModel
        }

        val hourlyWeather: List<HourlyWeather2>
            get() = (0..23).map { index ->
                HourlyWeather2(
                    temperature = weatherViewModel.hourly.value?.get(index)?.value?.toInt() ?: 0,
                    weather = WeatherCodeConverter.getSky(weatherViewModel.hourly_Sky.value?.get(index)?.value.toString())
                )
            }
    }
}



