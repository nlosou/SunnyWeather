
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sunnyweather.android.ui.component.AtmosphericPressure
import com.sunnyweather.android.ui.component.Humidity_table
import com.sunnyweather.android.ui.component.LifeIndexSection
import com.sunnyweather.android.ui.component.Somatosensory
import com.sunnyweather.android.ui.component.SunriseSunset
import com.sunnyweather.android.ui.component.Ultraviolet
import com.sunnyweather.android.ui.component.Wind
import com.sunnyweather.android.ui.component.rainfallEntrance
import com.sunnyweather.android.ui.place.PlaceViewModel
import com.sunnyweather.android.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.weather.WeatherState
import com.sunnyweather.android.ui.weather.WeatherViewModel

@Composable
fun Weather_other_info(modifier: Modifier, navController: NavController, mainViewModel: PlaceViewModel, WeatherViewModel: WeatherViewModel) {
    val weatherState by WeatherViewModel.state.collectAsState()
    val windDirection = when (weatherState.temp[0].result.realtime.wind.direction) {
        in 348.76..360.0 -> "北"
        in 0.0..11.25 -> "北"
        in 11.26..33.75 -> "北东北"
        in 33.76..56.25 -> "东北"
        in 56.26..78.75 -> "东东北"
        in 78.76..101.25 -> "东"
        in 101.26..123.75 -> "东东南"
        in 123.76..146.25 -> "东南"
        in 146.26..168.75 -> "南东南"
        in 168.76..191.25 -> "南"
        in 191.26..213.75 -> "南西南"
        in 213.76..236.25 -> "西南"
        in 236.26..258.75 -> "西西南"
        in 258.76..281.25 -> "西"
        in 281.26..303.75 -> "西西北"
        in 303.76..326.25 -> "西北"
        in 326.26..348.75 -> "北西北"
        else -> "无数据"
    }
    val windSpeed = when (weatherState.temp[0].result.realtime.wind.speed) {
        in 0.0..0.3 -> "0级"
        in 0.3..1.6 -> "1级"
        in 1.6..3.4 -> "2级"
        in 3.4..5.5 -> "3级"
        in 5.5..8.0 -> "4级"
        in 8.0..10.8 -> "5级"
        in 10.8..13.9 -> "6级"
        in 13.9..17.2 -> "7级"
        in 17.2..20.8 -> "8级"
        in 20.8..24.5 -> "9级"
        in 24.5..28.5 -> "10级"
        in 28.5..32.7 -> "11级"
        in 32.7..37.0 -> "12级"
        in 37.0..41.5 -> "13级"
        in 41.5..46.2 -> "14级"
        in 46.2..51.0 -> "15级"
        in 51.0..56.1 -> "16级"
        in 56.1..61.3 -> "17级"
        else -> "无数据"
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        // 紫外线和湿度
        Row(modifier = Modifier.fillMaxWidth()) {
            WeatherCard(
                weatherState,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1.8f), // 设置宽高相等
                title = "紫外线",
                content = weatherState.temp[0].result.realtime.life_index.ultraviolet.desc
            )
            Spacer(modifier=Modifier.padding(5.dp))
            WeatherCard(
                weatherState,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1.8f), // 设置宽高相等
                title = "湿度",
                content = "${(weatherState.temp[0].result.realtime.humidity*100).toInt()}%"
            )
        }
        // 体感和风
        Row(modifier = Modifier.fillMaxWidth()) {
            WeatherCard(
                weatherState,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1.8f), // 设置宽高相等
                title = "体感",
                content = "${weatherState.temp[0].result.realtime.apparent_temperature.toInt()}°"
            )
            Spacer(modifier=Modifier.padding(5.dp))
            WeatherCard(
                weatherState,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1.8f), // 设置宽高相等
                title = windDirection+"风",
                content = windSpeed
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            WeatherCard(
                weatherState,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1.8f), // 设置宽高相等
                title = "日出",
                content = weatherState.temp[0].result.daily.astro[0].sunrise.time
            )
            Spacer(modifier=Modifier.padding(5.dp))
            WeatherCard(
                weatherState,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1.8f), // 设置宽高相等
                title = "气压",
                content = ((weatherState.temp[0].result.realtime.pressure)/100).toInt().toString()
            )
        }
        // 生活指数
        Spacer(Modifier.padding(2.dp))
        LifeIndexSection()
        Spacer(Modifier.padding(5.dp))
        rainfallEntrance(navController,mainViewModel,WeatherViewModel)
    }
}

@Composable
fun WeatherCard(
    weatherState:WeatherState,
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    subContent: String? = null,
    iconId: Int? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        shape = RoundedCornerShape(20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 15.sp,
                    )
                )
                Text(
                    text = content,
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                subContent?.let {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = subContent,
                        style = TextStyle(
                            fontSize = 15.sp,
                        )
                    )
                }
            }
            when {
                title.contains("湿度") -> Box(modifier = Modifier.weight(1f)) { Humidity_table("", Modifier, weatherState) }
                title.contains("紫外线") -> Box(modifier = Modifier.weight(1f)) { Ultraviolet("", Modifier, weatherState) }
                title.contains("体感") -> Box(modifier = Modifier.weight(1f)) { Somatosensory("", Modifier, weatherState) }
                title.contains("风") -> Box(modifier = Modifier.weight(1f)) { Wind("") }
                title.contains("气压") -> Box(modifier = Modifier.weight(1f)) { AtmosphericPressure("") }
                title.contains("日")-> Box(modifier=Modifier.weight(1f)){SunriseSunset("",Modifier,weatherState)}
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview11() {
    SunnyWeatherTheme {
        //Weather_other_info(Modifier)
    }
}