package com.example.weatherapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.model.AirPollutionCurrentResult
import com.example.weatherapp.model.AirPollutionForecastResult
import com.example.weatherapp.model.HourlyResult
import com.example.weatherapp.model.WeatherResult
import com.example.weatherapp.model.WeekResult
import com.example.weatherapp.ui.screen.HomeScreen
import com.example.weatherapp.ui.screen.InsertScreen

@Composable
fun SetupNavGraph(
    darkTheme: Boolean,
    onThemeUpdated: (Boolean)-> Unit,
    navController: NavHostController,
    weatherResult: WeatherResult,
    weeklyResult: WeekResult,
    hourlyResult: HourlyResult,
    airPollutionForecastResult: AirPollutionForecastResult,
    airPollutionCurrentResult: AirPollutionCurrentResult,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(
                darkTheme,
                onThemeUpdated = onThemeUpdated,
                weatherResult = weatherResult,
                weeklyResult = weeklyResult,
                hourlyResult = hourlyResult,
                airPollutionForecastResult = airPollutionForecastResult,
                airPollutionCurrentResult = airPollutionCurrentResult,
                navController = navController
            )
        }
        composable(
            route = Screen.Insert.route
        ){
            InsertScreen(weatherLocationData = weatherResult, navController = navController)
        }
    }
}