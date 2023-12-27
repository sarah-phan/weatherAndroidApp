package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class WeatherResult(
    @SerializedName("coord") var coord: Coordinates? = Coordinates(),
    @SerializedName("weather") var weather: ArrayList<WeatherModel>? = arrayListOf(),
    @SerializedName("base") var base: String? = null,
    @SerializedName("main") var main: MainModel? = MainModel(),
    @SerializedName("visibility") var visibility: Int? = null,
    @SerializedName("wind") var wind: WindModel? = WindModel(),
    @SerializedName("clouds") var clouds: CLoudModel? = CLoudModel(),
    @SerializedName("dt") var dt: Int? = null,
    @SerializedName("sys") var sys: SysModel? = SysModel(),
    @SerializedName("timezone") var timezone: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("cod") var cod: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("snow") var snow: SnowModel? = SnowModel(),
)

data class WeatherModel(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("main") var main: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("icon") var icon: String? = null,
)

data class MainModel(
    @SerializedName("temp") var temp: Double? = null,
    @SerializedName("feels_like") var feels_like: Double? = null,
    @SerializedName("temp_min") var temp_min: Double? = null,
    @SerializedName("temp_max") var temp_max: Double? = null,
    @SerializedName("pressure") var pressure: Double? = null,
    @SerializedName("sea_level") var sea_level: Double? = null,
    @SerializedName("grnd_level") var grnd_level: Double? = null,
    @SerializedName("humidity") var humidity: Double? = null,
    @SerializedName("temp_kf") var temp_kf: Double? = null,
)

data class WindModel(
    @SerializedName("speed") var speed: Double? = null,
    @SerializedName("deg") var deg: Int? = null,
    @SerializedName("gust") var gust: Double? = null,
)

data class CLoudModel(
    @SerializedName("all") var all: Int? = null,
)

data class SysModel(
    @SerializedName("type") var type: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("sunrise") var sunrise: Int? = null,
    @SerializedName("sunset") var sunset: Int? = null,
)

data class SnowModel(
    @SerializedName("1h") var d1h: Double? = 0.0,
)