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

public val MyIconPack.Moon: ImageVector
    get() {
        if (_moon != null) {
            return _moon!!
        }
        _moon = Builder(name = "Moon", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(12.0f, 22.0f)
                curveTo(17.523f, 22.0f, 22.0f, 17.523f, 22.0f, 12.0f)
                curveTo(22.0f, 11.537f, 21.306f, 11.461f, 21.067f, 11.857f)
                curveTo(19.929f, 13.741f, 17.861f, 15.0f, 15.5f, 15.0f)
                curveTo(11.91f, 15.0f, 9.0f, 12.09f, 9.0f, 8.5f)
                curveTo(9.0f, 6.138f, 10.259f, 4.071f, 12.143f, 2.933f)
                curveTo(12.539f, 2.693f, 12.463f, 2.0f, 12.0f, 2.0f)
                curveTo(6.477f, 2.0f, 2.0f, 6.477f, 2.0f, 12.0f)
                curveTo(2.0f, 17.523f, 6.477f, 22.0f, 12.0f, 22.0f)
                close()
            }
        }
        .build()
        return _moon!!
    }

private var _moon: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Moon, contentDescription = "")
    }
}
