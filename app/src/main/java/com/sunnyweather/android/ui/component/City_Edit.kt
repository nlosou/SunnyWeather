package com.sunnyweather.android.ui.component

import android.graphics.Color
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sunnyweather.android.log
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.myiconpack.CheckCircle
import com.sunnyweather.android.ui.place.PlaceViewModel
import com.sunnyweather.android.ui.weather.WeatherViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun City_Edit(navigation:NavController,PlaceViewModel: PlaceViewModel, WeatherViewModel: WeatherViewModel,index:Int) {
        var place_list=PlaceViewModel.getSavedPlace()
    var start=0.dp
    var end=0.dp
    var  top=0.dp
    var bottom=0.dp
    // 用于控制浮动效果的缩放比例
    /*

     */
    var scale by remember { mutableStateOf(1f) }
    Box(modifier = Modifier.combinedClickable(
        // 去除点击效果

        onLongClick = {
            PlaceViewModel.show_edit.targetState=true
        },
        onClick = {
                // 点击时触发浮动效果
                scale = 0.9f
                // 延迟一段时间后恢复原状
                CoroutineScope(Dispatchers.Main).launch {
                    delay(200)
                    scale = 1f
                }
            "onClick".log(index.toString())
            PlaceViewModel.place_current.value=index
            navigation.navigate("greeting"){
                popUpTo("greeting"){inclusive=true}
            }
        },
        indication = null,
        interactionSource = remember {
            MutableInteractionSource()
        },
    ))
    {
        Card(modifier = Modifier.fillMaxWidth().graphicsLayer {
            // 应用缩放效果
            this.scaleX = scale
            this.scaleY = scale
        },

            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(0.dp) // 移除卡片阴影
        ){
            if(PlaceViewModel.show_edit.targetState)
            {
                start = 5.dp
                end=5.dp
                top=30.dp
                bottom=30.dp
            }else{
                start = 15.dp
                end=15.dp
                top=30.dp
                bottom=30.dp
            }
            Row(verticalAlignment=Alignment.CenterVertically,
                modifier = Modifier.padding(
                    start = start, end = end, top = top, bottom = bottom
                ).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween){
                // 使用 AnimatedVisibility 实现淡入效果
                AnimatedVisibility(
                    visible = PlaceViewModel.show_edit.targetState,
                    enter = fadeIn(animationSpec = tween(durationMillis = 400)), // 进入时淡入
                    exit = fadeOut(animationSpec = tween(durationMillis = 400)) // 退出时淡出
                ){
                        IconButton(
                            onClick = {

                            }
                        ) {
                            Icon(Icons.Filled.Menu, contentDescription = "")
                        }
                }
                Box(
                ) {
                    Row(verticalAlignment=Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween // 设置水平排列方式为两端对齐
                    ){
                        Column() {

                                Column{
                                    Text(place_list[index].formatted_address,
                                        style = TextStyle(
                                            fontSize = 20.sp
                                        )
                                    )
                                    Row(verticalAlignment=Alignment.CenterVertically) {
                                        Text("天气", style = TextStyle(
                                            fontSize = 15.sp
                                        )
                                        )
                                        Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                                        Text("11/12",style = TextStyle(
                                            fontSize = 16.sp
                                        ))
                                    }
                                }
                        }
                        Row (verticalAlignment=Alignment.CenterVertically){
                            Text("10",
                                style = TextStyle(
                                    fontSize = 50.sp,
                                )
                            )
                            AnimatedVisibility(
                                visible = PlaceViewModel.show_edit.targetState,
                                enter = fadeIn(animationSpec = tween(durationMillis = 400)), // 进入时淡入
                                exit = fadeOut(animationSpec = tween(durationMillis = 400)) // 退出时淡出
                            ){
                                IconButton(
                                    onClick = {

                                    }
                                ) {
                                    Icon(MyIconPack.CheckCircle, contentDescription = "")
                                }
                            }
                        }

                    }
                }
            }


        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview16() {
    SunnyWeatherTheme {
        val a= remember { PlaceViewModel() }
        val b= remember { WeatherViewModel() }
    }
}