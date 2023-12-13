package com.example.weatherapp.api

import com.example.weatherapp.data.Key.Companion.APIKey
import com.example.weatherapp.model.WeatherResult
import com.example.weatherapp.model.WeekResult
import com.example.weatherapp.model.WeeklyResult
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query ("lat") lat:Double = 0.0,
        @Query ("lon") lon:Double = 0.0,
        @Query ("units") units:String = "metric",
        @Query ("appid") appId:String = APIKey
        ): WeatherResult

    @GET("forecast/daily")
    suspend fun getWeekly(
        @Query ("lat") lat:Double = 0.0,
        @Query ("lon") lon:Double = 0.0,
        @Query ("units") units:String = "metric",
        @Query ("appid") appId:String = APIKey
    ): WeekResult

    @GET("forecast")
    suspend fun getWeekly2(
        @Query ("lat") lat:Double = 0.0,
        @Query ("lon") lon:Double = 0.0,
        @Query ("units") units:String = "metric",
        @Query ("appid") appId:String = APIKey
    ): WeeklyResult
}