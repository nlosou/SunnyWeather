package com.sunnyweather.android.logic.network

import com.sunnyweather.android.SunnyWeatherApplication
import com.sunnyweather.android.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET("v2.6/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/weather?alert=true&dailysteps=7&hourlysteps=24")
    fun getRealtimeWeather(@Path("lng") lng:String,@Path("lat") lat:String): Call<RealtimeResponse>

    @GET("v2.6/${SunnyWeatherApplication.TOKEN}/107.916651,26.905278/weather?alert=true&dailysteps=1&hourlysteps=24")
    fun getRealtimeWeather2(): Call<RealtimeResponse>
}
