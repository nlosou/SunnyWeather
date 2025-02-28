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
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.layout.ui.theme.SunnyWeatherTheme


@Composable
fun Weather_other_info(name: String, modifier: Modifier = Modifier) {
    ConstraintLayout(modifier=Modifier.fillMaxSize()) {
        val (Rainfall_forecast,Ultraviolet,humidity,somatosensory,Wind,sun,atmospheric_pressure) = remember { createRefs() }
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
        }.fillMaxWidth(0.5f).padding(10.dp, top = 3.dp)) {
            Row(modifier = Modifier.padding(10.dp)) {
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
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview11() {
    SunnyWeatherTheme {
        Weather_other_info("Android")
    }
}