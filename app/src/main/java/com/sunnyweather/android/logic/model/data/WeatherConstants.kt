/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     <url id="cv1eejeg0jbfo2jvc6s0" type="url" status="parsed" title="Apache License, Version 2.0" wc="10467">https://www.apache.org/licenses/LICENSE-2.0</url>
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sunnyweather.android.logic.model.data
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunnyweather.android.logic.model.data.WeatherAnimatableIcon.CloudyAnimatableIcon
import com.sunnyweather.android.logic.model.data.WeatherAnimatableIcon.HeavyRainAnimatableIcon
import com.sunnyweather.android.logic.model.data.WeatherAnimatableIcon.MostlyClearAnimatableIcon
import com.sunnyweather.android.logic.model.data.WeatherAnimatableIcon.RainAnimatableIcon
import com.sunnyweather.android.logic.model.data.WeatherAnimatableIcon.SnowAnimatableIcon
import com.sunnyweather.android.logic.model.data.WeatherAnimatableIcon.SunnyAnimatableIcon
import com.sunnyweather.android.logic.model.data.WeatherAnimatableIcon.ThunderStormAnimatableIcon
import com.sunnyweather.android.logic.model.data.WeatherBackground.ClearBg
import com.sunnyweather.android.logic.model.data.WeatherBackground.CloudyBg
import com.sunnyweather.android.logic.model.data.WeatherBackground.RainBg
import com.sunnyweather.android.logic.model.data.WeatherBackground.ShowersBg
import com.sunnyweather.android.logic.model.data.WeatherBackground.SnowBg
import com.sunnyweather.android.logic.model.data.WeatherBackground.StormBg
import com.sunnyweather.android.logic.model.data.WeatherBackground.SunnyBg
import com.sunnyweather.android.logic.model.data.WeatherComposedInfo.CloudyComposed
import com.sunnyweather.android.logic.model.data.WeatherComposedInfo.CloudyRainComposed
import com.sunnyweather.android.logic.model.data.WeatherComposedInfo.HeavyRainComposed
import com.sunnyweather.android.logic.model.data.WeatherComposedInfo.MostlyClearComposed
import com.sunnyweather.android.logic.model.data.WeatherComposedInfo.SnowyComposed
import com.sunnyweather.android.logic.model.data.WeatherComposedInfo.SunnyComposed
import com.sunnyweather.android.logic.model.data.WeatherComposedInfo.ThunderStormComposed
import com.sunnyweather.android.logic.model.data.WeatherIcon.CloudyIcon
import com.sunnyweather.android.logic.model.data.WeatherIcon.HeavyRainIcon
import com.sunnyweather.android.logic.model.data.WeatherIcon.MostlyClearIcon
import com.sunnyweather.android.logic.model.data.WeatherIcon.RainIcon
import com.sunnyweather.android.logic.model.data.WeatherIcon.SnowyIcon
import com.sunnyweather.android.logic.model.data.WeatherIcon.SunnyIcon
import com.sunnyweather.android.logic.model.data.WeatherIcon.ThunderStormIcon
import com.sunnyweather.android.ui.Anime.ComposeInfo
import com.sunnyweather.android.ui.Anime.ComposedIcon
import com.sunnyweather.android.ui.Anime.IconInfo
import com.sunnyweather.android.ui.Anime.theme.teal500
import com.sunnyweather.android.ui.Anime.theme.teal700
import com.sunnyweather.android.ui.Anime.theme.teal900
import com.sunnyweather.android.ui.Anime.theme.yellow200
import com.sunnyweather.android.ui.Anime.theme.yellow500
import com.sunnyweather.android.ui.Anime.AnimatableCloud
import com.sunnyweather.android.ui.Anime.AnimatableRains
import com.sunnyweather.android.ui.Anime.AnimatableSnow
import com.sunnyweather.android.ui.Anime.AnimatableSun
import com.sunnyweather.android.ui.Anime.AnimatableThunder
import com.sunnyweather.android.ui.Anime.Cloud
import com.sunnyweather.android.ui.Anime.Rains
import com.sunnyweather.android.ui.Anime.Snow
import com.sunnyweather.android.ui.Anime.Sun
import com.sunnyweather.android.ui.Anime.Thunder

// 定义天气类型及其相关属性
enum class Weather(
    val text: String = "", // 天气类型的文字描述
    val composedIcon: ComposeInfo, // 由多个元素组成的图标
    val icon: @Composable () -> Unit, // 静态图标
    val animatableIcon: @Composable () -> Unit, // 动态图标
    val background: BgColors // 背景色
) {
    Sunny(
        "Sunny",
        SunnyComposed, SunnyIcon, SunnyAnimatableIcon, SunnyBg
    ), // 晴天
    MostlyClear(
        "Clear with periodic clouds",
        MostlyClearComposed, MostlyClearIcon, MostlyClearAnimatableIcon, ClearBg
    ), // 大部分晴朗
    Cloudy(
        "Cloudy",
        CloudyComposed, CloudyIcon, CloudyAnimatableIcon, CloudyBg
    ), // 多云
    CloudyRain(
        "Cloudy with periodic showers",
        CloudyRainComposed, RainIcon, RainAnimatableIcon, ShowersBg
    ), // 阵雨
    HeavyRain(
        "Heavy rain",
        HeavyRainComposed, HeavyRainIcon, HeavyRainAnimatableIcon, RainBg
    ), // 大雨
    Snowy(
        "Snowy",
        SnowyComposed, SnowyIcon, SnowAnimatableIcon, SnowBg
    ), // 下雪
    Storm(
        "Thundery storm",
        ThunderStormComposed, ThunderStormIcon, {ThunderStormAnimatableIcon()}, StormBg
    ) // 雷暴
}

// 背景色定义，包含顶部、中部和底部区域的颜色
typealias BgColors = Triple<Color, Color, Color>

// 天气背景类，存储不同天气的背景色
object WeatherBackground {

    val ShowersBg = BgColors(
        teal700,
        Color.LightGray,
        teal900
    ) // 阵雨
    val RainBg = BgColors(
        Color.LightGray,
        Color.Gray,
        Color.White
    ) // 雨天
    val CloudyBg = BgColors(
        teal700,
        teal500,
        Color.White
    ) // 多云
    val SunnyBg = BgColors(
        yellow200,
        teal500,
        yellow500
    ) // 晴天
    val ClearBg = BgColors(
        teal500,
        teal900,
        teal500
    ) // 大部分晴朗
    val SnowBg = BgColors(
        Color.LightGray,
        Color.White,
        teal700
    ) // 下雪
    val StormBg = BgColors(
        Color.Black,
        Color.White,
        Color.DarkGray
    ) // 雷暴
}

// 图标组成信息对象，存储不同天气元素的组合信息
object WeatherComposedInfo {

    // 图标大小
    val IconSize = 200.dp // 图标大小

    // 不同天气类型的图标组成信息
    val SunnyComposed = ComposeInfo(
        sun = IconInfo(1f),
        cloud = IconInfo(0.8f, Offset(-0.1f, 0.1f), 0f),
        lightCloud = IconInfo(0.5f, Offset(-0.15f, 0.35f), 0f),
        rains = IconInfo(0.4f, Offset(0.225f, 0.3f), 0f),
        lightRain = IconInfo(0.4f, Offset(0.225f, 0.3f), 0f),
        snow = IconInfo(0.5f, Offset(0.1f, 0.3f), alpha = 0f),
        thunder = IconInfo(0.45f, Offset(0.29f, 0.6f), alpha = 0f)
    )
    val MostlyClearComposed = ComposeInfo( // 大部分晴朗
        sun = IconInfo(0.85f, Offset(0.1f, 0f)),
        cloud = IconInfo(0.5f, Offset(-0.1f, 0.1f), 0f),
        lightCloud = IconInfo(0.4f, Offset(0.175f, 0.375f), 1f),
        rains = IconInfo(0.4f, Offset(0.225f, 0.3f), 0f),
        lightRain = IconInfo(0.4f, Offset(0.225f, 0.3f), 0f),
        snow = IconInfo(0.5f, Offset(0.1f, 0.3f), alpha = 0f),
        thunder = IconInfo(0.45f, Offset(0.29f, 0.6f), alpha = 0f)
    )
    val CloudyComposed = ComposeInfo( // 多云
        sun = IconInfo(0.1f, Offset(0.75f, 0.2f), alpha = 0f),
        cloud = IconInfo(0.8f, Offset(0.1f, 0.1f)),
        lightCloud = IconInfo(0.5f, Offset(0.05f, 0.125f)),
        rains = IconInfo(0.4f, Offset(0.225f, 0.3f), alpha = 0f),
        lightRain = IconInfo(0.4f, Offset(0.225f, 0.3f), alpha = 0f),
        snow = IconInfo(0.5f, Offset(0.1f, 0.3f), alpha = 0f),
        thunder = IconInfo(0.45f, Offset(0.29f, 0.6f), alpha = 0f)
    )
    val CloudyRainComposed = ComposeInfo( // 阵雨
        sun = IconInfo(0.6f, Offset(0.4f, 0f)),
        cloud = IconInfo(0.8f, Offset(0.1f, 0.1f)),
        lightCloud = IconInfo(0.5f, Offset(-0.15f, 0.125f), 0f),
        rains = IconInfo(0.4f, Offset(0.225f, 0.3f), 0f),
        lightRain = IconInfo(0.4f, Offset(0.225f, 0.3f), 1f),
        snow = IconInfo(0.5f, Offset(0.1f, 0.3f), alpha = 0f),
        thunder = IconInfo(0.45f, Offset(0.29f, 0.6f), alpha = 0f)
    )
    val HeavyRainComposed = ComposeInfo( // 大雨
        sun = IconInfo(0.1f, Offset(0.75f, 0.2f), alpha = 0f),
        cloud = IconInfo(0.8f, Offset(0.1f, 0.1f)),
        lightCloud = IconInfo(0.5f, Offset(0.05f, 0.125f)),
        rains = IconInfo(0.4f, Offset(0.225f, 0.3f), alpha = 1f),
        lightRain = IconInfo(0.4f, Offset(0.225f, 0.3f), 0f),
        snow = IconInfo(0.5f, Offset(0.1f, 0.3f), alpha = 0f),
        thunder = IconInfo(0.45f, Offset(0.29f, 0.6f), alpha = 0f)
    )
    val SnowyComposed = ComposeInfo( // 下雪
        sun = IconInfo(0.1f, Offset(0.75f, 0.2f), alpha = 0f),
        cloud = IconInfo(0.8f, Offset(0.1f, 0.1f)),
        lightCloud = IconInfo(0.5f, Offset(-0.15f, 0.35f), 0f),
        rains = IconInfo(0.4f, Offset(0.225f, 0.3f), alpha = 0f),
        lightRain = IconInfo(0.4f, Offset(0.225f, 0.3f), 0f),
        snow = IconInfo(0.5f, Offset(0.1f, 0.3f), alpha = 1f),
        thunder = IconInfo(0.45f, Offset(0.29f, 0.6f), alpha = 0f)
    )
    val ThunderStormComposed = ComposeInfo( // 雷暴
        sun = IconInfo(0.1f, Offset(0.75f, 0.2f), alpha = 0f),
        cloud = IconInfo(0.9f, Offset(0.06f, 0.05f)),
        lightCloud = IconInfo(0.5f, Offset(-0.05f, 0.125f), 0f),
        rains = IconInfo(0.45f, Offset(0.2f, 0.3f), alpha = 0f),
        lightRain = IconInfo(0.4f, Offset(0.225f, 0.3f), 0f),
        snow = IconInfo(0.5f, Offset(0.1f, 0.3f), alpha = 0f),
        thunder = IconInfo(0.5f, Offset(0.27f, 0.6f), alpha = 1f)
    )
}

// 天气静态图标
object WeatherIcon {
    val SunnyIcon = @Composable { // 晴天
        Sun(Modifier.size(40.dp)) // 绘制太阳图标
    }

    val MostlyClearIcon = @Composable { // 大部分晴朗
        Box(Modifier.size(40.dp)) {
            Sun(
                Modifier
                    .size(40.dp)
                    .offset(3.dp)
            ) // 绘制太阳图标
            Cloud(
                Modifier
                    .size(16.dp)
                    .offset(8.dp, 18.dp)
            ) // 绘制云图标
        }
    }

    val CloudyIcon = @Composable { // 多云
        Cloud(
            Modifier
                .size(40.dp)
                .padding(3.dp)
        ) // 绘制云图标
    }

    val RainIcon = @Composable { // 下雨
        Box(Modifier.size(40.dp)) {
            Rains(
                Modifier
                    .size(25.dp)
                    .offset(5.dp, 8.dp),
                lightRain = true
            ) // 绘制雨图标
            Cloud(
                Modifier
                    .size(30.dp)
                    .align(Alignment.TopCenter)
            ) // 绘制云图标
        }
    }

    val HeavyRainIcon = @Composable { // 大雨
        Box(Modifier.size(40.dp)) {
            Rains(
                Modifier
                    .size(25.dp)
                    .offset(5.dp, 8.dp),
            ) // 绘制雨图标
            Cloud(
                Modifier
                    .size(30.dp)
                    .align(Alignment.TopCenter)
            ) // 绘制云图标
        }
    }

    val SnowyIcon = @Composable { // 下雪
        Box(Modifier.size(40.dp)) {
            Snow(
                Modifier
                    .size(20.dp)
                    .offset(3.dp, 8.dp),
            ) // 绘制雪图标
            Cloud(
                Modifier
                    .size(30.dp)
                    .align(Alignment.TopCenter)
            ) // 绘制云图标
        }
    }

    val ThunderStormIcon = @Composable { // 雷暴
        Box(Modifier.size(40.dp)) {
            Cloud(
                Modifier
                    .size(30.dp)
                    .align(Alignment.TopCenter)
            ) // 绘制云图标
            Thunder(
                Modifier
                    .size(20.dp)
                    .offset(10.dp, 18.dp),
            ) // 绘制雷图标
        }
    }
}

// 天气动态图标
object WeatherAnimatableIcon {
    val SunnyAnimatableIcon = @Composable { // 晴天动态图标
        AnimatableSun(
            Modifier
                .size(40.dp)
                .padding(2.dp)
        ) // 动画太阳
    }

    val MostlyClearAnimatableIcon = @Composable { // 大部分晴朗动态图标
        Box(
            Modifier
                .size(40.dp)
        ) {
            AnimatableSun(
                Modifier
                    .size(40.dp)
                    .offset(3.dp)
            ) // 动画太阳
            Cloud(
                Modifier
                    .size(20.dp)
                    .offset(8.dp, 18.dp)
            ) // 绘制云
        }
    }

    val CloudyAnimatableIcon = @Composable { // 多云动态图标
        AnimatableCloud(
            Modifier
                .size(40.dp)
                .padding(3.dp),
            800 // 动画时长
        )
    }

    val RainAnimatableIcon = @Composable { // 下雨动态图标
        Box(Modifier.size(40.dp)) {
            AnimatableRains(
                Modifier
                    .size(25.dp)
                    .offset(5.dp, 8.dp),
                true // 表示轻雨
            )
            Cloud(
                Modifier
                    .size(30.dp)
                    .align(Alignment.TopCenter)
            ) // 绘制云
        }
    }

    val HeavyRainAnimatableIcon = @Composable { // 大雨动态图标
        Box(Modifier.size(40.dp)) {
            AnimatableRains(
                Modifier
                    .size(25.dp)
                    .offset(5.dp, 8.dp)
            )
            Cloud(
                Modifier
                    .size(30.dp)
                    .align(Alignment.TopCenter)
            ) // 绘制云
        }
    }

    val SnowAnimatableIcon = @Composable { // 下雪动态图标
        Box(Modifier.size(40.dp)) {
            AnimatableSnow(
                Modifier
                    .size(20.dp)
                    .offset(3.dp, 8.dp),
            )
            AnimatableRains(
                Modifier
                    .size(25.dp)
                    .offset(5.dp, 8.dp)
            )
            Cloud(
                Modifier
                    .size(30.dp)
                    .align(Alignment.TopCenter)
            ) // 绘制云
        }
    }

    @Composable
    fun ThunderStormAnimatableIcon(modifier: Modifier = Modifier) {
        // 使用 BoxWithConstraints 获取当前 Box 的可变尺寸
        Box(
            modifier = modifier
        ) {
            BoxWithConstraints {
                val boxWidth = constraints.maxWidth.toFloat()
                val boxHeight = constraints.maxHeight.toFloat()

                // 计算缩放比例
                val scaleFactorX = boxWidth / 100f // 假设原来的宽度是 400，你可以根据实际情况调整
                val scaleFactorY = boxHeight / 100f // 假设原来的高度是 400

                // 使用默认缩放比例
                val scale = minOf(scaleFactorX, scaleFactorY)

                Box(
                    modifier = Modifier
                        .scale(scale)
                ) {
                    // 原有的组件布局
                    AnimatableThunder(
                        Modifier
                            .size(20.dp)
                            .offset(10.dp, 20.dp),
                        300 // 动画时长
                    )
                    AnimatableThunder(
                        Modifier
                            .size(15.dp)
                            .offset(5.dp, 20.dp),
                        250 // 动画时长
                    )
                    AnimatableThunder(
                        Modifier
                            .size(15.dp)
                            .offset(20.dp, 20.dp),
                        300 // 动画时长
                    )
                    AnimatableRains(
                        Modifier
                            .size(25.dp)
                            .offset(5.dp, 8.dp)
                    )
                    Cloud(
                        Modifier
                            .size(30.dp)
                            .align(Alignment.TopCenter).offset(5.dp, 1.dp)
                    ) // 绘制云
                }
            }
        }
    }
}

// 预览静态图标
@Preview(heightDp = 300)
@Composable
fun PreviewIcon() {
    Column {
        Row(Modifier.weight(1f)) { // 晴天
            SunnyIcon()
            SunnyAnimatableIcon()
        }
        Row(Modifier.weight(1f)) { // 大部分晴朗
            MostlyClearIcon()
            MostlyClearAnimatableIcon()
        }
        Row(Modifier.weight(1f)) { // 多云
            CloudyIcon()
            CloudyAnimatableIcon()
        }
        Row(Modifier.weight(1f)) { // 下雨
            RainIcon()
            RainAnimatableIcon()
        }
        Row(Modifier.weight(1f)) { // 大雨
            HeavyRainIcon()
            HeavyRainAnimatableIcon()
        }
        Row(Modifier.weight(1f)) { // 下雪
            SnowyIcon()
            SnowAnimatableIcon()
        }
        Row(Modifier.weight(1f)) { // 雷暴
            ThunderStormIcon()
            ThunderStormAnimatableIcon()
        }
    }
}

// 预览组合图标
@Preview(widthDp = 960, heightDp = 640)
@Composable
fun PreviewComposedIcon() {
    Column(
        Modifier
            .background(Color.White)
            .padding(40.dp)
    ) {

        val modifier = Modifier
            .padding(2.5.dp)
            .background(teal700)
            .padding(5.dp)

        Row(Modifier.align(Alignment.CenterHorizontally)) { // 第一组图标
            ComposedIcon(
                modifier, SunnyComposed
            )
            ComposedIcon(
                modifier, CloudyComposed
            )
            ComposedIcon(
                modifier, CloudyRainComposed
            )
            ComposedIcon(
                modifier, ThunderStormComposed
            )
        }
        Row(Modifier.align(Alignment.CenterHorizontally)) { // 第二组图标
            ComposedIcon(
                modifier, HeavyRainComposed
            )
            ComposedIcon(
                modifier, MostlyClearComposed
            )
            ComposedIcon(
                modifier, SnowyComposed
            )
        }
    }
}
@Preview()
@Composable
fun bg()
{
    Box(modifier = Modifier.fillMaxSize())
    {
    }

}