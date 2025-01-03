package com.example.weatherapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import com.example.weatherapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreenView(navController: NavController) {

    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF45278B), Color(0xFF2E335A))
                )
            ),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_rain_cloud_sun),
            contentDescription = "Sample SVG",
            modifier = Modifier.size(428.dp)
        )
        Text(
            fontSize = 64.sp,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(4f, 4f),
                    blurRadius = 8f
                )
            ),
            color = Color(0xFFFFFFFF),
            text = "Weather"
        )
        Text(
            color = Color(0xFFDDB130),
            fontSize = 64.sp,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(4f, 4f),
                    blurRadius = 8f
                )
            ),
            text = "ForeCasts"
        )
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {

            val button1 : ConstrainedLayoutReference = createRef()

            Button(
                modifier = Modifier
                    .size(304.dp, 72.dp)
                    .constrainAs(button1){
                        top.linkTo(parent.top, margin = 53.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        centerHorizontallyTo(parent)
                    },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFDDB130),
                    contentColor = Color(0xFF362A84)
                ),
                onClick = {
                    if (!isLoading) {
                        isLoading = true

                        navController.navigate("weather")

                        coroutineScope.launch {
                            delay(1000)
                            isLoading = false
                        }
                    }
                }
            ) {
                Text(
                    text = "Get Start",
                    fontSize = 28.sp
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewHomeScreenView() {
    val context = LocalContext.current
    val fakeNavController = TestNavHostController(context)
    HomeScreenView(navController = fakeNavController)
}