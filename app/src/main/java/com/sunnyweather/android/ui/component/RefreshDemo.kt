package com.sunnyweather.android.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 主界面实现
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefreshDemo() {
    val items = remember { (1..5).map { "Item $it" } }
    var isRefreshing by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val pullToRefreshState = rememberPullToRefreshState()

    Column {
        Box(Modifier.fillMaxSize()) {
            Box {
                // 刷新指示器
                PullToRefreshContainer(
                    state = pullToRefreshState,
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            }
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(pullToRefreshState.nestedScrollConnection)
            ) {
                items(items) { item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }



            // 刷新逻辑控制
            LaunchedEffect(pullToRefreshState.isRefreshing) {
                if (pullToRefreshState.isRefreshing) {
                    scope.launch {
                        // 模拟网络请求
                        delay(2000)
                        isRefreshing = false
                        pullToRefreshState.endRefresh()
                        lazyListState.animateScrollToItem(0) // 刷新后滚动到顶部
                    }
                }
            }
        }

    }
    // 下拉刷新容器

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    SunnyWeatherTheme {
        RefreshDemo()
    }
}