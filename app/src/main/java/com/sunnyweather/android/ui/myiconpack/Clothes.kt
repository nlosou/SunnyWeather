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
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunnyweather.android.ui.MyIconPack
import kotlin.Unit

public val MyIconPack.Clothes: ImageVector
    get() {
        if (_clothes != null) {
            return _clothes!!
        }
        _clothes = Builder(name = "Clothes", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 48.0f, viewportHeight = 48.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 4.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(37.0f, 19.0f)
                verticalLineTo(37.0f)
                moveTo(11.0f, 37.0f)
                verticalLineTo(44.0f)
                horizontalLineTo(37.0f)
                verticalLineTo(37.0f)
                moveTo(11.0f, 37.0f)
                horizontalLineTo(4.0f)
                verticalLineTo(12.0f)
                lineTo(9.0f, 7.0f)
                lineTo(16.0f, 4.0f)
                horizontalLineTo(32.0f)
                lineTo(39.0f, 7.0f)
                lineTo(44.0f, 12.0f)
                verticalLineTo(37.0f)
                horizontalLineTo(37.0f)
                moveTo(11.0f, 37.0f)
                verticalLineTo(19.0f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 4.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(24.0f, 21.0f)
                verticalLineTo(44.0f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 4.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(9.0f, 7.0f)
                lineTo(24.0f, 21.0f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 4.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(16.0f, 4.0f)
                lineTo(24.0f, 21.0f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 4.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(32.0f, 4.0f)
                lineTo(24.0f, 21.0f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 4.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(39.0f, 7.0f)
                lineTo(24.0f, 21.0f)
            }
        }
        .build()
        return _clothes!!
    }

private var _clothes: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Clothes, contentDescription = "")
    }
}
