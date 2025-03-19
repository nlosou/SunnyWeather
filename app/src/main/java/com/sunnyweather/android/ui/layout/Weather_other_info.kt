
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.AtmosphericPressure
import com.sunnyweather.android.ui.component.Humidity_table
import com.sunnyweather.android.ui.component.LifeIndexSection
import com.sunnyweather.android.ui.component.Somatosensory
import com.sunnyweather.android.ui.component.Ultraviolet
import com.sunnyweather.android.ui.component.Wind
import com.sunnyweather.android.ui.myiconpack.Car
import com.sunnyweather.android.ui.myiconpack.Clothes
import com.sunnyweather.android.ui.myiconpack.Cosmetic
import com.sunnyweather.android.ui.myiconpack.Drugs
import com.sunnyweather.android.ui.myiconpack.Sport
import com.sunnyweather.android.ui.myiconpack.Umbrella
import com.sunnyweather.android.ui.theme.SunnyWeatherTheme

@Composable
fun Weather_other_info(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        // 紫外线和湿度
        Row(modifier = Modifier.fillMaxWidth()) {
            WeatherCard(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1.8f), // 设置宽高相等
                title = "紫外线",
                content = "中"
            )
            Spacer(modifier=Modifier.padding(5.dp))
            WeatherCard(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1.8f), // 设置宽高相等
                title = "湿度",
                content = "42%"
            )
        }

        // 体感和风
        Row(modifier = Modifier.fillMaxWidth()) {
            WeatherCard(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1.8f), // 设置宽高相等
                title = "体感",
                content = "15°"
            )
            Spacer(modifier=Modifier.padding(5.dp))
            WeatherCard(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1.8f), // 设置宽高相等
                title = "西南风",
                content = "2级"
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            WeatherCard(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1.8f), // 设置宽高相等
                title = "日落",
                content = "15°"
            )
            Spacer(modifier=Modifier.padding(5.dp))
            WeatherCard(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1.8f), // 设置宽高相等
                title = "气压",
                content = "2级"
            )
        }
        // 生活指数
        LifeIndexSection()
    }
}

@Composable
fun WeatherCard(
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
            when(title)
            {
                "湿度"->Box(modifier = Modifier.weight(1f)){ Humidity_table("")}
                "紫外线"->Box(modifier = Modifier.weight(1f)){ Ultraviolet("") }
                "体感"->Box(modifier=Modifier.weight(1f)){ Somatosensory("") }
                "西南风"->Box(modifier=Modifier.weight(1f)){ Wind("") }
                "气压"-> Box(modifier=Modifier.weight(1f)){ AtmosphericPressure("") }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview11() {
    SunnyWeatherTheme {
        Weather_other_info(Modifier)
    }
}