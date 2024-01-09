package com.example.weatherapp.ui.screen

import androidx.compose.runtime.Composable
import com.example.weatherapp.model.AirPollutionCurrentResult
import com.example.weatherapp.model.AirPollutionForecastResult
import com.example.weatherapp.model.HourlyResult
import com.example.weatherapp.model.WeatherResult
import com.example.weatherapp.model.WeekResult
import com.example.weatherapp.ui.components.BottomSheetScaffold


@Composable
fun HomeScreen(
    darkTheme: Boolean,
    onThemeUpdated: (Boolean)-> Unit,
    weatherResult: WeatherResult,
    weeklyResult: WeekResult,
    hourlyResult: HourlyResult,
    airPollutionForecastResult: AirPollutionForecastResult,
    airPollutionCurrentResult: AirPollutionCurrentResult
) {
//    Log.d("test", airPollutionForecastResult.toString())

    BottomSheetScaffold(
        darkTheme= darkTheme,
        onThemeUpdated = onThemeUpdated,
        weeklyResult = weeklyResult,
        hourlyResult = hourlyResult,
        airPollutionForecastResult = airPollutionForecastResult,
        weatherResult = weatherResult,
        airPollutionCurrentResult = airPollutionCurrentResult
    )

}


