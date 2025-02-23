package com.sunnyweather.android.ui.myiconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunnyweather.android.ui.MyIconPack
import kotlin.Unit

public val MyIconPack.PencilLine: ImageVector
    get() {
        if (_pencilLine != null) {
            return _pencilLine!!
        }
        _pencilLine = Builder(name = "PencilLine", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(21.0f, 21.0f)
                horizontalLineTo(13.0f)
                moveTo(2.5f, 21.5f)
                lineTo(8.049f, 19.365f)
                curveTo(8.404f, 19.229f, 8.582f, 19.161f, 8.748f, 19.072f)
                curveTo(8.895f, 18.992f, 9.036f, 18.901f, 9.168f, 18.798f)
                curveTo(9.317f, 18.683f, 9.451f, 18.548f, 9.72f, 18.279f)
                lineTo(21.0f, 7.0f)
                curveTo(22.105f, 5.895f, 22.105f, 4.104f, 21.0f, 3.0f)
                curveTo(19.896f, 1.895f, 18.105f, 1.895f, 17.0f, 3.0f)
                lineTo(5.72f, 14.28f)
                curveTo(5.451f, 14.548f, 5.317f, 14.683f, 5.201f, 14.832f)
                curveTo(5.099f, 14.964f, 5.007f, 15.105f, 4.928f, 15.252f)
                curveTo(4.839f, 15.418f, 4.771f, 15.596f, 4.634f, 15.951f)
                lineTo(2.5f, 21.5f)
                close()
                moveTo(2.5f, 21.5f)
                lineTo(4.558f, 16.149f)
                curveTo(4.705f, 15.766f, 4.779f, 15.574f, 4.905f, 15.487f)
                curveTo(5.016f, 15.41f, 5.152f, 15.381f, 5.284f, 15.406f)
                curveTo(5.435f, 15.435f, 5.58f, 15.58f, 5.87f, 15.87f)
                lineTo(8.13f, 18.129f)
                curveTo(8.42f, 18.42f, 8.565f, 18.565f, 8.594f, 18.715f)
                curveTo(8.619f, 18.847f, 8.59f, 18.984f, 8.513f, 19.094f)
                curveTo(8.425f, 19.221f, 8.234f, 19.294f, 7.851f, 19.442f)
                lineTo(2.5f, 21.5f)
                close()
            }
        }
        .build()
        return _pencilLine!!
    }

private var _pencilLine: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.PencilLine, contentDescription = "")
    }
}
