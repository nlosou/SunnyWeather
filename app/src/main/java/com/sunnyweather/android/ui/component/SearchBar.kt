package com.sunnyweather.android.ui.component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.place.PlaceViewModel


@Composable
fun SearchBar(mainViewModel: PlaceViewModel) {

    //var text by remember { mutableStateOf("") }
    var text=mainViewModel.text.value
    Box (modifier=Modifier.fillMaxSize(),
        contentAlignment=Alignment.Center
        ){

        BasicTextField(
            value = mainViewModel.text.value,
            onValueChange = {
                mainViewModel.setText(it)
            },
            decorationBox = {
                innerTextField ->
                Row (verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 8.dp)
                    ){
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                    Box(modifier=Modifier.padding(horizontal = 0.dp),
                        contentAlignment=Alignment.CenterStart){
                        if(text.isEmpty()){
                            Text(text="搜索位置",
                                style = TextStyle(
                                    color = Color(0,0,0,128)
                                )
                            )
                        }
                        innerTextField()
                    }
                    if(text.isNotEmpty())
                    {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ){
                            IconButton(onClick = {
                                mainViewModel.setText("")
                                text=""
                            },
                                modifier=Modifier.size(16.dp),
                            ) {
                                Icon(imageVector = Icons.Filled.Close, contentDescription = "")
                            }
                        }

                    }

                }


            },
            modifier = Modifier.padding(horizontal = 10.dp)
                .background(Color.LightGray, CircleShape)
                .height(50.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    SunnyWeatherTheme {
        val mainViewModel = remember { PlaceViewModel() }
        SearchBar(mainViewModel)
    }
}