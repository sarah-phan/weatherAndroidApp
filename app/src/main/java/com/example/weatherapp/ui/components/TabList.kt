package com.example.weatherapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.weatherapp.model.AirPollutionForecastResult
import com.example.weatherapp.model.HourlyResult
import com.example.weatherapp.model.WeekResult
import com.example.weatherapp.ui.screen.HomeScreenHourly
import com.example.weatherapp.ui.screen.HomeScreenWeekly

@Composable
fun TabList(
    resultData: Any,
    airPollutionForecastResult: AirPollutionForecastResult = AirPollutionForecastResult()
) {
    var selectedIndex = remember {
        mutableStateOf(0)
    }
    if (resultData is HourlyResult) {
        resultData.list.let { listForecast ->
            if (listForecast.size > 0) {
                HourlyTabList(
                    hourlyDataList = listForecast,
                    selectedIndex = selectedIndex,
                )
                airPollutionForecastResult.list.let{
                    HomeScreenHourly(
                        hourlyDataSelected = listForecast[selectedIndex.value],
                        hourlyResultData = resultData,
                        airPollutionForecastResultSelected = it[selectedIndex.value + 1]
                    )
                }

            }
        }
    } else if (resultData is WeekResult) {
//        Log.d("resultData", resultData.toString())
        resultData.list.let { listForecast ->
            if (listForecast.size > 0) {
                WeeklyTabList(
                    weeklyDataList = listForecast,
                    selectedIndex = selectedIndex
                )
                HomeScreenWeekly(weeklyDetailSelected = listForecast[selectedIndex.value])
            }
        }
    }
}