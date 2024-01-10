package com.example.weatherapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class WeatherList(
    val lat: Double,
    val lon: Double,
    val city: String,
    val country: String,
    val date: String,
    val temperature: String,
    val iconTemperature: String,
    val maxTemp: String,
    val minTemp: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
