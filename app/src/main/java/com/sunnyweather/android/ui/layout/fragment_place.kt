package com.sunnyweather.android.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.ui.component.SearchBar
import com.sunnyweather.android.ui.component.Surface_Card
import com.sunnyweather.android.ui.place.PlaceViewModel
import com.sunnyweather.android.ui.theme.SunnyWeatherTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting2(mainViewModel: PlaceViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(10.dp),
                title = {
                        SearchBar(mainViewModel)
                },
                actions = {
                    Button(
                        onClick = { /* 按钮点击事件 */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0x0000000),   // 设置按钮背景颜色为白色
                            contentColor = Color(0xFF0096FF)       // 设置按钮文本颜色为黑色
                        )
                    ) {
                        Text("取消")
                    }

                }
            )
        },



    ) { contentPadding ->
        // 主内容区域
        if(mainViewModel.text.value.isNotEmpty())
        {
            LazyColumn(modifier=Modifier.padding(contentPadding),
                horizontalAlignment = Alignment.CenterHorizontally // 设置所有子项水平居中
            ) {

                items(mainViewModel._placeList.size) { item ->

                    Surface_Card(mainViewModel._placeList[item].name)
                }
            }
        }else{
            HotCityAndCurrentCity(Modifier.padding(contentPadding))
        }
        }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    val mainViewModel = remember { PlaceViewModel() }
    SunnyWeatherTheme {
        Greeting2(mainViewModel)
    }
}