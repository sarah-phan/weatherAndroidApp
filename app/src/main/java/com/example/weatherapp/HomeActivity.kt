package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.Modifier
import com.example.weatherapp.ui.screen.HomeScreen
import com.example.weatherapp.ui.theme.theme_light_primary
import com.example.weatherapp.ui.theme.WeatherAppTheme

class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface(
                    color = theme_light_primary,
                    modifier = Modifier.fillMaxSize()) {
                    val sheetState = rememberModalBottomSheetState()
                    //SplashScreen()
                    //HomeScreen()
//                    ModalBottomSheet(
//                        sheetState = sheetState,
//                        onDismissRequest = { /*TODO*/ })
//                    {
//
//                    }

                }
            }
        }
    }
}