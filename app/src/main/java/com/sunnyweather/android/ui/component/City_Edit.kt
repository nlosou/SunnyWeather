package com.sunnyweather.android.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme


@Composable
fun City_Edit(name: String, modifier: Modifier = Modifier) {
    Card {
        Box {
            Row{
                Column {
                    Text("大连市")
                    Row {
                        Text("天气")
                        Text("11/12")
                    }
                }
                Text("10")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview16() {
    SunnyWeatherTheme {
        City_Edit("Android")
    }
}