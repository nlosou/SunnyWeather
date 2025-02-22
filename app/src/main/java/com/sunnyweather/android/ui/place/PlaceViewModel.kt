package com.sunnyweather.android.ui.place

import android.view.animation.Transformation
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


}