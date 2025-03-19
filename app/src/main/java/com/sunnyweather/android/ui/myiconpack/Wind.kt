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

public val MyIconPack.Wind: ImageVector
    get() {
        if (_wind != null) {
            return _wind!!
        }
        _wind = Builder(name = "Wind", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(6.25f, 5.5f)
                curveTo(6.25f, 3.705f, 7.705f, 2.25f, 9.5f, 2.25f)
                curveTo(11.295f, 2.25f, 12.75f, 3.705f, 12.75f, 5.5f)
                curveTo(12.75f, 7.295f, 11.295f, 8.75f, 9.5f, 8.75f)
                horizontalLineTo(3.0f)
                curveTo(2.586f, 8.75f, 2.25f, 8.414f, 2.25f, 8.0f)
                curveTo(2.25f, 7.586f, 2.586f, 7.25f, 3.0f, 7.25f)
                horizontalLineTo(9.5f)
                curveTo(10.467f, 7.25f, 11.25f, 6.466f, 11.25f, 5.5f)
                curveTo(11.25f, 4.534f, 10.467f, 3.75f, 9.5f, 3.75f)
                curveTo(8.533f, 3.75f, 7.75f, 4.534f, 7.75f, 5.5f)
                verticalLineTo(5.857f)
                curveTo(7.75f, 6.271f, 7.414f, 6.607f, 7.0f, 6.607f)
                curveTo(6.586f, 6.607f, 6.25f, 6.271f, 6.25f, 5.857f)
                verticalLineTo(5.5f)
                close()
                moveTo(14.25f, 7.5f)
                curveTo(14.25f, 5.153f, 16.153f, 3.25f, 18.5f, 3.25f)
                curveTo(20.847f, 3.25f, 22.75f, 5.153f, 22.75f, 7.5f)
                curveTo(22.75f, 9.847f, 20.847f, 11.75f, 18.5f, 11.75f)
                horizontalLineTo(2.0f)
                curveTo(1.586f, 11.75f, 1.25f, 11.414f, 1.25f, 11.0f)
                curveTo(1.25f, 10.586f, 1.586f, 10.25f, 2.0f, 10.25f)
                horizontalLineTo(18.5f)
                curveTo(20.019f, 10.25f, 21.25f, 9.019f, 21.25f, 7.5f)
                curveTo(21.25f, 5.981f, 20.019f, 4.75f, 18.5f, 4.75f)
                curveTo(16.981f, 4.75f, 15.75f, 5.981f, 15.75f, 7.5f)
                verticalLineTo(8.0f)
                curveTo(15.75f, 8.414f, 15.414f, 8.75f, 15.0f, 8.75f)
                curveTo(14.586f, 8.75f, 14.25f, 8.414f, 14.25f, 8.0f)
                verticalLineTo(7.5f)
                close()
                moveTo(3.25f, 14.0f)
                curveTo(3.25f, 13.586f, 3.586f, 13.25f, 4.0f, 13.25f)
                horizontalLineTo(18.5f)
                curveTo(20.847f, 13.25f, 22.75f, 15.153f, 22.75f, 17.5f)
                curveTo(22.75f, 19.847f, 20.847f, 21.75f, 18.5f, 21.75f)
                curveTo(16.153f, 21.75f, 14.25f, 19.847f, 14.25f, 17.5f)
                verticalLineTo(17.0f)
                curveTo(14.25f, 16.586f, 14.586f, 16.25f, 15.0f, 16.25f)
                curveTo(15.414f, 16.25f, 15.75f, 16.586f, 15.75f, 17.0f)
                verticalLineTo(17.5f)
                curveTo(15.75f, 19.019f, 16.981f, 20.25f, 18.5f, 20.25f)
                curveTo(20.019f, 20.25f, 21.25f, 19.019f, 21.25f, 17.5f)
                curveTo(21.25f, 15.981f, 20.019f, 14.75f, 18.5f, 14.75f)
                horizontalLineTo(4.0f)
                curveTo(3.586f, 14.75f, 3.25f, 14.414f, 3.25f, 14.0f)
                close()
            }
        }
        .build()
        return _wind!!
    }

private var _wind: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Wind, contentDescription = "")
    }
}
