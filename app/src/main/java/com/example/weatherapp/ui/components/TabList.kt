package com.example.weatherapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.model.HourlyResult
import com.example.weatherapp.model.WeekResult
import com.example.weatherapp.ui.screen.HomeScreenWeekly

@Composable
fun TabList(resultData: Any) {
//    var hourlyData: HourlyResult? = null
//    var weeklyData: WeekResult? = null
    var selectedIndex = remember {
        mutableStateOf(0)
    }
    if (resultData is HourlyResult) {
        resultData.list.let { listForecast ->
            if (listForecast.size > 0) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 0.dp,
                            start = dimensionResource(id = R.dimen.padding_small),
                        ),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                }
            }

        }
    } else if (resultData is WeekResult) {
        resultData.list.let { listForecast ->
            if (listForecast.size > 0) {
                WeeklyTabList(
                    weeklyDataList = listForecast,
                    selectedIndex = selectedIndex
                )
            }
            HomeScreenWeekly(weeklyDetailSelected = listForecast[selectedIndex.value])
        }
    }

}