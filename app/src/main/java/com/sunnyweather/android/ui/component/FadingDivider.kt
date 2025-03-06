package com.sunnyweather.android.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FadingDivider(
    color: Color = Color.LightGray,
    thickness: Dp = 1.dp,
    fadeLength: Dp = 16.dp,
    isVertical: Boolean = true // 控制是否为垂直分割线
) {
    val thicknessPx = thickness.value
    val fadeLengthPx = fadeLength.value

    Box(
        modifier = Modifier
            .then(
                if (isVertical) {
                    Modifier
                        .fillMaxHeight()
                        .width(fadeLength * 2)
                } else {
                    Modifier
                        .fillMaxWidth()
                        .height(fadeLength * 2)
                }
            )
            .padding(horizontal = 8.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {

            if (isVertical) {
                drawLine(
                    color = color,
                    start = Offset(0f, size.height / 2),
                    end = Offset(size.width, size.height / 2),
                    strokeWidth = thicknessPx,
                    pathEffect = PathEffect.cornerPathEffect(0f)
                )
            } else {
                drawLine(
                    color = color,
                    start = Offset(size.width / 2, 0f),
                    end = Offset(size.width / 2, size.height),
                    strokeWidth = thicknessPx,
                    pathEffect = PathEffect.cornerPathEffect(0f)
                )
            }
        }
    }
}