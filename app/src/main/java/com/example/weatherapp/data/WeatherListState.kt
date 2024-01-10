package com.example.weatherapp.data

data class WeatherListState (
    val weatherList: List<WeatherList> = emptyList(),
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val city: String = "",
    val country: String = "",
    val date: String = "",
    val temperature: String = "",
    val iconTemperature: String = "",
    val maxTemp: String = "",
    val minTemp: String = "",
    val isAddingWeatherList: Boolean = false,
    val sortType: SortType = SortType.CITY
)