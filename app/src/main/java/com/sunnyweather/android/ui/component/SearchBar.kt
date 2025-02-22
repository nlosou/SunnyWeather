package com.sunnyweather.android.ui.component


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

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.sunnyweather.android.log
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.place.PlaceViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@Composable
fun SearchBar(mainViewModel: PlaceViewModel) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                scope.launch {
                    mainViewModel.placeFlow.collect { result ->
                        when (result) {

                        }
                    }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    //var text by remember { mutableStateOf("") }
    var text=mainViewModel.text.value
    Box (modifier=Modifier.fillMaxSize(),
        contentAlignment=Alignment.Center
        ){

        BasicTextField(
            value = mainViewModel.text.value,
            onValueChange = {
                mainViewModel.setText(it)
                "onValueChange".log(text)
                mainViewModel.searchPlaces(it)
                mainViewModel._placeList.clear()

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