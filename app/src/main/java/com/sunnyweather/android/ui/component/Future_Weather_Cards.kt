package com.sunnyweather.android.ui.component
import androidx.core.text.util.LocalePreferences.TemperatureUnit
import android.graphics.Paint
import android.graphics.Path
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.sunnyweather.android.logic.model.data.DailyWeather
import com.sunnyweather.android.logic.model.data.displayName2
import com.sunnyweather.android.log
import com.sunnyweather.android.logic.dayOfWeekChinese
import com.sunnyweather.android.logic.model.RealtimeResponse
import com.sunnyweather.android.logic.model.WeatherCodeConverter
import com.sunnyweather.android.ui.Anime.theme.FontType
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.weather.WeatherViewModel
import kotlinx.coroutines.launch
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Composable
fun Future_Weather_Cards(
    WeatherViewModel: WeatherViewModel,
    dailyWeather: DailyWeather
) {
    val weatherState by WeatherViewModel.state.collectAsState()
    val horizontalItemCount = 7
    val cardWidth = 80.dp
    val rowWidth = horizontalItemCount * cardWidth
    val scrollState1 = rememberLazyListState()
    val scrollState2 = rememberLazyListState()
    // 同步第一个 LazyRow 的滚动到第二个
    LaunchedEffect(scrollState1.isScrollInProgress) {
        snapshotFlow { scrollState1.firstVisibleItemScrollOffset }.collect { offset ->
            if (!scrollState2.isScrollInProgress) {
                scrollState2.scrollToItem(scrollState1.firstVisibleItemIndex, offset)
            }
        }
    }

    // 同步第二个 LazyRow 的滚动到第一个
    LaunchedEffect(scrollState2.isScrollInProgress) {
        snapshotFlow { scrollState2.firstVisibleItemScrollOffset }.collect { offset ->
            if (!scrollState1.isScrollInProgress) {
                scrollState1.scrollToItem(scrollState2.firstVisibleItemIndex, offset)
            }
        }
    }
    Box {
        // 每日天气卡片

        LazyRow(
            modifier = Modifier.height(350.dp)
                .wrapContentWidth(),
            state = scrollState1 // 使用相同的滚动状态
        ) {
            items(horizontalItemCount) { it ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(cardWidth)
                        .padding(top = 16.dp),
                ) {
                    Text(
                        text = when (it) {
                            0 -> "今天"
                            1 -> "明天"
                            else -> dayOfWeekChinese(
                                weatherState.daily[it].date
                            )
                        },
                        fontWeight = FontWeight.Light
                    )

                    Text(
                        text = OffsetDateTime.parse(
                            weatherState.daily[it].date,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmxxx")
                        ).format(DateTimeFormatter.ofPattern("MM月dd日")),
                        fontWeight = FontWeight.Light
                    )

                    // 天气图标
                    Box(
                        modifier = Modifier
                            .width(48.dp)
                            .height(48.dp)
                            .background(Color.Transparent)
                    ) {
                        WeatherCodeConverter.getSky(
                            weatherState.dailyWeather[it].value
                        ).icon()
                    }

                    Column(
                        modifier = Modifier.padding(top = 130.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("风速", fontWeight = FontWeight.Light, fontSize = 16.sp)
                        Text("空气状况", fontWeight = FontWeight.Light, fontSize = 16.sp)
                    }
                }

                if (it < 14) {
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
        LazyRow(
            modifier = Modifier.height(350.dp)
                .wrapContentWidth(),
            state = scrollState2 ,// 使用滚动状态
        ) {
            items(1) {
                // 温度曲线
                Box {
                    LineChart2(
                        modifier = Modifier
                            .width(560.dp)
                            .height(200.dp).offset(y=70.dp),
                        metrics = weatherState.daily
                    )
                    LineChart3(
                        modifier = Modifier
                            .width(560.dp)
                            .height(200.dp).offset(y=150.dp),
                        metrics = weatherState.daily
                    )
                }

            }
        }


    }
}

@Composable
fun LineChart3(
    modifier: Modifier, // Modifier
    metrics: List<RealtimeResponse.Result.Daily.Metrics> ,// 每日温度数据
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
    Canvas(modifier.width(canvasWidth)) { // 使用 Canvas 进行绘制

        val increment = size.width / metrics.size // 每个点的宽度
        val maxTemp = metrics.safeMax { it.max ?: 0f }
        val minTemp = metrics.safeMin { it.min ?: 0f }
        val dy = (maxTemp - minTemp).toFloat()

        val amplifyFactor = 2.0f
        val tempUnit = TemperatureUnit.CELSIUS
        // 使用滚动状态调整绘制内容

        drawIntoCanvas { canvas -> // 使用 Canvas 进行绘制

            // 当动画未完成时，裁剪绘制区域
            if (cur != metrics) {
                canvas.clipRect(Rect(0f, 0f,  size.width*animateFloat, size.height))
            }

            val path = Path() // 创建一个路径对象，用于绘制线条

            // 获取所有温度点并计算其坐标
            val points = metrics.mapIndexed { index, metric ->
                Offset(
                    index * increment + increment / 2, // 水平位置：每个卡片的中心
                    (1 - ((metric.max ?: 0f) - minTemp) / dy) * (size.height * 0.3f) + size.height * 0.2f
                )
            }

            // 设置路径的起点
            path.moveTo(points.first().x, points.first().y)

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


            // 使用渐变填充路径
            val colors = intArrayOf(
                Color.Black.copy(alpha = 1f).toArgb(), // 起始颜色：半透明黑色
                Color.Transparent.toArgb() // 结束颜色：透明
            )
            /*

            val shader = android.graphics.LinearGradient(
                0f, 0f, // 起始点坐标
                0f, 200f, // 结束点坐标
                colors, // 渐变颜色数组
                null, // 颜色分布位置
                android.graphics.Shader.TileMode.CLAMP // 渐变填充方式
            )
             */
            // 使用纯黑色填充路径
            canvas.nativeCanvas.drawPath(
                path,
                Paint().apply {
                    color = Color.Black.toArgb() // 设置纯黑色
                    style = Paint.Style.STROKE // 设置绘制样式为填充
                    isAntiAlias = true // 开启抗锯齿
                    strokeWidth=5f
                }
            )

            // 绘制温度点
            canvas.drawPoints(
                PointMode.Points, // 使用点模式绘制
                points, // 温度点列表
                androidx.compose.ui.graphics.Paint().apply {
                    strokeWidth = 15f // 点的大小
                    strokeCap = StrokeCap.Round // 线条末端为圆角
                    color = Color.Black // 线条颜色为半透明黑色
                }
            )

            // 绘制温度值文本
            val textStyle = TextStyle.Default.copy(fontSize = 15.sp)
            val textPaint = Paint().apply {
                color = Color.Black.toArgb() // 文本颜色为黑色
                textSize = textStyle.fontSize.toPx() // 自动处理密度转换
                typeface = FontType.typeface // 设置字体
            }

            metrics.asSequence().zip(points.asSequence()) // 将天气数据和温度点配对
                .forEachIndexed { index, pair ->
                    val (metric, point) = pair // 获取天气数据和对应的坐标
                    canvas.nativeCanvas.drawText(
                        "${metric.min?.toInt()?.displayName2(tempUnit)}", // 温度显示文本
                        point.x - textStyle.fontSize.value / 2, // 文本 X 坐标
                        point.y - textStyle.fontSize.value / 1.5f, // 文本 Y 坐标
                        textPaint // 绘制文本
                    )
                }
        }
    }
}


@Composable
fun LineChart2(
    modifier: Modifier, // Modifier
    metrics: List<RealtimeResponse.Result.Daily.Metrics> ,// 每日温度数据
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
    Canvas(modifier.width(canvasWidth)) { // 使用 Canvas 进行绘制

        val increment = size.width / metrics.size // 每个点的宽度
        val maxTemp = metrics.safeMax { it.max ?: 0f }
        val minTemp = metrics.safeMin { it.min ?: 0f }
        val dy = (maxTemp - minTemp).toFloat()

        val amplifyFactor = 2.0f
        val tempUnit = TemperatureUnit.CELSIUS
        // 使用滚动状态调整绘制内容

        drawIntoCanvas { canvas -> // 使用 Canvas 进行绘制

            // 当动画未完成时，裁剪绘制区域
            if (cur != metrics) {
                canvas.clipRect(Rect(0f, 0f,  size.width*animateFloat, size.height))
            }

            val path = Path() // 创建一个路径对象，用于绘制线条

            // 获取所有温度点并计算其坐标
            val points = metrics.mapIndexed { index, metric ->
                Offset(
                    index * increment + increment / 2, // 水平位置：每个卡片的中心
                    (1 - ((metric.max ?: 0f) - minTemp) / dy) * (size.height * 0.3f) + size.height * 0.2f
                )
            }

            // 设置路径的起点
            path.moveTo(points.first().x, points.first().y)

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


            // 使用渐变填充路径
            val colors = intArrayOf(
                Color.Black.copy(alpha = 1f).toArgb(), // 起始颜色：半透明黑色
                Color.Transparent.toArgb() // 结束颜色：透明
            )
            /*

            val shader = android.graphics.LinearGradient(
                0f, 0f, // 起始点坐标
                0f, 200f, // 结束点坐标
                colors, // 渐变颜色数组
                null, // 颜色分布位置
                android.graphics.Shader.TileMode.CLAMP // 渐变填充方式
            )
             */
            // 使用纯黑色填充路径
            canvas.nativeCanvas.drawPath(
                path,
                Paint().apply {
                    color = Color.Black.toArgb() // 设置纯黑色
                    style = Paint.Style.STROKE // 设置绘制样式为填充
                    isAntiAlias = true // 开启抗锯齿
                    strokeWidth=5f
                }
            )

            // 绘制温度点
            canvas.drawPoints(
                PointMode.Points, // 使用点模式绘制
                points, // 温度点列表
                androidx.compose.ui.graphics.Paint().apply {
                    strokeWidth = 15f // 点的大小
                    strokeCap = StrokeCap.Round // 线条末端为圆角
                    color = Color.Black // 线条颜色为半透明黑色
                }
            )

            // 绘制温度值文本
            val textStyle = TextStyle.Default.copy(fontSize = 15.sp)
            val textPaint = Paint().apply {
                color = Color.Black.toArgb() // 文本颜色为黑色
                textSize = textStyle.fontSize.toPx() // 自动处理密度转换
                typeface = FontType.typeface // 设置字体
            }

            metrics.asSequence().zip(points.asSequence()) // 将天气数据和温度点配对
                .forEachIndexed { index, pair ->
                    val (metric, point) = pair // 获取天气数据和对应的坐标
                    canvas.nativeCanvas.drawText(
                        "${metric.max?.toInt()?.displayName2(tempUnit)}", // 温度显示文本
                        point.x - textStyle.fontSize.value / 2, // 文本 X 坐标
                        point.y - textStyle.fontSize.value / 1.5f, // 文本 Y 坐标
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