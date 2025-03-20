package com.sunnyweather.android.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.myiconpack.Clothes


@Composable
fun LifeIndexSituation(name: String, modifier: Modifier = Modifier) {
    BoxWithConstraints(contentAlignment = Alignment.Center,)
    {
        // 获取父容器约束
        val parentWidth = maxWidth
        val parentHeight = maxHeight
        Column(modifier=Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
                ) {
                Text("霾转晴", color = Color.LightGray)
                Spacer(Modifier.padding(5.dp))
                Text("16/5",fontSize=(parentWidth.value*0.04f).sp, color = Color.LightGray)
            }
            Spacer(modifier = Modifier.padding(parentWidth*0.01f))
            Text("适宜厚外套",fontSize=(parentWidth.value*0.1f).sp)
            Text("天气较冷，注意保暖")
            Spacer(modifier = Modifier.padding(parentWidth*0.1f))
            LazyRow (horizontalArrangement = Arrangement.spacedBy(parentWidth*0.2f)){
                items(3){
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                        Text("明天")
                        Text("16/5",fontSize=(parentWidth.value*0.04f).sp, color = Color.LightGray)
                        Spacer(modifier = Modifier.padding(10.dp))
                        Icon(
                            imageVector = MyIconPack.Clothes,
                            contentDescription = "",
                            modifier = Modifier
                                .size(45.dp)
                        )
                        Text("适宜短袖")
                    }
                }
            }
            Spacer(modifier = Modifier.padding(20.dp))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview24() {
    SunnyWeatherTheme {
        LifeIndexSituation("Android")
    }
}