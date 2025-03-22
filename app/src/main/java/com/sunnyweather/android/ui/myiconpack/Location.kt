package com.sunnyweather.android.ui.myiconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunnyweather.android.ui.MyIconPack
import kotlin.Unit

public val MyIconPack.Location: ImageVector
    get() {
        if (_location != null) {
            return _location!!
        }
        _location = Builder(name = "Location", defaultWidth = 92.0.dp, defaultHeight = 92.0.dp,
                viewportWidth = 92.0f, viewportHeight = 92.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(68.4f, 7.9f)
                curveTo(62.7f, 2.8f, 54.7f, 0.0f, 46.0f, 0.0f)
                reflectiveCurveTo(29.3f, 2.8f, 23.6f, 7.9f)
                curveTo(16.6f, 14.1f, 13.0f, 23.4f, 13.0f, 35.0f)
                curveToRelative(0.0f, 25.1f, 28.9f, 54.6f, 30.2f, 55.8f)
                curveToRelative(0.8f, 0.8f, 1.8f, 1.2f, 2.8f, 1.2f)
                reflectiveCurveToRelative(2.1f, -0.4f, 2.8f, -1.2f)
                curveTo(50.1f, 89.6f, 79.0f, 60.1f, 79.0f, 35.0f)
                curveTo(79.0f, 23.4f, 75.4f, 14.1f, 68.4f, 7.9f)
                close()
                moveTo(46.0f, 82.1f)
                curveToRelative(-2.7f, -3.0f, -7.0f, -8.0f, -11.2f, -14.0f)
                curveTo(25.8f, 55.3f, 21.0f, 43.9f, 21.0f, 35.0f)
                curveToRelative(0.0f, -25.0f, 19.1f, -27.0f, 25.0f, -27.0f)
                curveToRelative(23.2f, 0.0f, 25.0f, 20.7f, 25.0f, 27.0f)
                curveTo(71.0f, 52.6f, 53.1f, 74.3f, 46.0f, 82.1f)
                close()
                moveTo(46.0f, 17.3f)
                curveToRelative(-8.8f, 0.0f, -15.9f, 7.3f, -15.9f, 16.2f)
                reflectiveCurveTo(37.2f, 49.6f, 46.0f, 49.6f)
                curveToRelative(8.8f, 0.0f, 15.9f, -7.3f, 15.9f, -16.2f)
                reflectiveCurveTo(54.8f, 17.3f, 46.0f, 17.3f)
                close()
                moveTo(46.0f, 42.6f)
                curveToRelative(-4.9f, 0.0f, -8.9f, -4.1f, -8.9f, -9.2f)
                reflectiveCurveToRelative(4.0f, -9.2f, 8.9f, -9.2f)
                curveToRelative(4.9f, 0.0f, 8.9f, 4.1f, 8.9f, 9.2f)
                reflectiveCurveTo(50.9f, 42.6f, 46.0f, 42.6f)
                close()
            }
        }
        .build()
        return _location!!
    }

private var _location: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Location, contentDescription = "")
    }
}
