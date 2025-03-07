package com.sunnyweather.android.logic.network

import com.sunnyweather.android.SunnyWeatherApplication
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.Query
import retrofit2.http.GET


//API接口
interface PlaceService {
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun serachPlaces(@Query("query") query:String): Call<PlaceResponse>
    @GET("CSV/tableConvert.com_203upw.json")
    fun searchAdcodes(): Call<List<Place>>
}
