package com.example.weatherapp.useri.screens.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import com.example.weatherapp.R

@Composable
fun WeatherScreenView(navController: NavController) {

    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF45278B), Color(0xFF2E335A))
                )
            ),
    ) {
        val (topRef, botRef, botNavRef) = createRefs()
        TopFragment(
            modifier = Modifier.constrainAs(topRef){
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
        )
        BotFragment(
            modifier = Modifier.constrainAs(botRef){
                start.linkTo(parent.start)
                bottom.linkTo(botNavRef.top)
            }
        )

        CustomBottomNavigation(
            modifier = Modifier.constrainAs(botNavRef){
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
        )
    }
}

@Composable
fun CustomBottomNavigation(modifier: Modifier) {
    // Состояние для текущей выбранной вкладки
    val selectedItem = remember { mutableStateOf(0) }

    // Список вкладок (иконки и названия)
    val items = listOf("Home", "Search", "Profile")

    // Контейнер для Bottom Navigation
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        contentColor = Color.White,
        elevation = 0.dp
    ) {
        items.forEachIndexed { index, label ->
            BottomNavigationItem(
                icon = {
                    // Иконка для вкладки
                    Icon(
                        imageVector = when (index) {
                            0 -> ImageVector.vectorResource(R.drawable.ic_group)
                            1 -> ImageVector.vectorResource(R.drawable.ic_plus_circle)
                            else -> ImageVector.vectorResource(R.drawable.ic_menu)
                        },
                        contentDescription = label
                    )
                },
                selected = selectedItem.value == index,
                onClick = {
                    // Обновление состояния выбранной вкладки
                    selectedItem.value = index
                },
                alwaysShowLabel = false // Чтобы показывать только иконки
            )
        }
    }
}

@Composable
fun BotFragment(modifier: Modifier){

    ConstraintLayout( modifier = modifier
        .clip(RoundedCornerShape(30.dp))
        .background(brush = Brush.verticalGradient(
            colors = listOf(Color(0xFF45278B), Color(0xFF2E444A))
        ))
        .fillMaxWidth()

    ) {

        val (row, textRef3, textRef4, devide, ) = createRefs()

        Text(
            modifier = Modifier
                .padding(start = 10.dp)
                .constrainAs(textRef3){
                    top.linkTo(parent.top, 19.dp)
                    start.linkTo(parent.start, 56.dp)
                },
            text = "Today",
            fontSize = 20.sp,
            color = Color(0xFFFFFFFF)
        )
        Text(
            modifier = Modifier
                .padding(start = 10.dp)
                .constrainAs(textRef4){
                    top.linkTo(parent.top, 19.dp)
                    end.linkTo(parent.end, 56.dp)
                },
            text = "July, 21",
            fontSize = 20.sp,
            color = Color(0xFFFFFFFF)
        )
        Divider(
            color = Color.Gray,
            thickness = 2.dp,
            modifier = Modifier.constrainAs(devide){
                top.linkTo(textRef3.bottom, 12.dp)
            }
        )

        Row(
            modifier = Modifier
                .constrainAs(row){
                    centerHorizontallyTo(parent)
                    top.linkTo(devide.top, 29.dp)
                    bottom.linkTo(parent.bottom, 36.dp)
                },
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            repeat(4) { // Повторяем 4 раза
                WeatherCard("19°C", "15.00")
            }

        }
    }
}

@Composable
fun WeatherCard(temp: String, time: String){
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
fun TopFragment(modifier: Modifier){
    ConstraintLayout(modifier = modifier) {
        val (textRef1, textRef2, row, imageRef, imageRef2) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.ic_rain_cloud_sun),
            contentDescription = "Wether Icon",
            modifier = Modifier
                .size(244.dp)
                .constrainAs(imageRef) {
                    centerHorizontallyTo(parent)
                }
        )
        Text(
            text = "19°",
            modifier = Modifier.constrainAs(textRef1) {
                centerHorizontallyTo(parent)
                top.linkTo(imageRef.bottom, (-20).dp)
            },
            fontSize = 64.sp,
            color = Color(0xFFFFFFFF),
        )
        Text(
            text = "Precipitations",
            modifier = Modifier.constrainAs(textRef2) {
                centerHorizontallyTo(parent)
                top.linkTo(textRef1.bottom)
            },
            fontSize = 24.sp,
            color = Color(0xFFFFFFFF)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(row) {
                    top.linkTo(textRef2.bottom)
                }
        ) {
            Text(
                text = "Max:19°",
                fontSize = 24.sp,
                color = Color(0xFFFFFFFF)
            )
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "Min:19°",
                fontSize = 24.sp,
                color = Color(0xFFFFFFFF)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_house),
            contentDescription = "House Icon",
            modifier = Modifier
                .size(336.dp, 198.dp)
                .constrainAs(imageRef2) {
                    centerHorizontallyTo(parent)
                    top.linkTo(row.bottom, 46.dp)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreenView() {
    val context = LocalContext.current
    val fakeNavController = TestNavHostController(context)
    WeatherScreenView(navController = fakeNavController)
}