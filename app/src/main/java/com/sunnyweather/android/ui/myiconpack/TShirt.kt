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

public val MyIconPack.TShirt: ImageVector
    get() {
        if (_tShirt != null) {
            return _tShirt!!
        }
        _tShirt = Builder(name = "TShirt", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(6.0f, 4.0f)
                horizontalLineTo(9.0f)
                curveTo(9.0f, 4.0f, 9.0f, 7.0f, 12.0f, 7.0f)
                curveTo(15.0f, 7.0f, 15.0f, 4.0f, 15.0f, 4.0f)
                horizontalLineTo(18.0f)
                moveTo(18.0f, 11.0f)
                verticalLineTo(19.4f)
                curveTo(18.0f, 19.731f, 17.731f, 20.0f, 17.4f, 20.0f)
                horizontalLineTo(6.6f)
                curveTo(6.269f, 20.0f, 6.0f, 19.731f, 6.0f, 19.4f)
                lineTo(6.0f, 11.0f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(18.0f, 4.0f)
                lineTo(22.443f, 5.777f)
                curveTo(22.751f, 5.9f, 22.9f, 6.249f, 22.777f, 6.557f)
                lineTo(21.151f, 10.623f)
                curveTo(21.06f, 10.851f, 20.839f, 11.0f, 20.594f, 11.0f)
                horizontalLineTo(18.0f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(6.0f, 4.0f)
                lineTo(1.557f, 5.777f)
                curveTo(1.249f, 5.9f, 1.1f, 6.249f, 1.223f, 6.557f)
                lineTo(2.849f, 10.623f)
                curveTo(2.94f, 10.851f, 3.161f, 11.0f, 3.406f, 11.0f)
                horizontalLineTo(6.0f)
            }
        }
        .build()
        return _tShirt!!
    }

private var _tShirt: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.TShirt, contentDescription = "")
    }
}
