package com.sunnyweather.android.ui.Anime

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun animateOffsetAndAlpha(isExpanded: Boolean): Pair<Dp, Float> {
    val offset by animateDpAsState(
        targetValue = if (isExpanded) 250.dp else 0.dp,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "Offset Animation"
    )

    val alpha by animateFloatAsState(
        targetValue = if (isExpanded) 1f else 0f,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "Alpha Animation"
    )

    return Pair(offset, alpha)
}