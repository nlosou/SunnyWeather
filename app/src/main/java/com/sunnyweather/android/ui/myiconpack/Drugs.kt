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

public val MyIconPack.Drugs: ImageVector
    get() {
        if (_drugs != null) {
            return _drugs!!
        }
        _drugs = Builder(name = "Drugs", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF020202)),
                    strokeLineWidth = 1.91f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(12.95f, 2.474f)
                lineTo(12.95f, 2.474f)
                arcTo(3.37f, 3.37f, 90.0f, false, true, 12.95f, 7.24f)
                lineTo(7.229f, 12.96f)
                arcTo(3.37f, 3.37f, 90.0f, false, true, 2.463f, 12.96f)
                lineTo(2.463f, 12.96f)
                arcTo(3.37f, 3.37f, 90.0f, false, true, 2.463f, 8.194f)
                lineTo(8.184f, 2.474f)
                arcTo(3.37f, 3.37f, 90.0f, false, true, 12.95f, 2.474f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF020202)),
                    strokeLineWidth = 1.91f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(16.76f, 16.76f)
                moveToRelative(-5.72f, 0.0f)
                arcToRelative(5.72f, 5.72f, 0.0f, true, true, 11.44f, 0.0f)
                arcToRelative(5.72f, 5.72f, 0.0f, true, true, -11.44f, 0.0f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF020202)),
                    strokeLineWidth = 1.91f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(20.77f, 20.77f)
                lineTo(12.95f, 12.95f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF020202)),
                    strokeLineWidth = 1.91f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(10.09f, 10.09f)
                lineTo(5.33f, 5.33f)
            }
        }
        .build()
        return _drugs!!
    }

private var _drugs: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Drugs, contentDescription = "")
    }
}
