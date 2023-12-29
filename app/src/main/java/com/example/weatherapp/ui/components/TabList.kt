package com.example.weatherapp.ui.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.weatherapp.model.HourlyResult
import com.example.weatherapp.model.WeekResult
import com.example.weatherapp.ui.screen.HomeScreenHourly
import com.example.weatherapp.ui.screen.HomeScreenWeekly

@Composable
fun TabList(resultData: Any) {
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
                HomeScreenHourly(
                    hourlyDataSelected = listForecast[selectedIndex.value],
                    hourlyResultData = resultData
                )
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