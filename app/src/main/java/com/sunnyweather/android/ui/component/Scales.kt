package com.sunnyweather.android.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunnyweather.android.ui.theme.SunnyWeatherTheme
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalTextApi::class)
@Composable
fun SpeederMeter(speedNum: Int,modifier: Modifier = Modifier
    .width(220.dp)
    .height(120.dp)
    .background(color = Color.White)){
    val centerPointColor = Color.Blue
    val colorCenterPoint1 = Color(0xFF000000)
    val colorCenterPoint2 = Color(0xFFEE3E07)
    val mersure = rememberTextMeasurer()
    fun getPointX(centerX:Float,angle:Int,circleRdius:Float,step:Int):Float{
        val angles = (angle*step).toDouble()
        val angle = Math.toRadians(angles)
        return centerX - cos(angle).toFloat()*(circleRdius)
    }

    fun getPointY(centerY:Float,angle:Int,circleRdius:Float,step:Int):Float{
        val angles = (angle*step).toDouble()
        val angle = Math.toRadians(angles)
        return centerY- sin(angle).toFloat()*(circleRdius)
    }
    val myUnit = "KM/H"
    val speedTextList = Array(7){(it*20).toString()}
    Box(modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Canvas(modifier, onDraw = {
            val centerX = size.width/2
            val bottomY = size.height-50
            val myColorStops = arrayOf(0.0f to Color.White,1.0f to Color.Black)
            //画指针底座
            drawCircle(
                brush = Brush.radialGradient(colorStops = myColorStops),
                radius = 30f,
                center = Offset(centerX,bottomY+15.dp.value),
            )
            drawCircle(
                color = Color.White,
                radius = 23f,
                center = Offset(centerX,bottomY+15.dp.value)
            )
            drawCircle(
                color = Color.Red,
                radius = 20f,
                center = Offset(centerX,bottomY+15.dp.value)
            )
            //画单位
            drawText(
                textMeasurer = mersure,
                text = myUnit,
                style = TextStyle(color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight(20), fontFamily = FontFamily.Serif),
                topLeft = Offset(centerX-50.dp.value,bottomY-90)
            )
            //画刻度
            repeat(61){
                val fontWeights = if( it%5==0){
                    if(it%10==0){
                        7f
                    }else{
                        4f
                    }
                }else{
                    2f
                }
                val lengths = if(it%5==0){
                    if(it%10==0){
                        25
                    }else{
                        22
                    }
                }else{
                    20
                }
                drawLine(
                    color = Color.Black,
                    start = Offset(
                        getPointX(centerX,it,bottomY,3),
                        getPointY(centerX,it,bottomY,3)
                    ),
                    end = Offset(
                        getPointX(centerX,it,bottomY-lengths,3),
                        getPointY(centerX,it,bottomY-lengths,3)
                    ),
                    strokeWidth = fontWeights,
                    cap = StrokeCap.Round
                )
            }
            //画数字
            repeat(speedTextList.size){
                val myTopLeft = Offset(
                    getPointX(centerX,it,bottomY-70,30)-25.dp.value,
                    getPointY(bottomY,it,bottomY-70,30))
                val colorType = if(it > 4){
                    Color.Red
                }else{
                    Color.Black
                }
                drawText(
                    textMeasurer = mersure,
                    text = speedTextList[it],
                    topLeft =myTopLeft,
                    style = TextStyle(colorType, fontSize = 11.sp, fontFamily = FontFamily.Serif)
                )
            }
            //画指针
            drawLine(
                color = Color.Red,
                start = Offset(
                    getPointX(centerX,speedNum,bottomY-30,1),
                    getPointY(centerX,speedNum,bottomY-30,1)
                ),
                end = Offset(
                    getPointX(centerX,speedNum,0f,1),
                    getPointY(centerX,speedNum,0f,1)
                ),
                strokeWidth = 15f,
                cap = StrokeCap.Round

            )
        })


    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpeederMeter(100,Modifier)
}