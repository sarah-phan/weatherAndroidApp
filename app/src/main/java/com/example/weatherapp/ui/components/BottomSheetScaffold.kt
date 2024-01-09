package com.example.weatherapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.model.AirPollutionCurrentResult
import com.example.weatherapp.model.AirPollutionForecastResult
import com.example.weatherapp.model.HourlyResult
import com.example.weatherapp.model.WeatherResult
import com.example.weatherapp.model.WeekResult
import com.example.weatherapp.ui.theme.LightColorPalette
import com.example.weatherapp.ui.theme.bottomSheetShape

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BottomSheetScaffold(
    darkTheme: Boolean,
    onThemeUpdated: (Boolean)-> Unit,
    weeklyResult: WeekResult,
    hourlyResult: HourlyResult,
    weatherResult: WeatherResult,
    airPollutionForecastResult: AirPollutionForecastResult,
    airPollutionCurrentResult: AirPollutionCurrentResult
) {
    Scaffold() {
        it
        BottomSheetScaffold(

            sheetContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            sheetContent = {
                BSheetOptions(
                    darkTheme,
                    weeklyResult = weeklyResult,
                    hourlyResult = hourlyResult,
                    airPollutionForecastResult = airPollutionForecastResult,
                    weatherResult = weatherResult,
                    airPollutionCurrentResult = airPollutionCurrentResult
                )
            },
            scaffoldState = rememberBottomSheetScaffoldState(),
            sheetPeekHeight = 320.dp,
            sheetShape = bottomSheetShape
        ) {
            Image(
                painterResource(id = if(darkTheme) R.drawable.bg_dark else R.drawable.bg_light),
                contentDescription = "",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Crop,
                alpha = 0.25f
            )
            HomeScreenMainWeatherInfor(darkTheme = darkTheme, onThemeUpdated = onThemeUpdated, weatherDataList = weatherResult)
        }

    }

}