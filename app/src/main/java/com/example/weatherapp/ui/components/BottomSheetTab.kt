package com.example.weatherapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherResult
import com.example.weatherapp.model.WeekResult
import com.example.weatherapp.ui.theme.LightColorPalette
import com.example.weatherapp.ui.theme.bottomSheetShape

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Scaffold(weatherResult: WeatherResult, weeklyResult: WeekResult, sheetContentChosen: @Composable () -> Unit){
    BottomSheetScaffold(
        sheetContainerColor = LightColorPalette.surfaceVariant,
        sheetContent = {
            sheetContentChosen()
        },
        scaffoldState = rememberBottomSheetScaffoldState(),
        sheetPeekHeight = 250.dp,
        sheetShape = bottomSheetShape
    ) {
        Image(
            painterResource(id = R.drawable.bg_light),
            contentDescription ="",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.Crop,
            alpha = 0.25f
            )
    }
}