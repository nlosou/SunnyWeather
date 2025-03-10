package com.sunnyweather.android.ui.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sunnyweather.android.ui.layout.ui.theme.SunnyWeatherTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Place_manage(name: String, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            IconButton(onClick = {

            }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Do something */ }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
        bottomBar = {
            BottomAppBar {
                IconButton(onClick = { /* Do something */ }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
                }
            }
        }
    ) { contentPadding ->
        // 主内容区域
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Text("Main content area", modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview10() {
    SunnyWeatherTheme {
        Place_manage("Android")
    }
}