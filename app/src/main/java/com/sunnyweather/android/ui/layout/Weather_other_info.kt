import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.FadingDivider
import com.sunnyweather.android.ui.component.Humidity_table
import com.sunnyweather.android.ui.component.Somatosensory
import com.sunnyweather.android.ui.component.Ultraviolet
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
            if(title=="湿度"){
                Box(modifier = Modifier.weight(1f)){
                    Humidity_table("")
                }
            }
            else if(title=="紫外线")
            {
                Box(modifier = Modifier.weight(1f)){
                    Ultraviolet("")
                }
            }
            else if(title=="体感")
            {
                Box(modifier=Modifier.weight(1f)){
                    Somatosensory("")
                }
            }


        }
    }
}

@Composable
fun LifeIndexSection() {
    Box {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(20.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                LifeIndexRow(
                    items = listOf(
                        LifeIndexItem(MyIconPack.Clothes, "适宜厚外套"),
                        LifeIndexItem(MyIconPack.Cosmetic, "注意防晒"),
                        LifeIndexItem(MyIconPack.Sport, "宜室内运动")
                    )
                )
                Divider(
                    color = Color.LightGray, // 设置分割线的颜色
                    thickness = 1.dp,
                    // 设置分割线的厚度
                )
                LifeIndexRow(
                    items = listOf(
                        LifeIndexItem(MyIconPack.Car, "不宜洗车"),
                        LifeIndexItem(MyIconPack.Umbrella, "不用带伞"),
                        LifeIndexItem(MyIconPack.Drugs, "易感冒")
                    )
                )
            }
        }
        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .height(300.dp)      // 垂直方向填满高度
                .width(1.dp)      // 设置垂直分割线的宽度（即厚度）
                .offset(x=135.dp,y=5.dp)
        )
        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .height(300.dp)      // 垂直方向填满高度
                .width(1.dp)      // 设置垂直分割线的宽度（即厚度）
                .offset(x=240.dp,y=5.dp)
        )

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
        items.forEachIndexed { index, item ->
            LifeIndexItemView(item)
            // 如果不是最后一个元素，则添加分割线

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
            contentDescription = "",
            modifier = Modifier.size(item.iconSize)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = item.text,
            style = TextStyle(
                fontSize = 15.sp
            )
        )
    }
}

data class LifeIndexItem(
    val icon: ImageVector,
    val text: String,
    val iconSize: Dp = 45.dp // 默认图标大小
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview11() {
    SunnyWeatherTheme {
        Weather_other_info(Modifier)
    }
}