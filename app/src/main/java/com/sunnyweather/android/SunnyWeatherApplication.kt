package com.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.baidu.location.LocationClient
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer

class SunnyWeatherApplication: Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN="NqlWWysMAIIOBL0l"
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
        SDKInitializer.setAgreePrivacy(context,true) // 必须设置隐私合规
        SDKInitializer.initialize(context)
        SDKInitializer.setCoordType(CoordType.BD09LL)
        LocationClient.setAgreePrivacy(true)
        SDKInitializer.setHttpsEnable(true)
    }
}