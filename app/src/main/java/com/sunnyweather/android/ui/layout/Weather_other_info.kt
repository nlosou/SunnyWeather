package com.sunnyweather.android.ui.layout


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.layout.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.myiconpack.Car
import com.sunnyweather.android.ui.myiconpack.Clothes
import com.sunnyweather.android.ui.myiconpack.Cosmetic
import com.sunnyweather.android.ui.myiconpack.Drugs
import com.sunnyweather.android.ui.myiconpack.Sport
import com.sunnyweather.android.ui.myiconpack.Umbrella


@Composable
fun Weather_other_info(name: String, modifier: Modifier = Modifier) {
    ConstraintLayout(modifier=Modifier.fillMaxSize()) {
        val (Rainfall_forecast,Ultraviolet,humidity,somatosensory,Wind,sun,atmospheric_pressure,life) = remember { createRefs() }
        Card(modifier= Modifier
            .constrainAs(Rainfall_forecast) {
                top.linkTo(parent.top)
            }
            .padding(10.dp)) {
            Row(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Column {
                    Text("降水预测",style = TextStyle(
                        fontSize = 15.sp,
                    ))
                    Text("2小时内无降雨",style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold  // 添加加粗效果
                    ))
                    Spacer(modifier=Modifier.padding(25.dp))
                    Text("放心出行吧",style = TextStyle(
                    fontSize = 15.sp,))
                }
                Image(
                    painter = painterResource(id = R.drawable.screenshot_20250228_163310),  // 替换为你的 PNG 图片资源ID
                    contentDescription = "Description of the PNG Image",
                    modifier = Modifier
                        .size(130.dp)
                        .clip(RoundedCornerShape(35.dp))
                        .align(Alignment.CenterVertically)// 设置图片大
                )

            }
        }

        Card(modifier = Modifier.constrainAs(Ultraviolet) {
            top.linkTo(Rainfall_forecast.bottom)
        }.fillMaxWidth(0.5f).padding(10.dp, top = 1.dp)) {
            Row(modifier = Modifier.padding(20.dp)) {
                Column {
                    Text(
                        "紫外线", style = TextStyle(
                            fontSize = 15.sp,
                        )
                    )
                    Text(
                        "中", style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold  // 添加加粗效果
                        )
                    )
                }
            }
        }
        Card(modifier = Modifier.constrainAs(humidity) {
            top.linkTo(Rainfall_forecast.bottom)
            start.linkTo(Ultraviolet.end)
        }.fillMaxWidth(0.5f).padding(10.dp, top = 1.dp, end = 10.dp)) {
            Row(modifier = Modifier.padding(20.dp)) {
                Column {
                    Text(
                        "湿度", style = TextStyle(
                            fontSize = 15.sp,
                        )
                    )
                    Spacer(modifier = Modifier.padding(3.4.dp))
                    Text(
                        "42%", style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold  // 添加加粗效果
                        )
                    )
                }
            }
        }
        Card(modifier = Modifier.constrainAs(somatosensory) {
            top.linkTo(Ultraviolet.bottom)
        }.fillMaxWidth(0.5f).padding(10.dp, top = 10.dp)) {
            Row(modifier = Modifier.padding(20.dp)) {
                Column {
                    Text(
                        "体感", style = TextStyle(
                            fontSize = 15.sp,
                        )
                    )
                    Spacer(modifier = Modifier.padding(3.4.dp))
                    Text(
                        "15°", style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold  // 添加加粗效果
                        )
                    )
                }
            }
        }
        Card(modifier = Modifier.constrainAs(Wind) {
            top.linkTo(humidity.bottom)
            start.linkTo(Ultraviolet.end)
        }.fillMaxWidth(0.5f).padding(10.dp, top = 10.dp,end = 10.dp)) {
            Row(modifier = Modifier.padding(20.dp)) {
                Column {
                    Text(
                        "西南风", style = TextStyle(
                            fontSize = 15.sp,
                        )
                    )
                    Spacer(modifier = Modifier.padding(3.4.dp))
                    Text(
                        "2级", style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold  // 添加加粗效果
                        )
                    )
                }
            }
        }
        Card(modifier = Modifier.constrainAs(sun) {
            top.linkTo(somatosensory.bottom)
        }.fillMaxWidth(0.5f).padding(10.dp, top = 10.dp)) {
            Row(modifier = Modifier.padding(20.dp)) {
                Column {
                    Text(
                        "体感", style = TextStyle(
                            fontSize = 15.sp,
                        )
                    )
                    Spacer(modifier = Modifier.padding(3.4.dp))
                    Text(
                        "15°", style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold  // 添加加粗效果
                        )
                    )
                }
            }
        }
        Card(modifier = Modifier.constrainAs(atmospheric_pressure) {
            top.linkTo(Wind.bottom)
            start.linkTo(Ultraviolet.end)
        }.fillMaxWidth(0.5f).padding(10.dp, top = 10.dp,end = 10.dp)) {
            Row(modifier = Modifier.padding(20.dp)) {
                Column {
                    Text(
                        "西南风", style = TextStyle(
                            fontSize = 15.sp,
                        )
                    )
                    Spacer(modifier = Modifier.padding(3.4.dp))
                    Text(
                        "2级", style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold  // 添加加粗效果
                        )
                    )
                }
            }
        }
        Card(
            modifier = Modifier
                .constrainAs(life) {
                    top.linkTo(atmospheric_pressure.bottom)
                    start.linkTo(parent.start)  // 链接到父布局左侧
                    end.linkTo(parent.end)      // 链接到父布局右侧
                    bottom.linkTo(parent.bottom)// 链接到父布局底部
                    width = Dimension.fillToConstraints // 宽度适配
                    height = Dimension.fillToConstraints // 高度适配
                }
                .fillMaxSize(1f) // 确保填充所有可用空间
                .padding(10.dp)
        ) {
            // 内容部分
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly  // 均匀分配空间
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)  // 每个 Column 占据相等的权重
                            .padding(10.dp)
                    ) {
                        Icon(MyIconPack.Clothes, contentDescription = "")
                        Text("适宜厚外套", style = TextStyle(fontSize = 20.sp))
                        Icon(MyIconPack.Car, contentDescription = "")
                        Text("不易洗车", style = TextStyle(fontSize = 20.sp))
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)  // 每个 Column 占据相等的权重
                            .padding(10.dp)
                    ) {
                        Icon(MyIconPack.Cosmetic, contentDescription = "")
                        Text("注意防晒", style = TextStyle(fontSize = 20.sp))
                        Icon(MyIconPack.Umbrella, contentDescription = "")
                        Text("不用带伞", style = TextStyle(fontSize = 20.sp))
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)  // 每个 Column 占据相等的权重
                            .padding(10.dp)
                    ) {
                        Icon(MyIconPack.Sport, contentDescription = "")
                        Text("宜室内运动", style = TextStyle(fontSize = 20.sp))
                        Icon(MyIconPack.Drugs, contentDescription = "")
                        Text("易感冒", style = TextStyle(fontSize = 20.sp))
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview11() {
    SunnyWeatherTheme {
        Weather_other_info("Android")
    }
}