package com.example.weatherapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.model.AirPollutionCurrentResult
import com.example.weatherapp.model.AirPollutionForecastResult
import com.example.weatherapp.model.HourlyResult
import com.example.weatherapp.model.WeatherResult
import com.example.weatherapp.model.WeekResult
import com.example.weatherapp.ui.theme.Shapes
import kotlinx.coroutines.launch

@Composable
fun BSheetOptions(
    darkTheme: Boolean,
    weeklyResult: WeekResult,
    hourlyResult: HourlyResult,
    airPollutionForecastResult: AirPollutionForecastResult,
    weatherResult: WeatherResult,
    airPollutionCurrentResult: AirPollutionCurrentResult
) {
    var optionButtonsChosen by remember { mutableStateOf(0) }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .verticalScroll(rememberScrollState())
    ) {
        val lazyListState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()
        val optionButtons = listOf("Current Weather", "Hourly Forecast", "Weekly Forecast")
        LazyRow(
            state = lazyListState, horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.padding_extra_small)
            ), userScrollEnabled = true, modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.padding_small),
                    bottom = dimensionResource(id = R.dimen.padding_small)
                )
        ) {
            itemsIndexed(optionButtons) { index, item ->
                val isOptionButtonsChosen = optionButtonsChosen == index
                Button(
                    onClick = {
                        optionButtonsChosen = index
                        coroutineScope.launch {
                            when (index) {
                                0 -> lazyListState.scrollToItem(0) // Scroll to the start for the first button
                                optionButtons.lastIndex -> lazyListState.scrollToItem(index) // Scroll to the end for the last button
                            }
                        }
                    },
                    shape = Shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isOptionButtonsChosen) MaterialTheme.colorScheme.secondary else Color.Transparent,
                    ),
                    modifier = Modifier
                        .padding(0.dp)
                        .widthIn(max = 150.dp),
                    border = BorderStroke(
                        width = 2.dp, color = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(
                        text = optionButtons[index],
                        color = if (isOptionButtonsChosen) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(0.dp)
                    )
                }
            }
        }


        Divider(
            color = MaterialTheme.colorScheme.outline,
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small))
        )

        if (optionButtonsChosen == 0) {
            TabList(
                darkTheme,
                resultData = weatherResult,
                airPollutionCurrentResult = airPollutionCurrentResult
            )
        } else if (optionButtonsChosen == 1) {
            TabList(
                darkTheme,
                resultData = hourlyResult, airPollutionForecastResult = airPollutionForecastResult
            )
        }else if (optionButtonsChosen == 2){
            TabList(darkTheme,
                resultData = weeklyResult)
        }
    }
}