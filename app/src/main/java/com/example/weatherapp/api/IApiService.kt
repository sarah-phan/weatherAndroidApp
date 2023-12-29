package com.example.weatherapp.api

import com.example.weatherapp.data.Key.Companion.APIKey
import com.example.weatherapp.model.AirPollutionForecastResult
import com.example.weatherapp.model.HourlyResult
import com.example.weatherapp.model.WeekResult
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {
//    @GET("weather")
//    suspend fun getWeather(
//        @Query ("lat") lat:Double = 0.0,
//        @Query ("lon") lon:Double = 0.0,
//        @Query ("units") units:String = "metric",
//        @Query ("appid") appId:String = APIKey
//        ): WeatherResult

    @GET("forecast/daily")
    suspend fun getWeekly(
        @Query ("lat") lat:Double = 0.0,
        @Query ("lon") lon:Double = 0.0,
        @Query ("units") units:String = "metric",
        @Query ("cnt") cnt:Int = 16,
        @Query ("appid") appId:String = APIKey
    ): WeekResult

    @GET("forecast/hourly")
    suspend fun getHourly(
        @Query ("lat") lat:Double = 0.0,
        @Query ("lon") lon:Double = 0.0,
        @Query ("units") units:String = "metric",
        @Query ("appid") appId:String = APIKey
    ): HourlyResult

    @GET("air_pollution/forecast")
    suspend fun getAirPollutionForecast(
        @Query ("lat") lat:Double = 0.0,
        @Query ("lon") lon:Double = 0.0,
        @Query ("appid") appId:String = APIKey
    ): AirPollutionForecastResult
}