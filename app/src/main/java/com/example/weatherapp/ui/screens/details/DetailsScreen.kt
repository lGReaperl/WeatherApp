package com.example.weatherapp.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
        val (topText,listDetails,buttonDetails,column) = createRefs()

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
fun ButtonDetails(modifier: Modifier, navController: NavController){
    Button(
        colors = ButtonColors(
            containerColor = Color.Red,
            contentColor = Color.White,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth(),
        onClick = {
            navController.navigate("weather")
        }
    ) { }
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
        horizontalAlignment = Alignment.CenterHorizontally,
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