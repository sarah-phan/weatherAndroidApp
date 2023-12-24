package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

interface ResultList
data class HourlyResult(
    @SerializedName("cod") var cod: String? = null,
    @SerializedName("message") var message: Double? = 0.0,
    @SerializedName("cnt") var cnt: Int? = 0,
    @SerializedName("list") var list: ArrayList<HourlyList> = arrayListOf(),
    @SerializedName("city") var city: CityModel = CityModel()
)

data class HourlyList(
    @SerializedName("dt") var dt: Int? = 0,
    @SerializedName("main") var main : MainModel? = MainModel(),
    @SerializedName("weather") var weather:ArrayList< WeatherModel>? = arrayListOf(),
    @SerializedName("clouds") var clouds: CLoudModel? = CLoudModel(),
    @SerializedName("wind") var wind: WindModel? = WindModel(),
    @SerializedName("visibility") var visibility: Int? = 0,
    @SerializedName("pop") var pop: Double? = 0.0,
    @SerializedName("sys") var sys: HourlySysModel? = HourlySysModel(),
    @SerializedName("dt_txt") var dtTxt: String? = null,
): ResultList

data class HourlySysModel(
    @SerializedName("pod") var pod: String? = null,
)