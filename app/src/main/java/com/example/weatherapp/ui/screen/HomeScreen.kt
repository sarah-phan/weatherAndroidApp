package com.example.weatherapp.ui.screen

import androidx.compose.runtime.Composable
import com.example.weatherapp.model.HourlyResult
import com.example.weatherapp.model.WeekResult
import com.example.weatherapp.ui.components.BottomSheetScaffold
import com.example.weatherapp.ui.components.HomeScreenMainWeatherInfor

@Composable
fun HomeScreen(weeklyResult: WeekResult, hourlyResult: HourlyResult) {
    HomeScreenMainWeatherInfor()
    BottomSheetScaffold(
        weeklyResult = weeklyResult,
        hourlyResult = hourlyResult
    )
}