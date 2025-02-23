package com.sunnyweather.android.ui.weather

import androidx.lifecycle.ViewModel
import com.sunnyweather.android.log
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Location
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach

class WeatherViewModel:ViewModel() {
    private val locationFlowData= MutableStateFlow<Location>(Location("", ""))
    val locationLng=""
    val locationLat=""
    val placeName=""

    @OptIn(ExperimentalCoroutinesApi::class)
    val WeatherFlow=locationFlowData.flatMapLatest {
        query->
        Repository.RealWeather(query.lng,query.lng)
            .onEach {
                result ->
                result.onSuccess {
                    item->
                    "WeatherFlow".log(item[0].result.realtime.toString())
                }.onFailure {
                    error->
                    "WeatherFlow".log(error.toString())
                }
            }.catch {
                e->
                "WeatherFlow".log(e.toString())
            }
    }
}