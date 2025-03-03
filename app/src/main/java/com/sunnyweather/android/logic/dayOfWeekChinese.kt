package com.sunnyweather.android.logic

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun dayOfWeekChinese(dateString:String):String {
    // 输入的日期字符串

    // 解析日期字符串为 ZonedDateTime 对象
    val zonedDateTime = ZonedDateTime.parse(dateString)

    // 获取星期几
    val dayOfWeek = zonedDateTime.dayOfWeek

    // 获取星期几的中文名称
    val dayOfWeekChinese = dayOfWeek.getDisplayName(
        java.time.format.TextStyle.FULL,
        Locale.SIMPLIFIED_CHINESE
    )

    return dayOfWeekChinese
}