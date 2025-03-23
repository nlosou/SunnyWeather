package com.sunnyweather.android.logic.model

import java.io.Serial

//数据模型
data class PlaceResponse(val status:String,val places:List<Place>)

//data class Place(val name: String,val location:Location,val address:String)
data class Place(
    var adcode: String,
    var formatted_address: String,
    var lng: Double,
    var lat: Double,
    var max:String,
    var min:String,
    var sky:String,
    var currentTemp:String
)
data class Location(var lng:String, var lat:String)


