package com.sunnyweather.android.logic.network

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


//Retrofit构造器
object ServiceCreator {
    private const val BASE_URL="https://api.caiyunapp.com/"
    private val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create())
        .build()

    fun <T>create(serviceClass: Class<T>):T= retrofit.create(serviceClass)
    inline fun <reified T> create():T= create(T::class.java)

}
object ServiceCreator_2 {
    private const val BASE_URL = "https://www.yangmutea.email/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val placeService: PlaceService = retrofit.create(PlaceService::class.java)
}