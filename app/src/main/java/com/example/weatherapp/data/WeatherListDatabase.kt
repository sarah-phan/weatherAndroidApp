package com.example.weatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(
    entities = [WeatherList::class],
    version = 1
)
abstract class WeatherListDatabase: RoomDatabase() {
    abstract val dao: WeatherListDAO
}