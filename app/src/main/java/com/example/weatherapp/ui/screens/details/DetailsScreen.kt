package com.example.weatherapp.ui.screens.details

import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import com.example.weatherapp.R

@Composable
fun DetailsScreenView(navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF45278B), Color(0xFF2E335A))
                )
            ),
    ) {
        val (topText,listDetails,buttonDetails,airQuality,sunStatus,uvIndex) = createRefs()

        TopTextDetailsFragment(
            modifier = Modifier
                .constrainAs(topText) {
                    top.linkTo(parent.top, 46.dp)
                    centerHorizontallyTo(parent)
                }
        )

        WeatherListDetailsFragment(
            modifier = Modifier
                .constrainAs(listDetails){
                    top.linkTo(topText.bottom, 52.dp)
                }
        )

        AirQuality(
            modifier = Modifier
                .constrainAs(airQuality){
                    top.linkTo(listDetails.bottom, 35.dp)
                    start.linkTo(parent.start, 40.dp)
                    end.linkTo(parent.end, 40.dp)
                }
        )

        SunStatus(
            modifier = Modifier
                .constrainAs(sunStatus){
                    top.linkTo(airQuality.bottom, 43.dp)
                    start.linkTo(airQuality.start)
                }
        )

        UVIndex(
            modifier = Modifier
                .constrainAs(uvIndex){
                    start.linkTo(sunStatus.end, 13.dp)
                    top.linkTo(sunStatus.top)
                }
        )

        ButtonDetails(
            modifier = Modifier
                .constrainAs(buttonDetails){
                    bottom.linkTo(parent.bottom)
                },
            navController = navController
        )
    }
}

@Composable
fun UVIndex(modifier: Modifier) {
    Box(
        modifier = modifier
            .border(width = 1.dp, shape = RoundedCornerShape(20.dp), color = Color.White)
            .background(
                shape = RoundedCornerShape(20.dp),
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF3E2D8F), // Первый цвет
                        Color(0xFF9D52AC)
                    ), // Цвета градиента
                    start = Offset(0f, 0f), // Начальная точка
                    end = Offset(0f, 172f) // Конечная точка
                )
            )
            .size(width = 161.dp, height = 150.dp),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            val (sunImg, sunriseTxt, timeTxt) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(sunImg){
                        top.linkTo(parent.top, 14.dp)
                        start.linkTo(parent.start, 14.dp)
                    },
            )

            Text(
                text = "UV INDEX",
                color = Color.White,
                modifier = Modifier
                    .constrainAs(sunriseTxt){
                        top.linkTo(sunImg.top)
                        bottom.linkTo(sunImg.bottom)
                        start.linkTo(sunImg.end, 5.dp)
                    },
                fontSize = 16.sp,
            )

            Text(
                text = "4\nModerate",
                color = Color.White,
                modifier = Modifier
                    .constrainAs(timeTxt){
                        start.linkTo(sunImg.start, 8.dp)
                        top.linkTo(sunImg.bottom, 10.dp)
                    },
                fontSize = 28.sp,
            )
        }
    }
}

@Composable
fun SunStatus(modifier: Modifier) {
    Box(
        modifier = modifier
            .border(width = 1.dp, shape = RoundedCornerShape(20.dp), color = Color.White)
            .background(
                shape = RoundedCornerShape(20.dp),
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF3E2D8F), // Первый цвет
                        Color(0xFF9D52AC)
                    ), // Цвета градиента
                    start = Offset(0f, 0f), // Начальная точка
                    end = Offset(0f, 172f) // Конечная точка
                )
            )
            .size(width = 161.dp, height = 150.dp),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            val (sunImg, sunriseTxt, timeTxt, sunsetTxt) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(sunImg){
                        top.linkTo(parent.top, 14.dp)
                        start.linkTo(parent.start, 14.dp)
                    },
            )

            Text(
                text = "SUNRISE",
                color = Color.White,
                modifier = Modifier
                    .constrainAs(sunriseTxt){
                        top.linkTo(sunImg.top)
                        bottom.linkTo(sunImg.bottom)
                        start.linkTo(sunImg.end, 5.dp)
                    },
                fontSize = 16.sp,
            )

            Text(
                text = "5:28 AM",
                color = Color.White,
                modifier = Modifier
                    .constrainAs(timeTxt){
                        start.linkTo(sunImg.start, 8.dp)
                        top.linkTo(sunImg.bottom, 10.dp)
                    },
                fontSize = 28.sp,
            )

            Text(
                text = "Sunset: 7.25PM",
                color = Color.White,
                modifier = Modifier
                    .constrainAs(sunsetTxt){
                        top.linkTo(timeTxt.bottom, 10.dp)
                        start.linkTo(timeTxt.start, (-3).dp)
                    },
                fontSize = 18.sp,
            )
        }
    }
}

@Composable
fun AirQuality(modifier: Modifier){
    Box(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(20.dp),
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF3E2D8F), // Первый цвет
                        Color(0xFF9D52AC)
                    ), // Цвета градиента
                    start = Offset(0f, 0f), // Начальная точка
                    end = Offset(0f, 172f) // Конечная точка
            ))
            .size(width = 352.dp, height = 174.dp),
    ){
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (crosshairsImg, qualityTxt, statusTxt, divider, moreTxt, arrowImg) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_crosshairs),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(crosshairsImg){
                        top.linkTo(parent.top, 26.dp)
                        start.linkTo(parent.start, 20.dp)
                    },
            )

            Text(
                modifier = Modifier
                    .constrainAs(qualityTxt){
                        start.linkTo(crosshairsImg.end, 12.dp)
                        top.linkTo(crosshairsImg.top)
                        bottom.linkTo(crosshairsImg.bottom)
                    },
                text = "AIR QUALITY",
                color = Color.White,
                fontSize = 16.sp,
            )

            Text(
                modifier = Modifier
                    .constrainAs(statusTxt){
                        start.linkTo(parent.start, 14.dp)
                        top.linkTo(crosshairsImg.bottom, 17.dp)
                    },
                text = "3-Low Health Risk",
                color = Color.White,
                fontSize = 28.sp,
            )

            Divider(
                modifier = Modifier
                    .constrainAs(divider){
                        top.linkTo(statusTxt.bottom, 20.dp)
                        end.linkTo(parent.end,24.dp)
                    },
                color = Color.White,
                thickness = 5.dp,
                startIndent = 44.dp,
            )

            Text(
                text = "See more",
                color = Color.White,
                modifier = Modifier
                    .constrainAs(moreTxt){
                        bottom.linkTo(parent.bottom, 19.dp)
                        start.linkTo(parent.start, 23.dp)
                    },
                fontSize = 18.sp,
            )

            Image(
                painter = painterResource(id = R.drawable.ic_chevron),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(arrowImg){
                        end.linkTo(parent.end, 47.dp)
                        bottom.linkTo(parent.bottom, 14.dp)
                    },
            )
        }
    }
}

@Composable
fun ButtonDetails(modifier: Modifier, navController: NavController){
    Button(
        colors = ButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth(),
        onClick = {
            navController.navigate("weather")
        },
    ){
        Image(painter = painterResource(id = R.drawable.ic_menu), contentDescription = null)
    }
}

@Composable
fun WeatherListDetailsFragment(modifier: Modifier){
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val (text,row) = createRefs()
        Text(
            modifier = Modifier
                .constrainAs(text){
                    start.linkTo(parent.start, 50.dp)
                },
            color = Color.White,
            fontSize = 24.sp,
            text = "7-Days Forecasts"
        )

        Row(
            modifier = Modifier
                .constrainAs(row){
                    centerHorizontallyTo(parent)
                    top.linkTo(text.bottom,14.dp)
                },
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            repeat(4) { // Повторяем 4 раза
                WeatherCardDetails("19°C", "15.00")
            }

        }
    }
}

@Composable
fun WeatherCardDetails(temp: String, time: String){
    Column(
        modifier = Modifier
            .background(shape = RoundedCornerShape(50.dp),
                brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF3E2D8F), // Первый цвет
                    Color(0xFF9D52AC)
                ), // Цвета градиента
                start = Offset(0f, 0f), // Начальная точка
                end = Offset(0f, 172f) // Конечная точка
            ))
            .size(width = 82.dp, height = 172.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = temp,
            fontSize = 20.sp,
            color = Color(0xFFFFFFFF)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_rain_cloud_sun),
            contentDescription = "Wether Icon",
            modifier = Modifier
                .size(66.dp)
        )
        Text(
            text = time,
            fontSize = 20.sp,
            color = Color(0xFFFFFFFF)
        )
    }
}

@Composable
fun TopTextDetailsFragment(modifier: Modifier){
    ConstraintLayout(modifier = modifier
        .fillMaxWidth())
    {
        val (text1,text2) = createRefs()
        Text(
            modifier = Modifier
                .constrainAs(text1){
                    centerHorizontallyTo(parent)
                },
            text = "NorthAmerica",
            fontSize = 24.sp,
            color = Color.White
        )
        Text(
            modifier = Modifier
                .constrainAs(text2){
                    top.linkTo(text1.bottom)
                    centerHorizontallyTo(parent)
                },
            text = "Max: 24°   Min:18°",
            fontSize = 24.sp,
            color = Color.White
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreenView() {
    val context = LocalContext.current
    val fakeNavController = TestNavHostController(context)
    DetailsScreenView(navController = fakeNavController)
}