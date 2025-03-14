package com.sunnyweather.android.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.place.PlaceViewModel
import com.sunnyweather.android.ui.weather.WeatherViewModel


@Composable
fun City_Edit(PlaceViewModel: PlaceViewModel, WeatherViewModel: WeatherViewModel,index:Int) {
        var place_list=PlaceViewModel.getSavedPlace()
        Card(modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
            ){
            Box(modifier = Modifier.padding(horizontal = 15.dp, vertical = 30.dp).fillMaxWidth()) {
                Row(verticalAlignment=Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween // 设置水平排列方式为两端对齐
                    ){
                    Column() {
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
                    Text("10",
                        style = TextStyle(
                            fontSize = 50.sp,
                        )
                    )
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
        Box (modifier = Modifier.fillMaxSize()){
            City_Edit(a,b,66)
        }
    }
}