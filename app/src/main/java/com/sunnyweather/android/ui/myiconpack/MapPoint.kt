package com.sunnyweather.android.ui.myiconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
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

public val MyIconPack.MapPoint: ImageVector
    get() {
        if (_mapPoint != null) {
            return _mapPoint!!
        }
        _mapPoint = Builder(name = "MapPoint", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.5f, strokeAlpha
                    = 0.5f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(19.716f, 20.362f)
                curveTo(21.143f, 19.585f, 22.0f, 18.587f, 22.0f, 17.5f)
                curveTo(22.0f, 16.347f, 21.037f, 15.296f, 19.454f, 14.5f)
                curveTo(17.623f, 13.579f, 14.962f, 13.0f, 12.0f, 13.0f)
                curveTo(9.038f, 13.0f, 6.377f, 13.579f, 4.546f, 14.5f)
                curveTo(2.963f, 15.296f, 2.0f, 16.347f, 2.0f, 17.5f)
                curveTo(2.0f, 18.653f, 2.963f, 19.704f, 4.546f, 20.5f)
                curveTo(6.377f, 21.421f, 9.038f, 22.0f, 12.0f, 22.0f)
                curveTo(15.107f, 22.0f, 17.882f, 21.362f, 19.716f, 20.362f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(5.0f, 8.515f)
                curveTo(5.0f, 4.917f, 8.134f, 2.0f, 12.0f, 2.0f)
                curveTo(15.866f, 2.0f, 19.0f, 4.917f, 19.0f, 8.515f)
                curveTo(19.0f, 12.084f, 16.766f, 16.25f, 13.28f, 17.74f)
                curveTo(12.467f, 18.087f, 11.533f, 18.087f, 10.72f, 17.74f)
                curveTo(7.234f, 16.25f, 5.0f, 12.084f, 5.0f, 8.515f)
                close()
                moveTo(12.0f, 11.0f)
                curveTo(13.105f, 11.0f, 14.0f, 10.105f, 14.0f, 9.0f)
                curveTo(14.0f, 7.895f, 13.105f, 7.0f, 12.0f, 7.0f)
                curveTo(10.895f, 7.0f, 10.0f, 7.895f, 10.0f, 9.0f)
                curveTo(10.0f, 10.105f, 10.895f, 11.0f, 12.0f, 11.0f)
                close()
            }
        }
        .build()
        return _mapPoint!!
    }

private var _mapPoint: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.MapPoint, contentDescription = "")
    }
}
