import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.MyIconPack
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
        // 降水预测
        /*

         WeatherCard(
            title = "降水预测",
            content = "2小时内无降雨",
            subContent = "放心出行吧",
            iconId = R.drawable.screenshot_20250228_163310
        )

         */


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
        shape = RoundedCornerShape(8.dp),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
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

            iconId?.let {
                Image(
                    painter = painterResource(id = iconId),
                    contentDescription = "Description of the PNG Image",
                    modifier = Modifier
                        .size(130.dp)
                        .clip(RoundedCornerShape(35.dp))
                )
            }
        }
    }
}

@Composable
fun LifeIndexSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            LifeIndexRow(
                items = listOf(
                    LifeIndexItem(MyIconPack.Clothes, "适宜厚外套"),
                    LifeIndexItem(MyIconPack.Car, "不易洗车")
                )
            )
            LifeIndexRow(
                items = listOf(
                    LifeIndexItem(MyIconPack.Cosmetic, "注意防晒"),
                    LifeIndexItem(MyIconPack.Umbrella, "不用带伞")
                )
            )
            LifeIndexRow(
                items = listOf(
                    LifeIndexItem(MyIconPack.Sport, "宜室内运动"),
                    LifeIndexItem(MyIconPack.Drugs, "易感冒")
                )
            )
        }
    }
}

@Composable
fun LifeIndexRow(items: List<LifeIndexItem>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items.forEach { item ->
            LifeIndexItemView(item)
        }
    }
}

@Composable
fun LifeIndexItemView(item: LifeIndexItem) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = ""
        )
        Text(
            text = item.text,
            style = TextStyle(
                fontSize = 20.sp
            )
        )
    }
}

data class LifeIndexItem(val icon: ImageVector, val text: String)

@Preview(showBackground = true)
@Composable
fun GreetingPreview11() {
    SunnyWeatherTheme {
        Weather_other_info(Modifier)
    }
}