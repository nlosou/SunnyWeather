package com.sunnyweather.android.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

// 修改后的数据类，直接使用 Compose 的 Color 类型
data class UVLevel(
    val min: Int,
    val max: Int,
    val color: Color,
    val description: String
)

@Composable
fun UVIndexColorScale(
    currentUV: Int = 0,
    onUVSelected: (Int) -> Unit = {}
) {
    // 使用正确的 Color 定义方式
    val uvLevels = listOf(
        UVLevel(0, 2, Color(0xFF4CAF50), "低"),
        UVLevel(3, 5, Color(0xFFFFEB3B), "中"),
        UVLevel(6, 7, Color(0xFFFF9800), "高"),
        UVLevel(8, 10, Color(0xFFF44336), "很高"),
        UVLevel(11, 15, Color(0xFF9C27B0), "极高")
    )

    // 修正颜色渐变定义
    val colorStops = listOf(
        0.0f to Color(0xFF4CAF50),
        0.2f to Color(0xFFFFEB3B),
        0.47f to Color(0xFFFF9800),
        0.67f to Color(0xFFF44336),
        0.73f to Color(0xFF9C27B0),
        1.0f to Color(0xFF9C27B0)
    )

    var selectedUV by remember { mutableStateOf(currentUV) }

    BoxWithConstraints(
        modifier = Modifier
            .padding(16.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    val uvValue = (change.position.x / size.width) * 15
                    selectedUV = uvValue.roundToInt().coerceIn(0..15)
                    onUVSelected(selectedUV)
                }
            }
    ) {
        val width = maxWidth
        val height = 48.dp

        Column {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .background(Color.White)
            ) {
                drawRect(
                    brush = Brush.horizontalGradient(
                        colorStops = colorStops.toTypedArray(),
                        startX = 0f,
                        endX = size.width
                    ),
                    size = size
                )
            }

            Canvas(modifier = Modifier.fillMaxWidth().height(24.dp)) {
                (0..15).forEach { uv ->
                    val xPos = (uv.toFloat() / 15) * size.width
                    drawLine(
                        color = Color.Black,
                        start = Offset(xPos, 0f),
                        end = Offset(xPos, 8.dp.toPx()),
                        strokeWidth = 1.dp.toPx()
                    )
                    if (uv % 3 == 0) {
                        drawContext.canvas.nativeCanvas.apply {
                            drawText(
                                uv.toString(),
                                xPos - 4.dp.toPx(),
                                20.dp.toPx(),
                                android.graphics.Paint().apply {
                                    color = Color.Black.hashCode()
                                    textSize = 12.sp.toPx()
                                }
                            )
                        }
                    }
                }
            }

            val currentLevel = uvLevels.first {
                selectedUV in it.min..it.max
            }

            Text(
                text = "UV指数: $selectedUV - ${currentLevel.description}",
                color = currentLevel.color,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUVIndexColorScale() {
    Column(modifier = Modifier.background(Color.White)) {
        UVIndexColorScale(currentUV = 5)
    }
}