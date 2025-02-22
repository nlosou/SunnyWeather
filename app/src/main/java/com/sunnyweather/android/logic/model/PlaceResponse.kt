package com.sunnyweather.android.logic.model

import java.io.Serial

//数据模型
data class PlaceResponse(val status:String,val places:List<Place>)

data class Place(val name: String,val location:Location,val address:String)

data class Location(val lng:String,val lat:String)


