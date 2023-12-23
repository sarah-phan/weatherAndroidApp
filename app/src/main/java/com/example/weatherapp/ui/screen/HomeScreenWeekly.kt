package com.example.weatherapp.ui.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.model.List2
import com.example.weatherapp.model.WeatherResult
import com.example.weatherapp.model.WeekResult
import com.example.weatherapp.ui.components.Scaffold
import com.example.weatherapp.ui.theme.LightColorPalette
import com.example.weatherapp.ui.theme.Shapes
import com.example.weatherapp.ui.theme.lineColor
import com.example.weatherapp.utils.Utils.Companion.timestampToHumanDate
import kotlin.math.roundToInt

@Composable
fun HomeScreenWeekly(weatherResult: WeatherResult, weeklyResult: WeekResult) {
    Scaffold(
        peekHeight = 300.dp
    ) {
        BSheet(weeklyResult = weeklyResult)
        Log.d("weeklyResult", weeklyResult.toString())
    }
}

@Composable
fun BSheet(weeklyResult: WeekResult) {
    val periods = listOf("6", "12", "18", "24")
    val details = listOf(
        "Max Temperature",
        "Min Temperature",
        "Rain",
        "Humidity",
        "Visibility",
        "Cloud",
        "Snow probability",
        "Ice probability",
        "Wind direction",
        "Wind gust",
        "Wind speed"
    )
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LightColorPalette.surfaceVariant)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.padding_small))
        ) {
            var tabButtonSelected by remember { mutableStateOf(0) }
            val tabButtons = listOf("Hourly Forecast", "Weekly Forecast")
            for (tabButtonsIndex in tabButtons.indices) {
                val isTabButtonSelected = tabButtonSelected == tabButtonsIndex
                Button(
                    onClick = {
                        tabButtonSelected = tabButtonsIndex
                    },
                    shape = Shapes.extraSmall,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isTabButtonSelected) LightColorPalette.secondary else Color.Transparent,
                    ),
                    modifier = Modifier.widthIn(max = 150.dp),
                    border = BorderStroke(
                        width = 1.dp, color = LightColorPalette.secondary
                    )
                ) {
                    Text(
                        text = tabButtons[tabButtonsIndex],
                        color = if (isTabButtonSelected) LightColorPalette.onSecondary else LightColorPalette.onSurfaceVariant,
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(0.dp)
                    )
                }
            }
        }

        Divider(
            color = lineColor,
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small))
        )
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
                        var day = ""
                        var time = ""
                        item.dt.let { dateTime ->
                            day = if (dateTime == null) "00"
                            else timestampToHumanDate(dateTime.toLong(), "EEE")
                            time = if (dateTime == null) "00"
                            else timestampToHumanDate(dateTime.toLong(), "dd/MM")
                        }
                        Box(modifier = Modifier
                            .height(120.dp)
                            .width(50.dp)
                            .background(
                                color = if (index == selectedIndex) LightColorPalette.primary else LightColorPalette.primaryContainer,
                                shape = Shapes.large
                            )
                            .selectable(selected = item.dt == selectedIndex, onClick = {
                                if (selectedIndex != index) selectedIndex = index
                            })) {
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
                            val weatherIcon = item.weather?.firstOrNull()?.icon
                            val weatherMainInformation = item.weather?.firstOrNull()?.main
                            if (weatherIcon != null) {
                                AsyncImage(
                                    model = "https://openweathermap.org/img/wn/${weatherIcon}@2x.png",
                                    contentDescription = weatherMainInformation
                                )
                            }
                        }
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(
                            bottom = dimensionResource(id = R.dimen.padding_medium),
                            top = 15.dp,
                        )
                        .height(310.dp),
                    userScrollEnabled = false
                ) {
                    items(items = periods) { item ->
                        PeriodData(period = item, listForecast[selectedIndex])
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(id = R.dimen.padding_small),
                            end = dimensionResource(id = R.dimen.padding_small),
                            bottom = dimensionResource(id = R.dimen.padding_small)
                        )
                        .border(BorderStroke(2.dp, lineColor), shape = Shapes.medium)
                        .background(Color.Transparent)
                ) {
                    Text(
                        text = "Details",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = dimensionResource(id = R.dimen.padding_small))
                            .align(Alignment.CenterHorizontally),
                        color = LightColorPalette.onSurface,
                        style = MaterialTheme.typography.titleSmall
                    )
                    LazyColumn(
                        userScrollEnabled = false,
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 5.dp)
                            .height(410.dp),
                    ) {
                        itemsIndexed(items = details) { index, item ->
                            Row(
                                modifier = Modifier.padding(vertical = 5.dp),
                            ) {
                                Text(
                                    modifier = Modifier.width(200.dp),
                                    color = LightColorPalette.onSurface,
                                    text = item,
                                    textAlign = TextAlign.Start,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                var string = ""

                                var selectedData = listForecast[selectedIndex]
                                when (index) {
                                    0 -> string = "${selectedData.temp?.max?.roundToInt()}°"

                                    1 -> string = "${selectedData.temp?.min?.roundToInt()}°"

                                    2 -> string = "${(selectedData.rain?.roundToInt())}%"

                                    3 -> string = "${selectedData.humidity?.roundToInt()}%"

                                    4 -> string = "${(selectedData.visibility?.roundToInt())}°"

                                    5 -> string = "${(selectedData.clouds)}%"

                                    6 -> string = "${(selectedData.snow)}%"

                                    7 -> string = "${(selectedData.ice)}%"

                                    8 -> string = "${selectedData.deg?.roundToInt()}°${
                                        toDirection(selectedData.deg)
                                    }"

                                    9 -> string = "${selectedData.gust} km/h"

                                    10 -> string = "${selectedData.speed} km/h"

                                }
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    color = LightColorPalette.onSurface,
                                    text = string,
                                    textAlign = TextAlign.End,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }

                }
            }
        }

    }
}

@Composable
fun PeriodData(period: String, infor: List2) {
    var temp: Int? = 0
    var feels_like: Int? = 0
    Log.d("period", period)
    when (period) {
        "6" -> {
            infor.let { item ->
                Log.d("item", item.toString())
                temp = item.temp?.morn?.roundToInt()
                Log.d("temp", temp.toString())
                feels_like = item.feel?.morn?.roundToInt()
            }
        }

        "12" -> {
            infor.let { item ->
                temp = item.temp?.day?.roundToInt()
                feels_like = item.feel?.day?.roundToInt()
            }
        }

        "18" -> {
            infor.let { item ->
                temp = item.temp?.eve?.roundToInt()
                feels_like = item.feel?.eve?.roundToInt()
            }
        }

        else -> {
            infor.let { item ->
                temp = item.temp?.night?.roundToInt()
                feels_like = item.feel?.night?.roundToInt()
            }
        }
    }

    Row(
        modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.padding_small),
                start = dimensionResource(id = R.dimen.padding_small),
                bottom = 0.dp
        )
    ) {
        Box(
            modifier = Modifier
                .width(65.dp)
                .padding(top = dimensionResource(id = R.dimen.padding_extra_small))
                .background(color = LightColorPalette.tertiary, shape = Shapes.extraSmall),
        ) {
            Text(
                text = "${period}h",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 8.dp,)
                    .align(Alignment.Center),
                color = LightColorPalette.onPrimaryContainer,
                style = MaterialTheme.typography.titleSmall,
            )
        }
        Box(
            modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.padding_small), bottom = 0.dp
                )
        ) {
            Column() {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Text(
                        text = "Temperature",
                        textAlign = TextAlign.Left,
                        color = LightColorPalette.onSurface,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                    Text(
                        text = "${temp}°",
                        textAlign = TextAlign.Left,
                        color = LightColorPalette.onSurface,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.height(25.dp)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(42.dp)
                ) {
                    Text(
                        text = "Feels like",
                        textAlign = TextAlign.Left,
                        color = LightColorPalette.onSurface,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                    Text(
                        text = "${feels_like}°",
                        textAlign = TextAlign.Left,
                        color = LightColorPalette.onSurface,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.height(25.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    Surface(modifier = Modifier.fillMaxWidth(), color = Color.Transparent) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(painterResource(id = R.drawable.ic_search), contentDescription = "")
        }
    }
}

fun toDirection(degree: Double?): String {
    val directions = arrayListOf(
        "N",
        "NNE",
        "NE",
        "ENE",
        "E",
        "ESE",
        "SE",
        "SSE",
        "S",
        "SSW",
        "SW",
        "WSW",
        "W",
        "WNW",
        "NW",
        "NNW",
        "N"
    )
    if (degree != null) {
        return directions[(degree.div(22.5).toInt())]
    }
    return ""
}

@Preview
@Composable
fun HomeScreenWeeklyPreview() {
    var weatherResult = WeatherResult()
    var weeklyResult = WeekResult()
    HomeScreenWeekly(weatherResult = weatherResult, weeklyResult = weeklyResult)
}