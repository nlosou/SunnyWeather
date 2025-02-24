package com.sunnyweather.android.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.myiconpack.Alarm


@Composable
fun Alarm_Info(modifier: Modifier) {
    Card (modifier){
        Column (verticalArrangement = Arrangement.Center) {
            // 使用 weight(1f) 使 Column 占满父布局剩余空间，从而将 Icon 挤到右边
            Row (modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text("大风蓝色预警")
                    Text("更新时间")
                }
                Icon(
                    MyIconPack.Alarm,
                    contentDescription = "",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Text("预警详细信息:dhsdhdhdhdhhdhdhdhdh")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    SunnyWeatherTheme {
        Alarm_Info(Modifier)
    }
}