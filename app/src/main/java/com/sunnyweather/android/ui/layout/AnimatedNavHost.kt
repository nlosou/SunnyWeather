import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "主页")
        Button(onClick = { navController.navigate("details") }) {
            Text("跳转到详情页")
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