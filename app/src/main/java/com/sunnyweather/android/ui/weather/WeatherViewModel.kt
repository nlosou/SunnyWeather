package com.sunnyweather.android.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunnyweather.android.log
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Location
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.HttpException

class WeatherViewModel:ViewModel() {
    private val locationFlowData= MutableStateFlow<Location>(Location("", ""))
    var locationLng=""
    var locationLat=""
    val placeName=""

    @OptIn(ExperimentalCoroutinesApi::class)
    val WeatherFlow=locationFlowData.flatMapLatest {
        query->
        "WeatherFlow".log(query.toString())
        Repository.RealWeather(query.lng,query.lng)
            .onEach {
                result ->
                result.onSuccess {
                    item->
                    "WeatherFlow_onSuccess".log(item[0].result.realtime.toString())
                }.onFailure {
                    "WeatherFlow".log(it.toString())
                    if (it is HttpException) {
                        "HTTP Exception: ".log("${it.response()?.errorBody()?.string()}")
                    } else {
                        "Other Exception: ".log("${it.message}")
                    }
                }
            }.catch {
                e->
                "WeatherFlow_catch".log(e.toString())
            }
    }

    fun SeacherWeather(lng:String,lat:String)
    {
        locationFlowData.value = Location(lng, lat)
    }
}