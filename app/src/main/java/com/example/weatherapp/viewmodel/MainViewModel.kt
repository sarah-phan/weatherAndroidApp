package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.RetrofitClient
import com.example.weatherapp.model.AirPollutionCurrentResult
import com.example.weatherapp.model.AirPollutionForecastResult
import com.example.weatherapp.model.AirPollutionList
import com.example.weatherapp.model.Coord
import com.example.weatherapp.model.HourlyList
import com.example.weatherapp.model.HourlyResult
import com.example.weatherapp.model.WeatherResult
import com.example.weatherapp.model.WeekResult
import kotlinx.coroutines.launch

enum class STATE{
    LOADING,
    SUCCESS,
    FAILED
}

class MainViewModel: ViewModel() {
    var state by mutableStateOf(STATE.LOADING)
    var weatherResponse: WeatherResult by mutableStateOf(WeatherResult())
    var weeklyResponse: WeekResult by mutableStateOf(WeekResult())
    var hourlyResponse: HourlyResult by mutableStateOf(HourlyResult())
    var airPollutionForecastResponse: AirPollutionForecastResult by mutableStateOf(
        AirPollutionForecastResult()
    )
    var airPollutionCurrentResponse: AirPollutionCurrentResult by mutableStateOf(
        AirPollutionCurrentResult()
    )
    var errorMsg: String by mutableStateOf("")


    fun getWeatherResponse(coord: Coord){
        Log.d("API", "API Called!")
        viewModelScope.launch{
            state = STATE.LOADING
            val apiService = RetrofitClient.getInstanceMain()
            try{
                val apiResponse = apiService.getWeather(coord.lat,coord.lon)
                weatherResponse = apiResponse
                state = STATE.SUCCESS
            }
            catch (ex:Exception){
                errorMsg = ex.message!!.toString()
                state = STATE.FAILED
            }
        }
    }
    fun getWeeklyResponse(coord: Coord){
        viewModelScope.launch{
            state = STATE.LOADING
            val apiService = RetrofitClient.getInstanceMain()
            try{
                val apiResponse = apiService.getWeekly(coord.lat,coord.lon)
                Log.d("apiResponse",apiResponse.toString())
                weeklyResponse = apiResponse
                state = STATE.SUCCESS
            }
            catch (ex:Exception){
                errorMsg = ex.message!!.toString()
                state = STATE.FAILED
            }
        }
    }

    fun getHourlyResponse(coord: Coord){
         viewModelScope.launch {
             state = STATE.LOADING
             val apiService = RetrofitClient.getInstanceMain()
             try {
                 val apiResponse = apiService.getHourly(coord.lat, coord.lon)
                 hourlyResponse = apiResponse
                 state = STATE.SUCCESS
             }
             catch (ex:Exception){
                 errorMsg = ex.message!!.toString()
                 state = STATE.FAILED
             }
         }
    }

    fun getAirPollutionResponse(coord: Coord){
        viewModelScope.launch {
            state = STATE.LOADING
            val apiService = RetrofitClient.getInstanceMain()
            try {
                val apiResponse = apiService.getAirPollutionForecast(coord.lat, coord.lon)
                airPollutionForecastResponse = apiResponse
                state = STATE.SUCCESS
            }
            catch (ex:Exception){
                errorMsg = ex.message!!.toString()
                state = STATE.FAILED
            }
        }
    }
    fun getAirPollutionCurrentResponse(coord: Coord){
        viewModelScope.launch {
            state = STATE.LOADING
            val apiService = RetrofitClient.getInstanceMain()
            try {
                val apiResponse = apiService.getAirPollutionCurrent(coord.lat, coord.lon)
                airPollutionCurrentResponse = apiResponse
                state = STATE.SUCCESS
            }
            catch (ex:Exception){
                errorMsg = ex.message!!.toString()
                state = STATE.FAILED
            }
        }
    }
}