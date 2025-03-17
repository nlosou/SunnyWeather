package com.sunnyweather.android.ui.myiconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunnyweather.android.ui.MyIconPack
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter

public val MyIconPack.Circle: ImageVector
    get() {
        if (_Circle != null) {
            return _Circle!!
        }
        _Circle = Builder(name = "CheckCircle", defaultWidth = 24.0.dp, defaultHeight =
        24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color.Transparent), stroke = SolidColor(Color.White), strokeLineWidth = 1.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                moveTo(1.0f, 12.0f)
                curveTo(1.0f, 5.925f, 5.925f, 1.0f, 12.0f, 1.0f)
                reflectiveCurveToRelative(11.0f, 4.925f, 11.0f, 11.0f)
                reflectiveCurveToRelative(-4.925f, 11.0f, -11.0f, 11.0f)
                reflectiveCurveTo(1.0f, 18.075f, 1.0f, 12.0f)
                close()
            }
        }
            .build()
        return _Circle!!
    }

private var _Circle: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Circle, contentDescription = "")
    }
}