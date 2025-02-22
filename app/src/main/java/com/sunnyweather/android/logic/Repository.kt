package com.sunnyweather.android.logic

import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object Repository {
    fun searchPlace(query: String): Flow<Result<List<Place>>> = flow {
        try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "OK") {
                val places = placeResponse.places
                emit(Result.success(places))
            } else {
                emit(Result.failure(RuntimeException("Response status is ${placeResponse.status}")))
            }
        } catch (e: Exception) {
            emit(Result.failure<List<Place>>(e))
        }
    }.flowOn(Dispatchers.IO)
}