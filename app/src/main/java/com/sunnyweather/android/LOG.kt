package com.sunnyweather.android

fun String.log(string: String)
{
    android.util.Log.d(this,string)
}