package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

//data class WeeklyResult(
//    @SerializedName("cod") var cod: String? = null,
//    @SerializedName("message") var message: Double? = 0.0,
//    @SerializedName("cnt") var cnt: Int? = 0,
//    @SerializedName("list") var list: ArrayList<List> = arrayListOf(),
//    @SerializedName("city") var city: CityModel = CityModel()
//)

data class WeekResult(
    @SerializedName("cod") var cod: String? = null,
    @SerializedName("message") var message: Double? = 0.0,
    @SerializedName("cnt") var cnt: Int? = 0,
    @SerializedName("list") var list: ArrayList<List2> = arrayListOf(),
    @SerializedName("city") var city: CityModel = CityModel()
)

data class List2 (
    @SerializedName("dt") var dt: Int? = 0,
    @SerializedName("sunrise") var sunrise : Int? = 0,
    @SerializedName("sunset") var sunset:Int? = 0,
    @SerializedName("temp") var temp: TempModel? = TempModel(),
    @SerializedName("feels_like") var feel: FeelsLikeModel? = FeelsLikeModel(),
    @SerializedName("pressure") var pressure: Double? = 0.0,
    @SerializedName("humidity") var humidity: Double? = 0.0,
    @SerializedName("weather") var weather: ArrayList<WeatherModel>? = arrayListOf(),
    @SerializedName("speed") var speed: Double? = 0.0,
    @SerializedName("deg") var deg: Double? = 0.0,
    @SerializedName("gust") var gust: Double? = 0.0,
    @SerializedName("clouds") var clouds: Int? = 0,
    @SerializedName("snow") var snow: Int? = 0,
    @SerializedName("visibility") var visibility: Double? = 0.0,
    @SerializedName("pop") var pop: Double? = 0.0,
    @SerializedName("rain") var rain: Double? = 0.0,
)

data class CityModel(
    @SerializedName("id") var id: Int? = 0,
    @SerializedName("name") var name: String? = null,
    @SerializedName("coord") var coord: Coordinates? = Coordinates(),
    @SerializedName("country") var country: String? = null,
    @SerializedName("population") var population: Int? = 0,
    @SerializedName("timezone") var timezone: Int? = 0,
    @SerializedName("sunrise") var sunrise: Int? = 0,
    @SerializedName("sunset") var sunset: Int? = 0,
    )

data class List (
    @SerializedName("dt") var dt: Int? = 0,
    @SerializedName("main") var main : MainModel? = MainModel(),
    @SerializedName("weather") var weather:ArrayList< WeatherModel>? = arrayListOf(),
    @SerializedName("clouds") var clouds: CLoudModel? = CLoudModel(),
    @SerializedName("wind") var wind: WindModel? = WindModel(),
    @SerializedName("visibility") var visibility: Int? = 0,
    @SerializedName("pop") var pop: Double? = 0.0,
    @SerializedName("sys") var message: SysModel? = SysModel(),
    @SerializedName("dt_txt") var dtTxt: String? = null,
    //@SerializedName("rain") var rain: RainModel? = RainModel(),
    )

data class Coordinates (
    @SerializedName("lat") var lat: Double? = 0.0,
    @SerializedName("lon") var lon: Double? = 0.0,
)

data class TempModel(
    @SerializedName("day") var day: Double? = 0.0,
    @SerializedName("min") var min: Double? = 0.0,
    @SerializedName("max") var max: Double? = 0.0,
    @SerializedName("night") var night: Double? = 0.0,
    @SerializedName("eve") var eve: Double? = 0.0,
    @SerializedName("morn") var morn: Double? = 0.0,
)

data class FeelsLikeModel(
    @SerializedName("day") var day: Double? = 0.0,
    @SerializedName("night") var night: Double? = 0.0,
    @SerializedName("eve") var eve: Double? = 0.0,
    @SerializedName("morn") var morn: Double? = 0.0,
)