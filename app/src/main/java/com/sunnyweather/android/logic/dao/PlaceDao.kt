package com.sunnyweather.android.logic.dao

import android.content.Context
import android.graphics.Insets.add
import android.provider.Settings.System.putString
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sunnyweather.android.SunnyWeatherApplication
import com.sunnyweather.android.logic.model.Place

object PlaceDao {
    fun savePalce(place: Place)
    {
        val sharedPreferences = sharedPreferences()
        val existingPlacesJson = sharedPreferences.getString("places", "[]") // 默认值为空列表
        val existingPlaces = Gson().fromJson<List<Place>>(existingPlacesJson, object : TypeToken<List<Place>>() {}.type)
        val updatedPlaces = existingPlaces.toMutableList().apply { add(place) }
        sharedPreferences.edit {
            putString("places", Gson().toJson(updatedPlaces))
        }
    }
    fun getSavedPlace():List<Place>{
        val placeJson=sharedPreferences().getString("places","[]")
        return Gson().fromJson(placeJson, object : TypeToken<List<Place>>() {}.type)
    }
    fun isPlaceSaved()=sharedPreferences().contains("place")

    fun deletePlaceAtIndex(index: Int) {
        val sharedPreferences = sharedPreferences()
        val existingPlacesJson = sharedPreferences.getString("places", "[]")
        val existingPlaces = Gson().fromJson<List<Place>>(existingPlacesJson, object : TypeToken<List<Place>>() {}.type)
        if (index >= 0 && index < existingPlaces.size) {
            val updatedPlaces = existingPlaces.toMutableList().apply { removeAt(index) }
            sharedPreferences.edit {
                putString("places", Gson().toJson(updatedPlaces))
            }
        }
    }
    private fun sharedPreferences()=SunnyWeatherApplication.context.getSharedPreferences("sunny_weather",Context.MODE_PRIVATE)
}