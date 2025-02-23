package com.sunnyweather.android.logic.network

import com.sunnyweather.android.log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


//统一的网络数据源访问入口
object SunnyWeatherNetwork {
    private val placeService=ServiceCreator.create<PlaceService>()
    private val weatherService=ServiceCreator.create<WeatherService>()
    //封装WeatherService
    suspend fun getRealWeather(lng:String,lat:String)= weatherService.getRealtimeWeather(lng,lat).await()

    suspend fun searchPlaces(query:String)= placeService.serachPlaces(query).await()



    private suspend fun <T> Call<T>.await():T{
        "SunnyWeatherNetwork".log("start")
        return  suspendCoroutine { continuation ->
            enqueue(object :Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body=response.body()
                    if(body!=null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

}