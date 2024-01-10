package com.example.weatherapp.data

sealed interface WeatherListEvent {
    object SaveWeatherInfo: WeatherListEvent
    data class SetCity(val city: String): WeatherListEvent
    object ShowDialog: WeatherListEvent
    object HideDialog: WeatherListEvent
    data class SortWeatherList(val sortType: SortType): WeatherListEvent
    data class DeleteWeatherInfo(val weatherList: WeatherList): WeatherListEvent

}