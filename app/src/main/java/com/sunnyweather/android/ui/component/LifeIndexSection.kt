package com.sunnyweather.android.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.log
import com.sunnyweather.android.ui.MyIconPack
import com.sunnyweather.android.ui.component.ui.theme.SunnyWeatherTheme
import com.sunnyweather.android.ui.myiconpack.Car
import com.sunnyweather.android.ui.myiconpack.Clothes
import com.sunnyweather.android.ui.myiconpack.Cosmetic
import com.sunnyweather.android.ui.myiconpack.Drugs
import com.sunnyweather.android.ui.myiconpack.Sport
import com.sunnyweather.android.ui.myiconpack.Umbrella
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LifeIndexSection() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            // 底部工作表内容
            Text("生活指数详情", modifier = Modifier.padding(16.dp))
        }
    }

    // 显示生活指数卡片
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            LifeIndexRow(
                items = listOf(
                    LifeIndexItem(MyIconPack.Clothes, "适宜厚外套"),
                    LifeIndexItem(MyIconPack.Cosmetic, "注意防晒"),
                    LifeIndexItem(MyIconPack.Sport, "宜室内运动")
                ),
                scope,
                showBottomSheet = { show -> showBottomSheet = show }
            )
            LifeIndexRow(
                items = listOf(
                    LifeIndexItem(MyIconPack.Car, "不宜洗车"),
                    LifeIndexItem(MyIconPack.Umbrella, "不用带伞"),
                    LifeIndexItem(MyIconPack.Drugs, "易感冒")
                ),
                scope,
                showBottomSheet = { show -> showBottomSheet = show }
            )
        }
    }
}

@Composable
fun LifeIndexRow(
    items: List<LifeIndexItem>,
    scope:CoroutineScope,
    showBottomSheet: (Boolean) -> Unit

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items.forEachIndexed { index, item ->
            LifeIndexItemView(item, scope,showBottomSheet,)
            if (index < items.lastIndex) {
                Spacer(modifier = Modifier.size(16.dp)) // 添加间隔
            }
        }
    }
}

@Composable
fun LifeIndexItemView(
    item: LifeIndexItem,
    scope:CoroutineScope,
    showBottomSheet: (Boolean) -> Unit

) {
    var scale by remember { mutableStateOf(1f) }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                scale = 0.9f
                "clickable".log("start")
                showBottomSheet(true)
                scope.launch {
                    delay(200)
                    scale = 1f
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.text,
            modifier = Modifier
                .size(item.iconSize)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = item.text,
            style = TextStyle(fontSize = 15.sp)
        )
    }
}

data class LifeIndexItem(
    val icon: ImageVector,
    val text: String,
    val iconSize: Dp = 45.dp // 默认图标大小
)


@Preview(showBackground = true)
@Composable
fun GreetingPreview23() {
    SunnyWeatherTheme {
        LifeIndexSection()
    }
}