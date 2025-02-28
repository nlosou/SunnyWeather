package com.sunnyweather.android.ui.myiconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunnyweather.android.ui.MyIconPack
import kotlin.Unit

public val MyIconPack.Sport: ImageVector
    get() {
        if (_sport != null) {
            return _sport!!
        }
        _sport = Builder(name = "Sport", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 48.0f, viewportHeight = 48.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 4.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(36.0f, 15.0f)
                curveTo(38.761f, 15.0f, 41.0f, 12.761f, 41.0f, 10.0f)
                curveTo(41.0f, 7.239f, 38.761f, 5.0f, 36.0f, 5.0f)
                curveTo(33.239f, 5.0f, 31.0f, 7.239f, 31.0f, 10.0f)
                curveTo(31.0f, 12.761f, 33.239f, 15.0f, 36.0f, 15.0f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 4.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(12.0f, 16.769f)
                lineTo(20.003f, 13.998f)
                lineTo(31.0f, 19.247f)
                lineTo(20.003f, 27.444f)
                lineTo(31.0f, 34.683f)
                lineTo(24.008f, 43.998f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 4.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(35.32f, 21.643f)
                lineTo(38.002f, 23.102f)
                lineTo(44.0f, 17.466f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 4.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(16.849f, 31.545f)
                lineTo(13.879f, 35.457f)
                lineTo(4.004f, 40.996f)
            }
        }
        .build()
        return _sport!!
    }

private var _sport: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Sport, contentDescription = "")
    }
}
