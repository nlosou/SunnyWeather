import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun ExpandShrinkNavigationDemo() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable(
            "home",
            enterTransition = {
                scaleIn(animationSpec = tween(durationMillis = 300))
            },
            exitTransition = {
                scaleOut(animationSpec = tween(durationMillis = 300))
            },
            popEnterTransition = {
                scaleIn(animationSpec = tween(durationMillis = 300))
            },
            popExitTransition = {
                scaleOut(animationSpec = tween(durationMillis = 300))
            }
        ) {
            HomeScreen(navController)
        }

        composable(
            "details",
            enterTransition = {
                scaleIn(animationSpec = tween(durationMillis = 300))
            },
            exitTransition = {
                scaleOut(animationSpec = tween(durationMillis = 300))
            },
            popEnterTransition = {
                scaleIn(animationSpec = tween(durationMillis = 300))
            },
            popExitTransition = {
                scaleOut(animationSpec = tween(durationMillis = 300))
            }
        ) {
            DetailsScreen(navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Scaffold Example") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("details") }
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

@Composable
fun DetailsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "详情页")
        Button(onClick = { navController.navigateUp() }) {
            Text("返回主页")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun a()
{
    ExpandShrinkNavigationDemo()
}