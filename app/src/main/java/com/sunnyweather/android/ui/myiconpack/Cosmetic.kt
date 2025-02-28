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

public val MyIconPack.Cosmetic: ImageVector
    get() {
        if (_cosmetic != null) {
            return _cosmetic!!
        }
        _cosmetic = Builder(name = "Cosmetic", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(11.0f, 10.5f)
                curveTo(11.0f, 7.462f, 13.462f, 5.0f, 16.5f, 5.0f)
                curveTo(19.538f, 5.0f, 22.0f, 7.462f, 22.0f, 10.5f)
                curveTo(22.0f, 13.538f, 19.538f, 16.0f, 16.5f, 16.0f)
                curveTo(13.462f, 16.0f, 11.0f, 13.538f, 11.0f, 10.5f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    fillAlpha = 0.5f, strokeAlpha = 0.5f, strokeLineWidth = 1.5f, strokeLineCap =
                    Round, strokeLineJoin = StrokeJoin.Companion.Round, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(16.5f, 20.0f)
                verticalLineTo(16.0f)
                moveTo(16.5f, 20.0f)
                horizontalLineTo(19.5f)
                moveTo(16.5f, 20.0f)
                horizontalLineTo(13.5f)
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(2.0f, 11.0f)
                verticalLineTo(10.25f)
                curveTo(1.586f, 10.25f, 1.25f, 10.586f, 1.25f, 11.0f)
                horizontalLineTo(2.0f)
                close()
                moveTo(8.0f, 11.0f)
                horizontalLineTo(8.75f)
                curveTo(8.75f, 10.586f, 8.414f, 10.25f, 8.0f, 10.25f)
                verticalLineTo(11.0f)
                close()
                moveTo(2.0f, 11.75f)
                horizontalLineTo(8.0f)
                verticalLineTo(10.25f)
                horizontalLineTo(2.0f)
                verticalLineTo(11.75f)
                close()
                moveTo(7.25f, 11.0f)
                verticalLineTo(17.0f)
                horizontalLineTo(8.75f)
                verticalLineTo(11.0f)
                horizontalLineTo(7.25f)
                close()
                moveTo(2.75f, 17.0f)
                verticalLineTo(11.0f)
                horizontalLineTo(1.25f)
                verticalLineTo(17.0f)
                horizontalLineTo(2.75f)
                close()
                moveTo(5.0f, 19.25f)
                curveTo(3.757f, 19.25f, 2.75f, 18.243f, 2.75f, 17.0f)
                horizontalLineTo(1.25f)
                curveTo(1.25f, 19.071f, 2.929f, 20.75f, 5.0f, 20.75f)
                verticalLineTo(19.25f)
                close()
                moveTo(7.25f, 17.0f)
                curveTo(7.25f, 18.243f, 6.243f, 19.25f, 5.0f, 19.25f)
                verticalLineTo(20.75f)
                curveTo(7.071f, 20.75f, 8.75f, 19.071f, 8.75f, 17.0f)
                horizontalLineTo(7.25f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    fillAlpha = 0.5f, strokeAlpha = 0.5f, strokeLineWidth = 1.5f, strokeLineCap =
                    Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(3.0f, 11.0f)
                horizontalLineTo(7.0f)
                verticalLineTo(5.618f)
                curveTo(7.0f, 4.875f, 6.218f, 4.391f, 5.553f, 4.724f)
                lineTo(3.553f, 5.724f)
                curveTo(3.214f, 5.893f, 3.0f, 6.239f, 3.0f, 6.618f)
                verticalLineTo(11.0f)
                close()
            }
        }
        .build()
        return _cosmetic!!
    }

private var _cosmetic: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Cosmetic, contentDescription = "")
    }
}
