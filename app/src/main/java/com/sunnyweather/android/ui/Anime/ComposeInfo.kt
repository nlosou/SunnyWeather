/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     <url id="cv1ejj8h947abh95qa3g" type="url" status="parsed" title="Apache License, Version 2.0" wc="10467">https://www.apache.org/licenses/LICENSE-2.0</url>
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sunnyweather.android.ui.Anime

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import com.sunnyweather.android.logic.model.data.WeatherComposedInfo.IconSize

/**
 * [ComposedIcon] 使用此信息组合图标。
 * 包含多个图标的属性信息，用于组合和绘制复杂的天气图标。
 */
data class ComposeInfo(
    val sun: IconInfo, // 太阳图标的信息
    val cloud: IconInfo, // 云图标的属性
    val lightCloud: IconInfo, // 轻云图标的属性
    val rains: IconInfo, // 雨图标的属性
    val lightRain: IconInfo, // 轻雨图标的属性
    val snow: IconInfo, // 雪图标的属性
    val thunder: IconInfo, // 雷电图标的属性
) {
    // 操作符重载：用于缩放整形后的图标
    operator fun times(float: Float): ComposeInfo =
        copy(
            sun = sun * float, // 缩放太阳图标
            cloud = cloud * float, // 缩放云图标
            lightCloud = lightCloud * float, // 缩放轻云图标
            rains = rains * float, // 缩放雨图标
            lightRain = lightRain * float, // 缩放轻雨图标
            snow = snow * float, // 缩放雪图标
            thunder = thunder * float // 缩放雷电图标
        )

    // 操作符重载：用于对两个 ComposeInfo 对象执行减法操作
    operator fun minus(composeInfo: ComposeInfo): ComposeInfo =
        copy(
            sun = sun - composeInfo.sun, // 减去太阳图标的属性
            cloud = cloud - composeInfo.cloud, // 减去云图标的属性
            lightCloud = lightCloud - composeInfo.lightCloud, // 减去轻云图标的属性
            rains = rains - composeInfo.rains, // 减去雨图标的属性
            lightRain = lightRain - composeInfo.lightRain, // 减去轻雨图标的属性
            snow = snow - composeInfo.snow, // 减去雪图标的属性
            thunder = thunder - composeInfo.thunder // 减去雷电图标的属性
        )

    // 操作符重载：用于对两个 ComposeInfo 对象执行加法操作
    operator fun plus(composeInfo: ComposeInfo): ComposeInfo =
        copy(
            sun = sun + composeInfo.sun, // 加上太阳图标的属性
            cloud = cloud + composeInfo.cloud, // 加上云图标的属性
            lightCloud = lightCloud + composeInfo.lightCloud, // 加上轻云图标的属性
            rains = rains + composeInfo.rains, // 加上雨图标的属性
            lightRain = lightRain + composeInfo.lightRain, // 加上轻雨图标的属性
            snow = snow + composeInfo.snow, // 加上雪图标的属性
            thunder = thunder + composeInfo.thunder // 加上雷电图标的属性
        )
}

/**
 * 每个图标的属性信息
 * @param sizeRatio: 相对于 [WeatherComposedInfo.IconSize] 的大小比例
 * @param offset: 从左上角的偏移量
 * @param alpha: 透明度
 */
data class IconInfo(
    val sizeRatio: Float = 1f, // 与最大值相比的大小比例
    val offset: Offset = Offset(0f, 0f), // 相对于顶部左边的偏移量
    val alpha: Float = 1f, // 图标的透明度
) {

    // 图标的大小（基于最大尺寸）
    val size: Dp = IconSize

    // 操作符重载：用于缩放 [IconInfo]
    operator fun times(float: Float): IconInfo =
        copy(
            sizeRatio = sizeRatio * float, // 缩放大小比例
            offset = Offset(
                offset.x * float, // 缩放x轴偏移
                offset.y * float // 缩放y轴偏移
            ),
            alpha = alpha * float, // 缩放透明度
        )

    // 操作符重载：用于对两个 [IconInfo] 对象执行减法操作
    operator fun minus(iconInfo: IconInfo): IconInfo =
        copy(
            sizeRatio = sizeRatio - iconInfo.sizeRatio, // 减去大小比例
            offset = Offset(
                offset.x - iconInfo.offset.x, // 减去x轴偏移
                offset.y - iconInfo.offset.y // 减去y轴偏移
            ),
            alpha = alpha - iconInfo.alpha, // 减去透明度
        )

    // 操作符重载：用于对两个 [IconInfo] 对象执行加法操作
    operator fun plus(iconInfo: IconInfo): IconInfo =
        copy(
            sizeRatio = sizeRatio + iconInfo.sizeRatio, // 加上大小比例
            offset = Offset(
                offset.x + iconInfo.offset.x, // 加上x轴偏移
                offset.y + iconInfo.offset.y // 加上y轴偏移
            ),
            alpha = alpha + iconInfo.alpha, // 加上透明度
        )
}