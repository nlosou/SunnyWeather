package com.sunnyweather.android.ui.component


import android.graphics.Color.rgb
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.log
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.weather.WeatherState
import java.lang.Math.pow
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sqrt


@Composable
fun SunriseSunset(name: String, modifier: Modifier = Modifier,weatherState:WeatherState) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier.background(Color.Transparent)
    ) {
        // 使用remember和mutableStateOf管理动态角度
        var targetAngle by remember { mutableStateOf(270f) }
        // 使用animateFloatAsState创建动画效果
        val animatedAngle by animateFloatAsState(
            targetValue = targetAngle,
            animationSpec = tween(durationMillis = 1000) // 动画时长1秒
        )
        // 获取父容器约束
        val parentWidth = maxWidth
        val parentHeight = maxHeight
        "parentWidth".log(parentWidth.value.toString())
        // 按父容器宽度 20% 设置图标尺寸
        val iconSize = parentWidth * 0.2f
        // 按父容器宽度 8% 设置字体大小
        val textSize = (parentWidth.value * 0.15f).sp
        // 按父容器宽度 3% 设置线条宽度
        val strokeWidthDp = parentWidth * 0.05f
        Text(
            text = weatherState.temp[0].result.daily.astro[0].sunrise.time,
            modifier = Modifier.offset(y=parentHeight*0.45f, x = parentHeight*-0.4f),
            fontSize = textSize,
            color = Color.Gray
        )
        Text(
            text = weatherState.temp[0].result.daily.astro[2].sunset.time,
            modifier = Modifier.offset(y=parentHeight*0.45f, x = parentHeight*0.4f),
            fontSize = textSize,
            color = Color.Gray
        )
        Box (modifier = Modifier
          .dashHorizontalLine(
                color = Color.Gray,
                strokeWidth = strokeWidthDp*0.1f,
                dashLength = strokeWidthDp*0.5f
            )
            ){
            Canvas(modifier = Modifier
                .fillMaxSize()
                .rotate(0f))
            {
                // 将线条宽度转换为像素
                val strokeWidthPx = strokeWidthDp.toPx()
                val radius = size.minDimension / 2f - strokeWidthPx
                //背景
                // 绘制正态分布曲线
                drawCosCurve(size = size, strokeWidthPx)

            }
        }

    }
}

fun Modifier.dashHorizontalLine(
    color: Color = Color.Black,
    strokeWidth: Dp = 1.dp,
    dashLength: Dp = 5.dp
) = drawBehind {
    val strokeWidthPx = strokeWidth.toPx()
    val dashLengthPx = dashLength.toPx()

    // 计算水平线Y轴中心位置
    val yPos = size.height / 1.5

    drawLine(
        color = color,
        start = Offset(0f, yPos.toFloat()),
        end = Offset(size.width, yPos.toFloat()),
        strokeWidth = strokeWidthPx,
        pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashLengthPx, dashLengthPx),
            phase = 0f
        )
    )
}
private fun DrawScope.drawCosCurve(size: Size, strokeWidth: Float) {
    // 参数配置
    val amplitude = size.height * 0.3f    // 振幅（占画布高度的40%）
    val frequency = 1f                    // 波形数量（完整周期数）
    val xRange = 2 * PI.toFloat()          // X轴数学范围
    val centerY = size.height / 2f         // 画布垂直中线
    // 分界线Y坐标与虚线位置保持一致
    val dividerY = size.height / 1.5f
    // 坐标映射函数
    fun mapX(mathX: Float): Float {
        return (mathX + PI.toFloat()) * (size.width / xRange)
    }

    fun mapY(mathY: Float): Float {
        return centerY - mathY * amplitude // Y轴翻转+居中处理
    }
    val upperPath = Path()
    val lowerPath = Path()
    var currentPoint = Offset(mapX(-PI.toFloat()), mapY(cos(-PI.toFloat())))
    var currentPoint2=currentPoint
    var upperPathStart= mutableListOf <Offset>()
    var intersect= mutableListOf <Offset>()

    var X = -PI.toFloat() + 0.05f
    while (X <= PI.toFloat()) {
        val nextPoint = Offset(mapX(X), mapY(cos(X * frequency)))
        if (crossesDivider(currentPoint, nextPoint, dividerY)) {
            // 分割路径
            upperPathStart.add(nextPoint)

        }
        currentPoint2 = nextPoint
        X += 0.05f
    }

    upperPath.moveTo(upperPathStart[0].x, upperPathStart[0].y)
    lowerPath.moveTo(currentPoint.x, currentPoint.y)
    // 构建路径
    // 遍历生成路径点
    var x = -PI.toFloat() + 0.05f
    while (x <= PI.toFloat()) {
        val nextPoint = Offset(mapX(x), mapY(cos(x * frequency)))
        // 检测是否跨越分界线
        if (crossesDivider(currentPoint, nextPoint, dividerY)) {
            val intersect = calculateIntersection(
                currentPoint,
                nextPoint,
                dividerY
            )
            upperPath.lineTo(intersect.x, intersect.y)
            lowerPath.lineTo(upperPathStart[0].x, upperPathStart[0].y)
            // 交换路径归属
            if (nextPoint.y < dividerY) {
                lowerPath.moveTo(intersect.x, intersect.y)
                upperPath.moveTo(intersect.x, intersect.y)
            } else {
                upperPath.moveTo(intersect.x, intersect.y)
                lowerPath.moveTo(intersect.x, intersect.y)
            }
        }
        // 添加点到对应路径
        if (nextPoint.y < dividerY) {
            upperPath.lineTo(nextPoint.x, nextPoint.y)
        } else {
            lowerPath.lineTo(nextPoint.x, nextPoint.y)
        }

        currentPoint = nextPoint
        x += 0.05f
    }

    // 绘制执行
    drawPath(
        path = lowerPath,
        color = Color(rgb(98,100,145)),
        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )

    drawPath(
        path = upperPath,
        color = Color(rgb(251,202,107)),
        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )

}
// 检测线段是否跨越分界线
private fun crossesDivider(
    start: Offset,
    end: Offset,
    dividerY: Float
): Boolean {
    return (start.y < dividerY && end.y >= dividerY) ||
            (start.y >= dividerY && end.y < dividerY)
}

// 计算分界交点（线性插值法）
private fun calculateIntersection(
    start: Offset,
    end: Offset,
    dividerY: Float
): Offset {
    val t = (dividerY - start.y) / (end.y - start.y)
    return Offset(
        start.x + t * (end.x - start.x),
        dividerY
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview25() {
    SunnyWeatherTheme {
        //SunriseSunset("Android")
    }
}