package com.sunnyweather.android.ui.component

import android.graphics.Color.alpha
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.R
import com.sunnyweather.android.logic.model.data.DailyWeather
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.myiconpack.Point
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import com.sunnyweather.android.logic.model.data.LocalTemUnit
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import kotlin.math.abs
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import android.graphics.Path
import android.graphics.Paint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.core.text.util.LocalePreferences
import com.sunnyweather.android.logic.model.data.TemperatureUnit
//import com.sunnyweather.android.data.WeatherDataProvider2
import com.sunnyweather.android.logic.model.data.displayName
import com.sunnyweather.android.log
import com.sunnyweather.android.ui.Anime.theme.FontType
import com.sunnyweather.android.ui.weather.WeatherViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import androidx.compose.ui.graphics.Color


/**
 *     CompositionLocalProvider：这是一个用于在组件树中传递值的工具，类似于 React 的 Context API。LocalTemUnit 是一个 CompositionLocal，TemperatureUnit.Centigrade 是它的默认值。
 *     LazyRow：用于实现水平滚动的列表。它与 RecyclerView 类似，可以优化性能，支持长列表。
 *     Box 和 Row：Box 是叠放布局，Row 是水平排列布局。
 *     forEachIndexed：这是一个 Kotlin 的扩展函数，用于遍历集合并同时获取索引和元素。
 *     alpha：用于设置透明度。
 *     时间格式化：根据索引生成时间，前 12 个小时显示为 AM，后 12 个小时显示为 PM。
 *     LineChart：这是一个自定义组件，用于绘制折线图，后续会详细解释。
 */
@Composable
fun HourlyWeatherChart(
    modifier: Modifier = Modifier, // 默认的 Modifier
    dailyWeather: DailyWeather, // 每日天气数据
    WeatherViewModel:WeatherViewModel
) {
    val weatherState by WeatherViewModel.state.collectAsState()
    // 使用 CompositionLocalProvider 提供一个温度单位的默认值
    BoxWithConstraints(
    ){
        // 获取父容器约束
        val parentWidth = maxWidth
        val parentHeight = maxHeight
        CompositionLocalProvider(
            LocalTemUnit provides TemperatureUnit.Centigrade // 提供温度单位为摄氏度
        ) {
            LazyRow( // 使用 LazyRow 实现水平滚动列表
                modifier
                    .wrapContentHeight() // 设置高度为 100dp
                    .wrapContentWidth() // 宽度根据内容自动调整
            ) {
                item { // 在 LazyRow 中添加一个内容项
                    Box( // Box 布局，用于包含内容
                        Modifier
                            .height((parentHeight.value*0.3).dp)// 填充最大高度
                            .width((parentWidth.value*2.5f).dp) // 设置宽度为 900dp
                    ) {
                        LineChart( // 调用 LineChart 组件绘制折线图
                            Modifier.fillMaxSize(), // 填充整个 Box 的大小
                            dailyWeather // 传递每日天气数据
                        )
                        Row(Modifier.fillMaxSize()) { // 使用 Row 布局，内容水平排列
                            dailyWeather.hourly.forEachIndexed { index, it -> // 遍历每小时天气数据
                                Column( // 使用 Column 布局，内容垂直排列
                                    modifier = Modifier
                                        .weight(1f) // 每个列占相等宽度
                                        .alpha(0.6f) // 设置透明度为 60%
                                        .align(Alignment.Bottom) // 内容底部对齐
                                ) {
                                    Box( // Box 布局，用于显示天气图标
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally) // 图标水平居中
                                            .scale(0.9f) // 图标缩放为 60%
                                    ) {
                                        it.weather.icon() // 显示天气图标
                                    }
                                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmxxx")
                                    val offsetDateTime = OffsetDateTime.parse(weatherState.hourly[index].datetime, formatter)
                                    val time = offsetDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                                    Text( // 显示时间文本
                                        /*
                                        if (index < 12) "${index + 1} AM" // 根据索引生成 AM 或 PM 时间
                                        else "${index - 11} PM",
                                         */
                                        time,
                                        fontSize = 11.sp, // 设置字体大小为 9sp
                                        fontWeight = FontWeight.Black, // 设置字体为轻体
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally) // 文本水平居中
                                            .clearAndSetSemantics { } // 清除语义信息
                                    )
                                    "dailyWeather".log(index.toString())
                                    Divider(
                                        color = Color.Gray, // 设置分割线的颜色
                                        thickness = 1.dp // 设置分割线的厚度
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}


/**
 *    Path 和贝塞尔曲线：Path 是 Android 中用于绘制复杂图形的工具。贝塞尔曲线通过控制点来平滑连接两个点，使折线图看起来更圆滑。
 *     渐变填充：LinearGradient 是一种线性渐变，这里用于为折线图的填充区域创建渐变效果。
 *     动画：animateFloatAsState 是 Compose 中的动画 API，用于创建一个从起点到终点的动画值。这里的动画用于在数据变化时平滑地切换图表。
 *     Shader.TileMode.CLAMP：这个模式用于控制渐变的填充方式，确保渐变填充不溢出。
 *     Canvas 和 NativeCanvas：Canvas 是 Compose 中的低级绘制 API，NativeCanvas 是它的原生实现，可以直接调用 Android 的绘制方法（如 drawPath 和 drawText）。
 *     Paint 和绘制：Paint 是用于绘制图形和文本的工具，通过设置颜色、透明度、字体等属性，可以精确控制绘制效果。
 *     文本绘制：文本的坐标和样式通过 drawText 和 Paint 设置，确保文本与温度点对齐并正确显示温度值。
 *
 */

@Composable
fun LineChart(
    modifier: Modifier, // Modifier
    dailyWeather: DailyWeather // 每日天气数据
) {
    val (cur, setCur) = remember { mutableStateOf<DailyWeather?>(null) } // 用于存储当前动漫变化的天气数据
    var trigger by remember { mutableStateOf(0f) } // 动画触发器

    DisposableEffect(dailyWeather) { // 监听 dailyWeather 的变化
        trigger = 1f // 当 dailyWeather 变化时，设置动画触发器为 1f
        onDispose { } // 动画结束后不执行任何操作
    }

    // 使用 animateFloatAsState 创建一个动画值，从 0 到 1
    val animateFloat by animateFloatAsState(
        targetValue = trigger, // 动画目标值
        animationSpec = tween(1500) // 定义动画时长为 1500 毫秒
    ) {
        setCur(dailyWeather) // 回调函数，设置当前天气数据
        trigger = 0f // 重置触发器
    }

    val tempUnit = LocalTemUnit.current // 获取当前的温度单位
    Canvas(modifier) { // 使用 Canvas 进行绘制

        val increment = size.width / dailyWeather.hourly.size // 计算每个温度点之间的宽度增量
        val max = dailyWeather.hourly.maxOf { it.temperature } // 获取最高温度
        val min = dailyWeather.hourly.minOf { it.temperature } // 获取最低温度
        val dy = (max - min).toFloat() // 计算温度范围的差值

        drawIntoCanvas { canvas -> // 使用 Canvas 进行绘制

            // 当动画未完成时，裁剪绘制区域
            if (cur != dailyWeather) {
                canvas.clipRect(
                    Rect(0f, 0f, size.width * animateFloat, size.height) // 裁剪区域
                )
            }

            val path = Path() // 创建一个路径对象，用于绘制线条

            // 获取所有温度点并计算其坐标
            val points = dailyWeather.hourly.mapIndexed { index, hourlyWeather ->
                Offset(
                    increment * index + increment / 2, // X 坐标
                    (1 - (hourlyWeather.temperature - min) / dy) * (size.height * 0.3f) + size.height * 0.2f // Y 坐标
                )
            }

            // 设置路径的起点和终点
            path.moveTo(0f, points.first().y)
            path.lineTo(points.first().x, points.first().y)

            // 使用贝塞尔曲线连接各个温度点
            (0..points.size - 2).forEach { index ->
                val startP = points[index]
                val endP = points[index + 1]

                val p2: Offset
                val p3: Offset
                val cx = (startP.x + endP.x) / 2
                val dy = abs((endP.y - startP.y) / 4)

                if (endP.y < startP.y) { // 如果下一个点低于当前点
                    p2 = Offset(cx, startP.y - dy)
                    p3 = Offset(cx, endP.y + dy)
                } else { // 如果下一个点高于当前点
                    p2 = Offset(cx, startP.y + dy)
                    p3 = Offset(cx, endP.y - dy)
                }

                path.cubicTo(p2.x, p2.y, p3.x, p3.y, endP.x, endP.y) // 绘制贝塞尔曲线
            }

            // 连接路径的最后一部分
            path.lineTo(points.last().x + increment / 2, points.last().y)
            path.lineTo(points.last().x + increment / 2, size.height)
            path.lineTo(0f, size.height)
            path.lineTo(0f, points.first().y)

            // 使用渐变填充路径
            val colors = listOf(
                Color(0xFFFF3300).copy(alpha = 0.8f),  // 高温红，完全不透明
                Color(0xFFFFFF00).copy(alpha = 0.6f),  // 中温黄，80% 不透明
                Color(0xFF00FF99).copy(alpha = 0.4f),  // 常温绿，60% 不透明
                Color(0xFF0099FF).copy(alpha = 0.1f)   // 低温蓝，40% 不透明
            )
            val positions = floatArrayOf(
                0.0f,   // 对应低温位置
                0.3f,   // 常温起始点
                0.7f,   // 中温起始点
                1.0f    // 高温终点
            )
            val shader = android.graphics.LinearGradient(
                0f, 0f, // 起始点坐标
                0f, 200f, // 结束点坐标
                colors.map { it.toArgb() }.toIntArray(), // 渐变颜色数组, // 渐变颜色数组
                positions, // 颜色分布位置
                android.graphics.Shader.TileMode.CLAMP // 渐变填充方式
            )

            // 使用 Paint 绘制路径
            canvas.nativeCanvas.drawPath(
                path,
                Paint().apply {
                    this.shader = shader // 设置渐变效果
                    style = Paint.Style.FILL // 设置绘制样式为填充
                    isAntiAlias = true // 开启抗锯齿
                }
            )

            // 绘制温度点
            canvas.drawPoints(
                PointMode.Points, // 使用点模式绘制
                points, // 温度点列表
                androidx.compose.ui.graphics.Paint().apply {
                    strokeWidth = 12f // 线条宽度为 8dp
                    strokeCap = StrokeCap.Round // 线条末端为圆角
                    color = Color.Black.copy(0.6f) // 线条颜色为半透明黑色
                }
            )

            // 绘制温度值文本
            val textStyle = TextStyle.Default.copy(fontSize = 12.sp)
            val textPaint = Paint().apply {
                color = Color.Black.toArgb() // 文本颜色为黑色
                textSize = textStyle.fontSize.toPx() // 自动处理密度转换
                typeface = FontType.typeface // 设置字体
                alpha = 70
            }

            dailyWeather.hourly.asSequence().zip(points.asSequence()) // 将天气数据和温度点配对
                .forEachIndexed { index, pair ->
                    val (weather, point) = pair // 获取天气数据和对应的坐标
                    canvas.nativeCanvas.drawText(
                        weather.temperature.displayName(tempUnit), // 温度显示文本
                        point.x - textStyle.fontSize.value / 2, // 文本 X 坐标
                        point.y - textStyle.fontSize.value / 1.5f, // 文本 Y 坐标
                        textPaint // 绘制文本
                    )
                }
        }
    }
}
