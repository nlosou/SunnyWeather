package com.sunnyweather.android.ui.component
import androidx.core.text.util.LocalePreferences.TemperatureUnit
import android.graphics.Paint
import android.graphics.Path
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.constraintlayout.compose.ConstraintLayout
import com.sunnyweather.android.data.DailyWeather
import com.sunnyweather.android.data.LocalTemUnit
import com.sunnyweather.android.data.displayName
import com.sunnyweather.android.data.displayName2
import com.sunnyweather.android.log
import com.sunnyweather.android.logic.dayOfWeekChinese
import com.sunnyweather.android.logic.model.RealtimeResponse
import com.sunnyweather.android.logic.model.WeatherCodeConverter
import com.sunnyweather.android.ui.Anime.theme.FontType
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.weather.WeatherViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Composable
fun Future_Weather_Cards(WeatherViewModel: WeatherViewModel, dailyWeather: DailyWeather) {
    Column(
        modifier = Modifier
            .fillMaxWidth().offset(y=200.dp)
    ) {
        // LazyRow for daily weather cards
        val horizontalItemCount = 7
        val cardWidth = 90.dp // 设置每个卡片的宽度
        val rowWidth = horizontalItemCount * cardWidth

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .width(rowWidth) // 设置 LazyRow 的宽度
        ) {
            items(horizontalItemCount) { item ->
                Surface(
                    color = Color.Transparent
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.width(cardWidth) // 每个子项的宽度
                    ) {
                        // 日历卡片的内容
                        when (item) {
                            0 -> Text("今天", fontWeight = FontWeight.Light)
                            1 -> Text("明天", fontWeight = FontWeight.Light)
                            else -> Text(
                                dayOfWeekChinese(WeatherViewModel.daily.value[item].date),
                                fontWeight = FontWeight.Light
                            )
                        }

                        // 日期
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmxxx")
                        val offsetDateTime = OffsetDateTime.parse(WeatherViewModel.daily.value[item].date, formatter)
                        val time = offsetDateTime.format(DateTimeFormatter.ofPattern("MM月dd日"))
                        Text(time, fontWeight = FontWeight.Light)

                        // 天气图标
                        WeatherCodeConverter.getSky(WeatherViewModel.daily_weather.value[item].value)
                            .icon()

                        Spacer(modifier = Modifier.padding(25.dp))
                    }

                    // 垂直分割线
                    if (item < 14) {
                        Box(
                            modifier = Modifier
                                .height(250.dp)
                                .width(1.dp)
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.LightGray.copy(alpha = 0.3f),
                                            Color.LightGray,
                                            Color.LightGray.copy(alpha = 0.3f)
                                        )
                                    )
                                )
                                .padding(horizontal = 1.dp)
                        )
                    }
                }
            }
        }

        // LineChart2
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .width(rowWidth) // 设置 Canvas 的宽度
        ) {
            if (WeatherViewModel.daily.value.isNotEmpty()) {
                LineChart2(
                    modifier = Modifier
                        .width(rowWidth)
                        .height(200.dp),
                    metrics = WeatherViewModel.daily.value
                )
            }
        }

        // 风速和空气状况
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Spacer(modifier = Modifier.padding(25.dp))
            Text("风速", fontWeight = FontWeight.Light, fontSize = 16.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Text("空气状况", fontWeight = FontWeight.Light, fontSize = 16.sp)
        }
    }
}
@Composable
fun LineChart2(
    modifier: Modifier, // Modifier
    metrics: List<RealtimeResponse.Result.Daily.Metrics> // 每日温度数据
) {
    val cardWidth = 90.dp // 卡片宽度
    val horizontalItemCount = 7
    val canvasWidth = cardWidth * horizontalItemCount


    "LineChart2".log(metrics.toString())
    val (cur, setCur) = remember { mutableStateOf<List<RealtimeResponse.Result.Daily.Metrics>?>(null) } // 用于存储当前动漫变化的天气数据
    var trigger by remember { mutableStateOf(0f) } // 动画触发器

    DisposableEffect(metrics) { // 监听 metrics 的变化
        trigger = 1f // 当 metrics 变化时，设置动画触发器为 1f
        onDispose { } // 动画结束后不执行任何操作
    }
    "LineChart2".log("2")
    // 使用 animateFloatAsState 创建一个动画值，从 0 到 1
    val animateFloat by animateFloatAsState(
        targetValue = trigger, // 动画目标值
        animationSpec = tween(1500) // 定义动画时长为 1500 毫秒
    ) {
        setCur(metrics) // 回调函数，设置当前天气数据
        trigger = 0f // 重置触发器
    }
    "LineChart2".log("3")
    val tempUnit = TemperatureUnit.CELSIUS// 获取当前的温度单位
    "LineChart2".log("4")
    Canvas(modifier) { // 使用 Canvas 进行绘制

        val increment = size.width / metrics.size // 每个点的宽度
        val maxTemp = metrics.safeMax { it.max ?: 0f }
        val minTemp = metrics.safeMin { it.min ?: 0f }
        val dy = (maxTemp - minTemp).toFloat()

        val amplifyFactor = 2.0f
        val tempUnit = TemperatureUnit.CELSIUS

        drawIntoCanvas { canvas -> // 使用 Canvas 进行绘制

            // 当动画未完成时，裁剪绘制区域
            if (cur != metrics) {
                canvas.clipRect(
                    Rect(0f, 0f, size.width * animateFloat, size.height) // 裁剪区域
                )
            }

            val path = Path() // 创建一个路径对象，用于绘制线条

            // 获取所有温度点并计算其坐标
            val points = metrics.mapIndexed { index, metric ->
                Offset(
                    index * increment + increment / 2, // 水平位置：每个卡片的中心
                    (1 - ((metric.max ?: 0f) - minTemp) / dy) * (size.height * 0.3f) + size.height * 0.2f
                )
            }

            // 设置路径的起点和终点
            path.moveTo(0f, points.first().y)
            path.lineTo(points.first().x, points.first().y)

            // 使用直线连接各个温度点
            (0 until points.size - 1).forEach { index ->
                val x1 = points[index].x
                val y1 = points[index].y
                val x2 = points[index + 1].x
                val y2 = points[index + 1].y

                path.lineTo(x2, y2)
            }

            // 绘制顺序：从最高温度到最低温度
            val maxPoints = points.map { it.copy(y = ((1 - ((minTemp + dy) - minTemp) / dy) * (size.height * 0.3f) + size.height * 0.2f)) }
            val minPoints = points.map { it.copy(y = ((1 - (0f / dy) * (size.height * 0.3f) + size.height * 0.2f)) ) }

            // 连接路径的最后一部分
            path.lineTo(points.last().x + increment / 2, points.last().y)
            path.lineTo(points.last().x + increment / 2, size.height)
            path.lineTo(0f, size.height)
            path.lineTo(0f, points.first().y)

            // 使用渐变填充路径
            val colors = intArrayOf(
                Color.Black.copy(alpha = 1f).toArgb(), // 起始颜色：半透明黑色
                Color.Transparent.toArgb() // 结束颜色：透明
            )

            val shader = android.graphics.LinearGradient(
                0f, 0f, // 起始点坐标
                0f, 200f, // 结束点坐标
                colors, // 渐变颜色数组
                null, // 颜色分布位置
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
            var textSize = 10.sp.toPx() // 文本大小为 10sp
            val textPaint = Paint().apply {
                color = Color.Black.toArgb() // 文本颜色为黑色
                textSize = textSize // 设置文本大小
                alpha = 90 // 文本透明度为 90%
                typeface = FontType.typeface // 设置字体
            }

            metrics.asSequence().zip(points.asSequence()) // 将天气数据和温度点配对
                .forEachIndexed { index, pair ->
                    val (metric, point) = pair // 获取天气数据和对应的坐标
                    canvas.nativeCanvas.drawText(
                        "${metric.max?.toInt()?.displayName2(tempUnit)} / ${metric.min?.toInt()?.displayName2(tempUnit)}", // 温度显示文本
                        point.x - textSize / 2, // 文本 X 坐标
                        point.y - textSize / 1.5f, // 文本 Y 坐标
                        textPaint // 绘制文本
                    )
                }
        }
    }
}

// 扩展函数：计算列表中最大值
fun <T> List<T>.safeMax(transform: (T) -> Float): Float {
    return if (isEmpty()) {
        0f
    } else {
        maxOfOrNull { transform(it) } ?: 0f
    }
}

// 扩展函数：计算列表中最小值
fun <T> List<T>.safeMin(transform: (T) -> Float): Float {
    return if (isEmpty()) {
        0f
    } else {
        minOfOrNull { transform(it) } ?: 0f
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    val WeatherViewModel = remember { WeatherViewModel() }
    SunnyWeatherTheme {
       // Future_Weather_Cards(WeatherViewModel)
    }
}