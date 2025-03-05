package com.sunnyweather.android.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.weather.WeatherViewModel

@Composable
fun SyncedLazyColumns() {
    val scrollState1 = rememberLazyListState()
    val scrollState2 = rememberLazyListState()

    // 同步第一个 LazyRow 的滚动到第二个
    LaunchedEffect(scrollState1.isScrollInProgress) {
        snapshotFlow { scrollState1.firstVisibleItemScrollOffset }.collect { offset ->
            if (!scrollState2.isScrollInProgress) {
                scrollState2.scrollToItem(scrollState1.firstVisibleItemIndex, offset)
            }
        }
    }

    // 同步第二个 LazyRow 的滚动到第一个
    LaunchedEffect(scrollState2.isScrollInProgress) {
        snapshotFlow { scrollState2.firstVisibleItemScrollOffset }.collect { offset ->
            if (!scrollState1.isScrollInProgress) {
                scrollState1.scrollToItem(scrollState2.firstVisibleItemIndex, offset)
            }
        }
    }

    Column {
        LazyRow(state = scrollState1) {
            items(100) { Text("Item $it") }
        }
        LazyRow(state = scrollState2) {
            items(100) { Text("Item $it") }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview10() {
    SyncedLazyColumns()
}