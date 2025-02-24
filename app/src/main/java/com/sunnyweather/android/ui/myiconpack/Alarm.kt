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

public val MyIconPack.Alarm: ImageVector
    get() {
        if (_alarm != null) {
            return _alarm!!
        }
        _alarm = Builder(name = "Alarm", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(6.0f, 6.9f)
                lineTo(3.87f, 4.78f)
                lineTo(5.28f, 3.37f)
                lineTo(7.4f, 5.5f)
                lineTo(6.0f, 6.9f)
                moveTo(13.0f, 1.0f)
                verticalLineTo(4.0f)
                horizontalLineTo(11.0f)
                verticalLineTo(1.0f)
                horizontalLineTo(13.0f)
                moveTo(20.13f, 4.78f)
                lineTo(18.0f, 6.9f)
                lineTo(16.6f, 5.5f)
                lineTo(18.72f, 3.37f)
                lineTo(20.13f, 4.78f)
                moveTo(4.5f, 10.5f)
                verticalLineTo(12.5f)
                horizontalLineTo(1.5f)
                verticalLineTo(10.5f)
                horizontalLineTo(4.5f)
                moveTo(19.5f, 10.5f)
                horizontalLineTo(22.5f)
                verticalLineTo(12.5f)
                horizontalLineTo(19.5f)
                verticalLineTo(10.5f)
                moveTo(6.0f, 20.0f)
                horizontalLineTo(18.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 20.0f, 22.0f)
                horizontalLineTo(4.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 6.0f, 20.0f)
                moveTo(12.0f, 5.0f)
                arcTo(6.0f, 6.0f, 0.0f, false, true, 18.0f, 11.0f)
                verticalLineTo(19.0f)
                horizontalLineTo(6.0f)
                verticalLineTo(11.0f)
                arcTo(6.0f, 6.0f, 0.0f, false, true, 12.0f, 5.0f)
                close()
            }
        }
        .build()
        return _alarm!!
    }

private var _alarm: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Alarm, contentDescription = "")
    }
}
