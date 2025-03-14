package com.sunnyweather.android.ui.place

import android.view.animation.Transformation
import androidx.collection.mutableIntListOf
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.log
import com.sunnyweather.android.logic.AdcodeService
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Place
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.http.Query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import kotlin.math.log



class PlaceViewModel :ViewModel(){
    private val searchQuery = MutableStateFlow<String>("")
     val _placeList = mutableStateListOf<Place>() // 可观察的列表
    val place_name= mutableStateOf("")
    var place_num= mutableStateOf(0)
    var place_current= mutableStateOf(0)
    var show_edit= mutableStateOf(false)
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
}