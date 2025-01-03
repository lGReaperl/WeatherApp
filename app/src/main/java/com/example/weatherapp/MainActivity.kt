package com.example.weatherapp

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.weatherapp.ui.navigation.MainNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainNavHost()
            val context = LocalContext.current
            LaunchedEffect(Unit) {
                hideSystemUI(context)
            }

            val decorView = window.decorView
            decorView.setOnSystemUiVisibilityChangeListener { visibility ->
                if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                    GlobalScope.launch(Dispatchers.Main) {
                        delay(2000)  // Задержка 2 секунды
                        hideSystemUI(context)  // Скрытие панелей после задержки
                    }
                }
            }
        }
    }
}

fun hideSystemUI(context: android.content.Context) {
    val activity = context as ComponentActivity

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowInsetsController = activity.window.insetsController
        windowInsetsController?.hide(android.view.WindowInsets.Type.statusBars() or android.view.WindowInsets.Type.navigationBars())
    } else {
        val decorView = activity.window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }
}