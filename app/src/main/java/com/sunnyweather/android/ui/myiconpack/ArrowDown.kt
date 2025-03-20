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
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunnyweather.android.ui.MyIconPack
import kotlin.Unit

public val MyIconPack.ArrowDown: ImageVector
    get() {
        if (_arrowDown != null) {
            return _arrowDown!!
        }
        _arrowDown = Builder(name = "ArrowDown", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF292D32)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(11.999f, 21.25f)
                curveTo(11.809f, 21.25f, 11.619f, 21.18f, 11.469f, 21.03f)
                lineTo(5.399f, 14.96f)
                curveTo(5.109f, 14.67f, 5.109f, 14.19f, 5.399f, 13.9f)
                curveTo(5.689f, 13.61f, 6.169f, 13.61f, 6.459f, 13.9f)
                lineTo(11.999f, 19.44f)
                lineTo(17.539f, 13.9f)
                curveTo(17.829f, 13.61f, 18.309f, 13.61f, 18.599f, 13.9f)
                curveTo(18.889f, 14.19f, 18.889f, 14.67f, 18.599f, 14.96f)
                lineTo(12.529f, 21.03f)
                curveTo(12.379f, 21.18f, 12.189f, 21.25f, 11.999f, 21.25f)
                close()
            }
            path(fill = SolidColor(Color(0xFF292D32)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(12.0f, 21.08f)
                curveTo(11.59f, 21.08f, 11.25f, 20.74f, 11.25f, 20.33f)
                verticalLineTo(3.5f)
                curveTo(11.25f, 3.09f, 11.59f, 2.75f, 12.0f, 2.75f)
                curveTo(12.41f, 2.75f, 12.75f, 3.09f, 12.75f, 3.5f)
                verticalLineTo(20.33f)
                curveTo(12.75f, 20.74f, 12.41f, 21.08f, 12.0f, 21.08f)
                close()
            }
        }
        .build()
        return _arrowDown!!
    }

private var _arrowDown: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.ArrowDown, contentDescription = "")
    }
}
