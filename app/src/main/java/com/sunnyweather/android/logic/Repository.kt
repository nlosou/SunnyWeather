package com.sunnyweather.android.logic

import com.sunnyweather.android.log
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.network.ServiceCreator
import com.sunnyweather.android.logic.network.ServiceCreator_2
import com.sunnyweather.android.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

object Repository {
    fun searchPlaces(query: String): Flow<Result<List<Place>>> = flow {

        try {
            "Repository.searchPlaces".log(query)
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)

            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                "val places = placeResponse.places".log(places.toString())
                emit(Result.success(places))
            } else {
                "not_is_ok".log(placeResponse.status)
                emit(Result.failure(RuntimeException("Response status is ${placeResponse.status}")))

            }
        } catch (e: Exception) {
            "placeResponse_Exception".log(e.toString())
            emit(Result.failure<List<Place>>(e))
        }
    }.flowOn(Dispatchers.IO)
}

object AdcodeService {
    fun searchPlacesByKeyword(keyword: String): Flow<Result<List<Place>>> =
        flow {
            emit(withContext(Dispatchers.IO) {
                try {
                    val response = ServiceCreator_2.placeService.searchAdcodes().execute()
                    if (response.isSuccessful) {
                        val places = response.body()?.filter {
                            it.formatted_address.contains(keyword, true) // 忽略大小写
                        } ?: emptyList()
                        Result.success(places)
                    } else {
                        Result.failure(Throwable("Request failed with code: ${response.code()}"))
                    }
                } catch (e: Exception) {
                    Result.failure(e)
                }
            })
        }
}