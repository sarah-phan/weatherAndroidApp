package com.example.weatherapp.ui.screen

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.weatherapp.model.HourlyResult
import com.example.weatherapp.model.WeekResult
import com.example.weatherapp.ui.components.BottomSheetScaffold
import com.example.weatherapp.ui.components.HomeScreenMainWeatherInfor

@Composable
fun HomeScreen(weeklyResult: WeekResult, hourlyResult: HourlyResult) {
    HomeScreenMainWeatherInfor()
    Log.d("test", weeklyResult.toString())
    BottomSheetScaffold(
        weeklyResult = weeklyResult,
        hourlyResult = hourlyResult
    )
}