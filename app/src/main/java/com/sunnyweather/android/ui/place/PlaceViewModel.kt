package com.sunnyweather.android.ui.place

import android.view.animation.Transformation
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Place
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.http.Query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class PlaceViewModel :ViewModel(){
    private val searchQuery = MutableStateFlow<String>("")
    val placeList=ArrayList<Place>()
    @OptIn(ExperimentalCoroutinesApi::class)
    val placeFlow = searchQuery.flatMapLatest { query ->
        // 假设 Repository.searchPlace(query) 返回一个 Flow<List<Place>>
        Repository.searchPlace(query)
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


}