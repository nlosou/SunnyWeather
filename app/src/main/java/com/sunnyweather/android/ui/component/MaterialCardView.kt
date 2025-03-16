package com.sunnyweather.android.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunnyweather.android.log
import com.sunnyweather.android.ui.place.PlaceViewModel

import com.sunnyweather.android.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.weather.WeatherViewModel


@Composable
fun Surface_Card(item:Int,placeViewModel: PlaceViewModel,WeatherViewModel:WeatherViewModel) {
    var place=placeViewModel._placeList[item]
    val weatherState by WeatherViewModel.state.collectAsState()
    Surface(shape = RoundedCornerShape(8.dp),
            shadowElevation = 10.dp,
            modifier = Modifier.fillMaxWidth().height(100.dp).padding(horizontal = 15.dp, vertical = 7.dp),
            ){
                Column {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween, // 水平两端对齐
                        verticalAlignment = Alignment.CenterVertically // 垂直居中
                    ){
                        Box(){
                            Text(place.formatted_address)
                        }
                        Box(){
                            IconButton(onClick = {
                                "Surface_Card".log(place.lat.toString()+place.lng.toString())
                                WeatherViewModel.SeacherWeather(
                                    place.lng.toString(),
                                    place.lat.toString()
                                )
                                weatherState.locationLat=place.lat.toString()
                                weatherState.locationLng=place.lng.toString()
                                placeViewModel.place_name.value=place.formatted_address
                                placeViewModel.savePlace(placeViewModel._placeList[item])
                                placeViewModel.place_num.value++
                            }) {
                                Icon(Icons.Filled.AddCircle, contentDescription = "", modifier = Modifier.size(100.dp))
                            }
                        }
                    }
                }
        }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    var placeViewModel= remember { PlaceViewModel() }
    var WeatherViewModel= remember { WeatherViewModel() }
    SunnyWeatherTheme {
        Surface_Card(0,placeViewModel,WeatherViewModel)
    }
}