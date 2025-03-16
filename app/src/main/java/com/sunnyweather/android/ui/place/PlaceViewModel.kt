package com.sunnyweather.android.ui.place

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.log
import com.sunnyweather.android.logic.AdcodeService
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Place
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException

data class Place_State(
    val Select_City:MutableMap<Int,Boolean> = mutableMapOf()
)

class PlaceViewModel :ViewModel(){

    private val _Place_State=MutableStateFlow(Place_State())
    val __Place_State:StateFlow<Place_State> =_Place_State.asStateFlow()

    private val searchQuery = MutableStateFlow<String>("")
     val _placeList = mutableStateListOf<Place>() // 可观察的列表
    val place_name= mutableStateOf("")
    var place_num= mutableStateOf(0)
    var place_current= mutableStateOf(0)
    var show_edit= MutableTransitionState(false)
    @OptIn(ExperimentalCoroutinesApi::class)
    val placeFlow=searchQuery.flatMapLatest {
            query ->
        AdcodeService.searchPlacesByKeyword(query)
            .onEach {
                    result ->
                result.fold(
                    onSuccess = {
                        // 清空旧数据并添加新数据
                        _placeList.clear()
                        _placeList.addAll(it)
                        "placeList".log("${_placeList}")
                    },
                    onFailure = {
                        "WeatherFlow".log(it.toString())
                        if (it is HttpException) {
                            "HTTP Exception: ".log("${it.response()?.errorBody()?.string()}")
                        } else {
                            "Other Exception: ".log("${it.message}")
                        }
                    }
                )
            }.catch {
                    e ->
                "placeFlow caught exception".log(e.toString())
            }
    }

    fun searchPlaces(query: String)
    {
        searchQuery.value=query
    }

    // 定义一个可变状态变量，用于存储第一个文本框的值
    private val _text = mutableStateOf("")
    val text: State<String> = _text

    // 定义一个可变状态变量，用于存储第二个文本框的值
    private val _text2 = mutableStateOf("")
    val text2: State<String> = _text2

    // 设置第一个文本框的值
    fun setText(value: String) {
        _text.value = value
    }

    // 设置第二个文本框的值
    fun setText2(value: String) {
        _text2.value = value
    }

    fun savePlace(place: Place)=Repository.savePlace(place)
    fun getSavedPlace()=Repository.getSavedPlace()
    fun isPlaceSaved()=Repository.isPlaceSaved()


    fun toggleSelect(index: Int) {
        val currentState = _Place_State.value.Select_City[index] ?: false
        _Place_State.value = _Place_State.value.copy(
            Select_City = _Place_State.value.Select_City.toMutableMap().apply {
                this[index] = !currentState
            }
        )
    }

}