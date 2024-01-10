package com.example.weatherapp.ui.theme

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Insert: Screen(route = "insert_screen")
}