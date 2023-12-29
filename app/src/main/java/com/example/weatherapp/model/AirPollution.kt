package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class AirPollutionForecastResult(
    @SerializedName("coord") var coord: Coordinates = Coordinates(),
    @SerializedName("list") var list: ArrayList<AirPollutionList> = arrayListOf()
)

data class AirPollutionCurrentResult(
    @SerializedName("coord") var coord: Coordinates = Coordinates(),
    @SerializedName("list") var list: AirPollutionList = AirPollutionList()
)

data class AirPollutionList(
    @SerializedName("main") var main: AirPollutionMain = AirPollutionMain(),
    @SerializedName("components") var componenets: Component = Component(),
    @SerializedName("dt") var dt: Int? = 0
)

data class AirPollutionMain(
    @SerializedName("aqi") var aqi: Int? = 0
)

data class Component(
    @SerializedName("co") var co: Double? = 0.0,
    @SerializedName("no") var no: Double? = 0.0,
    @SerializedName("no2") var no2: Double? = 0.0,
    @SerializedName("o3") var o3: Double? = 0.0,
    @SerializedName("so2") var so2: Double? = 0.0,
    @SerializedName("pm2_5") var pm2_5: Double? = 0.0,
    @SerializedName("pm10") var pm10: Double? = 0.0,
    @SerializedName("nh3") var nh3: Double? = 0.0,
)