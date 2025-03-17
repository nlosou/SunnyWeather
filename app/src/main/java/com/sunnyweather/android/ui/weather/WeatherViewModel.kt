package com.sunnyweather.android.ui.weather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunnyweather.android.log
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Location
import com.sunnyweather.android.logic.model.RealtimeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.HttpException



data class WeatherState(
    val temp: List<RealtimeResponse> = emptyList(),
    val hourly: List<RealtimeResponse.Result.Hourly.Temperature> = emptyList(),
    val hourlySky: List<RealtimeResponse.Result.Hourly.Skycon> = emptyList(),
    val daily: List<RealtimeResponse.Result.Daily.Metrics> = emptyList(),
    val dailyWeather: List<RealtimeResponse.Result.Daily.Skycon> = emptyList(),
    var locationLng:String="",
    var locationLat:String="",
    var isExpanded: Boolean = false,
)

class WeatherViewModel:ViewModel() {
    private val locationFlowData= MutableStateFlow<Location>(Location("", ""))
    private val _state = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state.asStateFlow()
    var _hourly = mutableStateOf<List<RealtimeResponse.Result.Hourly.Temperature>>(emptyList())
    var _hourly_Sky = mutableStateOf<List<RealtimeResponse.Result.Hourly.Skycon>>(emptyList())
    @OptIn(ExperimentalCoroutinesApi::class)
    val WeatherFlow=locationFlowData.flatMapLatest {
        query->
        "WeatherFlow_location".log(query.toString())
        Repository.RealWeather(query.lng, query.lat)
            .flowOn(Dispatchers.IO)
            .map {
                result ->
                result.onSuccess {
                    item->
                    "WeatherFlow_onSuccess".log(item.toString())
                    _state.value = WeatherState(
                        temp = item ?: emptyList(),
                        hourly = item[0].result.hourly.temperature,
                        hourlySky = item[0].result.hourly.skycon,
                        daily = item[0].result.daily.temperature,
                        dailyWeather = item[0].result.daily.skycon,
                        locationLng = query.lng,
                        locationLat = query.lat,
                        isExpanded = _state.value.isExpanded
                    )
                    _hourly.value=_state.value.hourly
                    _hourly_Sky.value=_state.value.hourlySky
                }.onFailure {
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
    fun fix_location(lng:String,lat:String){
        _state.value.locationLng=lat
        _state.value.locationLat=lat
    }
    fun SeacherWeather(lng:String,lat:String)
    {
        locationFlowData.value = Location(lng, lat)
    }

    fun updateExpandedState(isExpanded: Boolean) {
        _state.value = _state.value.copy(isExpanded = isExpanded)
    }
    fun get__hourly():List<RealtimeResponse.Result.Hourly.Temperature>
    {
        return state.value.hourly
    }
    fun get__hourly_Sky():List<RealtimeResponse.Result.Hourly.Skycon>
    {
        return  state.value.hourlySky
    }
}