package com.example.weatherapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.model.List2
import com.example.weatherapp.model.WeekResult
import com.example.weatherapp.ui.theme.LightColorPalette
import com.example.weatherapp.ui.theme.Shapes
import com.example.weatherapp.utils.Utils

@Composable
fun weeklyTabList(
    weeklyResult: WeekResult,
    sheetContent: @Composable (List2) -> Unit,
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    weeklyResult.list.let { listForecast ->
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
                itemsIndexed(listForecast) { index, item ->
                    val weatherIcon = item.weather?.firstOrNull()?.icon
                    val weatherMainInformation = item.weather?.firstOrNull()?.main
                    var day = ""
                    var time = ""
                    item.dt.let { dateTime ->
                        day = if (dateTime == null) "00"
                        else Utils.timestampToHumanDate(dateTime.toLong(), "EEE")
                        time = if (dateTime == null) "00"
                        else Utils.timestampToHumanDate(dateTime.toLong(), "dd/MM")
                    }
                    Box(
                        modifier = Modifier
                            .height(120.dp)
                            .width(50.dp)
                            .background(
                                color = if (index == selectedIndex) LightColorPalette.primary else LightColorPalette.primaryContainer,
                                shape = Shapes.large
                            )
                            .selectable(selected = item.dt == selectedIndex, onClick = {
                                if (selectedIndex != index) selectedIndex = index
                            })
                    ) {
                        Text(
                            text = "${day.uppercase()}",
                            modifier = Modifier
                                .padding(
                                    bottom = 40.dp
                                )
                                .align(Alignment.Center),
                            color = LightColorPalette.onPrimaryContainer,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "$time",
                            modifier = Modifier.align(Alignment.Center),
                            color = LightColorPalette.onPrimaryContainer,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        if (weatherIcon != null) {
                            AsyncImage(
                                model = "https://openweathermap.org/img/wn/${weatherIcon}@2x.png",
                                contentDescription = weatherMainInformation,
                                modifier = Modifier
                                    .padding(top = 65.dp)
                                    .size(50.dp),
                                alignment = Alignment.Center
                            )
                        }
                    }
                }
            }
        }
        val selectedWeekResult = listForecast.getOrNull(selectedIndex)
        selectedWeekResult?.let {
            sheetContent(it)
        }
    }
}