package com.example.weatherapp.ui.components

import androidx.compose.runtime.Composable

@Composable
fun WindDirection(degree: Double?): String {
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