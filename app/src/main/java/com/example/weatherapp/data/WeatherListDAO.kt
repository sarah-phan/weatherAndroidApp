package com.example.weatherapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import kotlin.collections.List


@Dao
interface WeatherListDAO {
    @Upsert
    suspend fun upsertWeatherInfo(weatherList: WeatherList)

    @Delete
    suspend fun deleteWeatherInfo(weatherList: WeatherList)

    @Query("SELECT * FROM weatherlist ORDER BY city ASC")
    fun getWeatherInfoOrderedByCity(): Flow<List<WeatherList>>
}