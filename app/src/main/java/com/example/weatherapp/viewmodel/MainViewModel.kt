package com.example.weatherapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.RetrofitClient
import com.example.weatherapp.model.Coord
import com.example.weatherapp.model.HourlyResult
import com.example.weatherapp.model.WeekResult
import kotlinx.coroutines.launch

enum class STATE{
    LOADING,
    SUCCESS,
    FAILED
}

class MainViewModel: ViewModel() {
    var state by mutableStateOf(STATE.LOADING)
//    var weatherResponse: WeatherResult by mutableStateOf(WeatherResult())
    var weeklyResponse: WeekResult by mutableStateOf(WeekResult())
    var hourlyResponse: HourlyResult by mutableStateOf(HourlyResult())
    var errorMsg: String by mutableStateOf("")

//    fun getWeatherResponse(coord: Coord){
//        Log.d("API", "API Called!")
//        viewModelScope.launch{
//            state = STATE.LOADING
//            val apiService = RetrofitClient.getInstance()
//            try{
//                val apiResponse = apiService.getWeather(coord.lat,coord.lon)
//                weatherResponse = apiResponse
//                state = STATE.SUCCESS
//            }
//            catch (ex:Exception){
//                errorMsg = ex.message!!.toString()
//                state = STATE.FAILED
//            }
//        }
//    }
    fun getWeeklyResponse(coord: Coord){
        viewModelScope.launch{
            state = STATE.LOADING
            val apiService = RetrofitClient.getInstance()
            try{
                val apiResponse = apiService.getWeekly(coord.lat,coord.lon)
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
             val apiService = RetrofitClient.getInstance()
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
}