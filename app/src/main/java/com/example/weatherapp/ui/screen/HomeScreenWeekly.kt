package com.example.weatherapp.ui.screen

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.model.List2
import com.example.weatherapp.ui.theme.LightColorPalette
import com.example.weatherapp.ui.theme.Shapes
import com.example.weatherapp.ui.theme.lineColor
import kotlin.math.roundToInt

@Composable
fun HomeScreenWeekly(weeklyDetailSelected: List2) {
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
            PeriodData(period = item, infor = weeklyDetailSelected)
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

                    var selectedData = weeklyDetailSelected
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


@Composable
fun PeriodData(period: String, infor: List2) {
    var temp: Int? = 0
    var feels_like: Int? = 0
    when (period) {
        "6" -> {
            infor.let { item ->
                temp = item.temp?.morn?.roundToInt()
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
                    .padding(top = 8.dp)
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