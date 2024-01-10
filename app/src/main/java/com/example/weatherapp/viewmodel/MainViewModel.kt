package com.example.weatherapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.RetrofitClient
import com.example.weatherapp.data.SortType
import com.example.weatherapp.data.WeatherList
import com.example.weatherapp.data.WeatherListDAO
import com.example.weatherapp.data.WeatherListEvent
import com.example.weatherapp.data.WeatherListState
import com.example.weatherapp.model.AirPollutionCurrentResult
import com.example.weatherapp.model.AirPollutionForecastResult
import com.example.weatherapp.model.Coord
import com.example.weatherapp.model.HourlyResult
import com.example.weatherapp.model.WeatherResult
import com.example.weatherapp.model.WeekResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class STATE{
    LOADING,
    SUCCESS,
    FAILED
}

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModel(
    private val dao: WeatherListDAO
): ViewModel() {
    var state by mutableStateOf(STATE.LOADING)
    var weatherResponse: WeatherResult by mutableStateOf(WeatherResult())
    var weeklyResponse: WeekResult by mutableStateOf(WeekResult())
    var hourlyResponse: HourlyResult by mutableStateOf(HourlyResult())
    var airPollutionForecastResponse: AirPollutionForecastResult by mutableStateOf(
        AirPollutionForecastResult()
    )
    var airPollutionCurrentResponse: AirPollutionCurrentResult by mutableStateOf(AirPollutionCurrentResult())
    var errorMsg: String by mutableStateOf("")
    private val _sortType = MutableStateFlow(SortType.CITY)
    private val _weatherLists = _sortType
        .flatMapLatest { _sortType ->
                when(_sortType) {
                    SortType.CITY -> dao.getWeatherInfoOrderedByCity()
                }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(WeatherListState())
    val weatherListState = combine(_state, _sortType, _weatherLists) {
            state, sortType, weatherList ->
        state.copy(
            weatherList = weatherList,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), WeatherListState())
    fun onEvent(event: WeatherListEvent){
        when(event) {
            is WeatherListEvent.DeleteWeatherInfo -> {
                viewModelScope.launch {
                    dao.deleteWeatherInfo(event.weatherList)
                }
            }
            WeatherListEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingWeatherList = false
                    )
                }
            }
            WeatherListEvent.SaveWeatherInfo -> {
                val city = weatherListState.value.city
                val lat = weatherListState.value.lat
                val lon = weatherListState.value.lon
                val country = weatherListState.value.country
                val date = weatherListState.value.date
                val temperature = weatherListState.value.temperature
                val iconTemperature = weatherListState.value.iconTemperature
                val maxTemp = weatherListState.value.maxTemp
                val minTemp = weatherListState.value.minTemp
                if (city.isBlank()) {
                    return
                }
                val weatherList = WeatherList(
                    lat = lat,
                    lon = lon,
                    city = city,
                    country = country,
                    date = date,
                    temperature = temperature,
                    iconTemperature = iconTemperature,
                    maxTemp = maxTemp,
                    minTemp = minTemp
                )
                viewModelScope.launch {
                    dao.upsertWeatherInfo(weatherList)
                }
                _state.update { it.copy(
                    isAddingWeatherList = false,
                    lat = 0.0,
                    lon = 0.0,
                    city = "",
                    country = "",
                    date = "",
                    temperature = "",
                    iconTemperature = "",
                    maxTemp = "",
                    minTemp = ""
                ) }
            }
            is WeatherListEvent.SetCity -> {
                _state.update { it.copy(
                    city = event.city
                ) }
            }
            WeatherListEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingWeatherList = true
                ) }
            }

            is WeatherListEvent.SortWeatherList -> {
                _sortType.value = event.sortType
            }
        }
    }
    fun getWeatherResponse(coord: Coord){
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
    fun getAirPollutionCurrentResponse(coord: Coord) {
        viewModelScope.launch {
            state = STATE.LOADING
            val apiService = RetrofitClient.getInstanceMain()
            try{
                val apiResponse = apiService.getAirPollutionCurrent(coord.lat, coord.lon)
                airPollutionCurrentResponse = apiResponse
                state = STATE.SUCCESS
            }
            catch (ex:Exception) {
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
}