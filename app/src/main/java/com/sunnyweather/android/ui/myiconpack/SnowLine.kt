package com.sunnyweather.android.ui.myiconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
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

public val MyIconPack.SnowLine: ImageVector
    get() {
        if (_snowLine != null) {
            return _snowLine!!
        }
        _snowLine = Builder(name = "SnowLine", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF09244B)), stroke = SolidColor(Color(0x00000000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = EvenOdd) {
                moveTo(12.0f, 2.0f)
                curveTo(12.552f, 2.0f, 13.0f, 2.448f, 13.0f, 3.0f)
                lineTo(13.0f, 3.962f)
                lineTo(13.654f, 3.616f)
                curveTo(14.142f, 3.358f, 14.747f, 3.544f, 15.005f, 4.033f)
                curveTo(15.264f, 4.521f, 15.077f, 5.126f, 14.589f, 5.384f)
                lineTo(13.0f, 6.224f)
                lineTo(13.0f, 8.126f)
                curveTo(13.715f, 8.31f, 14.352f, 8.687f, 14.854f, 9.197f)
                lineTo(16.502f, 8.246f)
                lineTo(16.435f, 6.45f)
                curveTo(16.415f, 5.898f, 16.845f, 5.434f, 17.397f, 5.413f)
                curveTo(17.949f, 5.393f, 18.413f, 5.823f, 18.434f, 6.375f)
                lineTo(18.461f, 7.115f)
                lineTo(19.294f, 6.634f)
                curveTo(19.772f, 6.358f, 20.384f, 6.522f, 20.66f, 7.0f)
                curveTo(20.936f, 7.478f, 20.772f, 8.09f, 20.294f, 8.366f)
                lineTo(19.461f, 8.847f)
                lineTo(20.088f, 9.24f)
                curveTo(20.556f, 9.534f, 20.696f, 10.151f, 20.403f, 10.619f)
                curveTo(20.109f, 11.087f, 19.492f, 11.227f, 19.024f, 10.934f)
                lineTo(17.502f, 9.978f)
                lineTo(15.855f, 10.929f)
                curveTo(15.95f, 11.27f, 16.0f, 11.629f, 16.0f, 12.0f)
                curveTo(16.0f, 12.371f, 15.95f, 12.73f, 15.855f, 13.071f)
                lineTo(17.502f, 14.022f)
                lineTo(19.024f, 13.066f)
                curveTo(19.492f, 12.773f, 20.109f, 12.913f, 20.403f, 13.381f)
                curveTo(20.696f, 13.849f, 20.556f, 14.466f, 20.088f, 14.76f)
                lineTo(19.462f, 15.153f)
                lineTo(20.294f, 15.634f)
                curveTo(20.772f, 15.91f, 20.936f, 16.522f, 20.66f, 17.0f)
                curveTo(20.384f, 17.479f, 19.772f, 17.642f, 19.294f, 17.366f)
                lineTo(18.462f, 16.885f)
                lineTo(18.434f, 17.625f)
                curveTo(18.413f, 18.177f, 17.949f, 18.607f, 17.397f, 18.587f)
                curveTo(16.845f, 18.566f, 16.415f, 18.102f, 16.435f, 17.55f)
                lineTo(16.502f, 15.754f)
                lineTo(14.854f, 14.803f)
                curveTo(14.352f, 15.313f, 13.715f, 15.69f, 13.0f, 15.874f)
                lineTo(13.0f, 17.776f)
                lineTo(14.589f, 18.616f)
                curveTo(15.077f, 18.874f, 15.264f, 19.479f, 15.005f, 19.967f)
                curveTo(14.747f, 20.456f, 14.142f, 20.642f, 13.654f, 20.384f)
                lineTo(13.0f, 20.038f)
                lineTo(13.0f, 21.0f)
                curveTo(13.0f, 21.552f, 12.552f, 22.0f, 12.0f, 22.0f)
                curveTo(11.448f, 22.0f, 11.0f, 21.552f, 11.0f, 21.0f)
                lineTo(11.0f, 20.038f)
                lineTo(10.346f, 20.384f)
                curveTo(9.858f, 20.642f, 9.253f, 20.456f, 8.995f, 19.967f)
                curveTo(8.737f, 19.479f, 8.923f, 18.874f, 9.411f, 18.616f)
                lineTo(11.0f, 17.776f)
                lineTo(11.0f, 15.874f)
                curveTo(10.285f, 15.69f, 9.647f, 15.313f, 9.146f, 14.802f)
                lineTo(7.498f, 15.754f)
                lineTo(7.565f, 17.55f)
                curveTo(7.585f, 18.101f, 7.155f, 18.566f, 6.603f, 18.586f)
                curveTo(6.051f, 18.607f, 5.587f, 18.176f, 5.566f, 17.624f)
                lineTo(5.538f, 16.885f)
                lineTo(4.706f, 17.366f)
                curveTo(4.227f, 17.642f, 3.616f, 17.478f, 3.34f, 17.0f)
                curveTo(3.064f, 16.521f, 3.227f, 15.91f, 3.706f, 15.634f)
                lineTo(4.538f, 15.153f)
                lineTo(3.912f, 14.759f)
                curveTo(3.444f, 14.466f, 3.304f, 13.848f, 3.597f, 13.381f)
                curveTo(3.891f, 12.913f, 4.508f, 12.772f, 4.976f, 13.066f)
                lineTo(6.498f, 14.022f)
                lineTo(8.145f, 13.071f)
                curveTo(8.051f, 12.73f, 8.0f, 12.371f, 8.0f, 12.0f)
                curveTo(8.0f, 11.629f, 8.051f, 11.27f, 8.145f, 10.929f)
                lineTo(6.498f, 9.978f)
                lineTo(4.976f, 10.934f)
                curveTo(4.508f, 11.228f, 3.891f, 11.087f, 3.597f, 10.619f)
                curveTo(3.304f, 10.152f, 3.445f, 9.534f, 3.912f, 9.241f)
                lineTo(4.539f, 8.847f)
                lineTo(3.706f, 8.366f)
                curveTo(3.227f, 8.09f, 3.064f, 7.478f, 3.34f, 7.0f)
                curveTo(3.616f, 6.522f, 4.227f, 6.358f, 4.706f, 6.634f)
                lineTo(5.539f, 7.115f)
                lineTo(5.566f, 6.376f)
                curveTo(5.587f, 5.824f, 6.051f, 5.393f, 6.603f, 5.414f)
                curveTo(7.155f, 5.434f, 7.585f, 5.898f, 7.565f, 6.45f)
                lineTo(7.498f, 8.246f)
                lineTo(9.146f, 9.198f)
                curveTo(9.647f, 8.687f, 10.285f, 8.31f, 11.0f, 8.126f)
                lineTo(11.0f, 6.224f)
                lineTo(9.411f, 5.384f)
                curveTo(8.923f, 5.126f, 8.737f, 4.521f, 8.995f, 4.033f)
                curveTo(9.253f, 3.544f, 9.858f, 3.358f, 10.346f, 3.616f)
                lineTo(11.0f, 3.962f)
                lineTo(11.0f, 3.0f)
                curveTo(11.0f, 2.448f, 11.448f, 2.0f, 12.0f, 2.0f)
                close()
                moveTo(12.0f, 14.0f)
                curveTo(13.105f, 14.0f, 14.0f, 13.105f, 14.0f, 12.0f)
                curveTo(14.0f, 10.895f, 13.105f, 10.0f, 12.0f, 10.0f)
                curveTo(10.895f, 10.0f, 10.0f, 10.895f, 10.0f, 12.0f)
                curveTo(10.0f, 13.105f, 10.895f, 14.0f, 12.0f, 14.0f)
                close()
            }
        }
        .build()
        return _snowLine!!
    }

private var _snowLine: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.SnowLine, contentDescription = "")
    }
}
