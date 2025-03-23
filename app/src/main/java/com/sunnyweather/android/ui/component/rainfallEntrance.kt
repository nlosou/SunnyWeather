package com.sunnyweather.android.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sunnyweather.android.log
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.myiconpack.MapMarked
import com.sunnyweather.android.ui.place.PlaceViewModel
import com.sunnyweather.android.ui.weather.WeatherViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun rainfallEntrance(navController: NavController, mainViewModel: PlaceViewModel, WeatherViewModel: WeatherViewModel) {
    var scale by remember { mutableStateOf(1f) }
    val weatherState by WeatherViewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    BoxWithConstraints {
        val parentHeight=maxHeight
        val parentWight=maxWidth
        Card(
            modifier = Modifier
                .height(parentHeight).width(maxWidth)
                .scale(scale)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    scale = 0.9f
                    "clickable".log("start")

                    scope.launch {
                        delay(200)
                        scale = 1f
                    }
                    navController.navigate("BaiduMap")
                },
            shape = RoundedCornerShape(20.dp)
        ){
            Box(Modifier.padding(16.dp)) {
                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Column {
                        Text("降水预报")
                        Text("2小时内无降雨", fontSize = (parentWight*0.06f).value.sp)
                        Spacer(Modifier.padding((parentHeight*0.2f)))
                        Text("放心出门吧")
                    }
                    Icon(MyIconPack.MapMarked, contentDescription = "",modifier = Modifier.size(parentWight*0.5f).offset(x=parentWight*0.1f))
                }
            }
        }
    }
}
