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

public val MyIconPack.Wind2: ImageVector
    get() {
        if (_wind2 != null) {
            return _wind2!!
        }
        _wind2 = Builder(name = "Wind2", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(9.509f, 4.667f)
                curveTo(9.875f, 4.258f, 10.408f, 4.0f, 11.0f, 4.0f)
                curveTo(12.105f, 4.0f, 13.0f, 4.895f, 13.0f, 6.0f)
                curveTo(13.0f, 7.105f, 12.105f, 8.0f, 11.0f, 8.0f)
                horizontalLineTo(2.0f)
                moveTo(12.509f, 19.333f)
                curveTo(12.875f, 19.743f, 13.408f, 20.0f, 14.0f, 20.0f)
                curveTo(15.105f, 20.0f, 16.0f, 19.105f, 16.0f, 18.0f)
                curveTo(16.0f, 16.895f, 15.105f, 16.0f, 14.0f, 16.0f)
                horizontalLineTo(2.0f)
                moveTo(16.764f, 7.0f)
                curveTo(17.313f, 6.386f, 18.111f, 6.0f, 19.0f, 6.0f)
                curveTo(20.657f, 6.0f, 22.0f, 7.343f, 22.0f, 9.0f)
                curveTo(22.0f, 10.657f, 20.657f, 12.0f, 19.0f, 12.0f)
                horizontalLineTo(2.0f)
            }
        }
        .build()
        return _wind2!!
    }

private var _wind2: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Wind2, contentDescription = "")
    }
}
