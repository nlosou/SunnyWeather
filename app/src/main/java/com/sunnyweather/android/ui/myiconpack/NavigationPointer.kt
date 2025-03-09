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

public val MyIconPack.NavigationPointer: ImageVector
    get() {
        if (_navigationPointer != null) {
            return _navigationPointer!!
        }
        _navigationPointer = Builder(name = "NavigationPointer", defaultWidth = 24.0.dp,
                defaultHeight = 24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(3.413f, 10.745f)
                curveTo(2.818f, 10.513f, 2.52f, 10.397f, 2.434f, 10.23f)
                curveTo(2.358f, 10.086f, 2.358f, 9.914f, 2.433f, 9.769f)
                curveTo(2.52f, 9.602f, 2.818f, 9.486f, 3.413f, 9.254f)
                lineTo(20.3f, 2.663f)
                curveTo(20.837f, 2.454f, 21.106f, 2.349f, 21.278f, 2.406f)
                curveTo(21.427f, 2.456f, 21.544f, 2.573f, 21.594f, 2.722f)
                curveTo(21.651f, 2.894f, 21.546f, 3.162f, 21.336f, 3.699f)
                lineTo(14.746f, 20.587f)
                curveTo(14.514f, 21.182f, 14.398f, 21.48f, 14.231f, 21.566f)
                curveTo(14.086f, 21.642f, 13.914f, 21.642f, 13.769f, 21.566f)
                curveTo(13.602f, 21.479f, 13.487f, 21.182f, 13.255f, 20.586f)
                lineTo(10.627f, 13.828f)
                curveTo(10.58f, 13.707f, 10.557f, 13.647f, 10.52f, 13.596f)
                curveTo(10.488f, 13.551f, 10.449f, 13.512f, 10.404f, 13.479f)
                curveTo(10.353f, 13.443f, 10.292f, 13.42f, 10.172f, 13.373f)
                lineTo(3.413f, 10.745f)
                close()
            }
        }
        .build()
        return _navigationPointer!!
    }

private var _navigationPointer: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.NavigationPointer, contentDescription = "")
    }
}
