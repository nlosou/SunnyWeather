package com.sunnyweather.android.ui.weather

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunnyweather.android.log
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Location
import com.sunnyweather.android.logic.model.RealtimeResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.HttpException

class WeatherViewModel:ViewModel() {
    private val locationFlowData= MutableStateFlow<Location>(Location("", ""))
    var locationLng=""
    var locationLat=""
    val placeName=""
    val isRefreshRequested = mutableStateOf(false)
    var temp = mutableStateOf<List<RealtimeResponse>>(emptyList())
    var hourly = mutableStateOf<List<RealtimeResponse.Result.Hourly.Temperature>>(emptyList())
    var hourly_Sky = mutableStateOf<List<RealtimeResponse.Result.Hourly.Skycon>>(emptyList())
    @OptIn(ExperimentalCoroutinesApi::class)
    val WeatherFlow=locationFlowData.flatMapLatest {
        query->
        "WeatherFlow_location".log(query.toString())
        Repository.RealWeather(query.lng, query.lat)
            .onEach {
                result ->
                result.onSuccess {
                    item->
                    "WeatherFlow_onSuccess".log(item.toString())
                    "WeatherFlow_onSuccess_type".log(item::class.simpleName.toString())
                    temp.value = item ?: emptyList() // 使用默认值
                    hourly.value=item[0].result.hourly.temperature
                    hourly_Sky.value=item[0].result.hourly.skycon
                    "WeatherFlow_onSuccess".log(temp.value[0].result.daily.temperature[1].max.toString())
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

    @OptIn(ExperimentalCoroutinesApi::class)
    val WeatherFlow2=locationFlowData.flatMapLatest {
            query->
        "WeatherFlow".log(query.toString())
        Repository.RealWeather2()
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