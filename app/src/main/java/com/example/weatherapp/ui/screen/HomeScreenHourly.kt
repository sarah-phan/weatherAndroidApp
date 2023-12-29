package com.example.weatherapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.model.CLoudModel
import com.example.weatherapp.model.CityModel
import com.example.weatherapp.model.Coordinates
import com.example.weatherapp.model.HourlyList
import com.example.weatherapp.model.HourlyResult
import com.example.weatherapp.model.HourlySysModel
import com.example.weatherapp.model.MainModel
import com.example.weatherapp.model.RainModel
import com.example.weatherapp.model.SnowModel
import com.example.weatherapp.model.WeatherModel
import com.example.weatherapp.model.WindModel
import com.example.weatherapp.ui.components.HourlyDataUiBox
import com.example.weatherapp.ui.components.WindDirection
import kotlin.math.roundToInt


@Composable
fun HomeScreenHourly(
    hourlyFromCurrentSelected: HourlyList,
    hourlyResultData: HourlyResult,
) {
    val subTitleTemp = listOf(
        R.string.feels_like,
        R.string.max,
        R.string.min
    )
    val subTitleConditionDetail = listOf(
        R.string.temperature,
        R.string.main_condition,
        R.string.condition_description,
    )
    val subTitleAtmospheric = listOf(
        R.string.rain,
        R.string.humidity,
        R.string.cloud
    )
    val subTitleWind = listOf(
        R.string.direction,
        R.string.gust,
        R.string.speed
    )
    val subTitleOther = listOf(
        R.string.visibility,
        R.string.snow
    )
    val contentTempDetail: ArrayList<String> = arrayListOf()
    val contentConditionDetail: ArrayList<String> = arrayListOf()
    val contentAtmosphericDetail: ArrayList<String> = arrayListOf()
    val contenWindDetail: ArrayList<String> = arrayListOf()
    val contentOtherDetail: ArrayList<String> = arrayListOf()

    hourlyFromCurrentSelected.main.let {
        contentTempDetail.add("${it?.feels_like?.roundToInt()}°")
        contentTempDetail.add("${it?.temp_max?.roundToInt()}°")
        contentTempDetail.add("${it?.temp_min?.roundToInt()}°")

        contentConditionDetail.add("${it?.temp?.roundToInt()}°")
    }
    hourlyFromCurrentSelected.weather.forEach {
        contentConditionDetail.add(it?.main ?: "")
        contentConditionDetail.add(it?.description ?: "")
    }
    hourlyFromCurrentSelected.rain.let {
        contentAtmosphericDetail.add("${it?.d1h?.roundToInt()}mm")
    }
    hourlyFromCurrentSelected.main.let {
        contentAtmosphericDetail.add("${it?.humidity?.roundToInt()}%")
    }
    contentAtmosphericDetail.add("${hourlyFromCurrentSelected.clouds?.all}%")
    hourlyFromCurrentSelected.wind.let {
        contenWindDetail.add("${it?.deg}°${WindDirection(degree = it?.deg?.toDouble())}")
        contenWindDetail.add("${it?.gust?.roundToInt()} km/h")
        contenWindDetail.add("${it?.speed?.roundToInt()} km/h")
    }
    contentOtherDetail.add("${hourlyFromCurrentSelected.visibility}m")
    contentOtherDetail.add("${hourlyFromCurrentSelected.snow?.d1h?.roundToInt()}%")

    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.padding_medium)
        ),
        modifier = Modifier.padding(
            vertical = dimensionResource(id = R.dimen.padding_medium),
            horizontal = dimensionResource(id = R.dimen.padding_small)
        )
    ) {
        //Condition
        HourlyDataUiBox(
            title = "Condition",
            subTitle = subTitleConditionDetail,
            content = contentConditionDetail,
        )

        //Temperature
        HourlyDataUiBox(
            title = "Temperature",
            subTitle = subTitleTemp,
            content = contentTempDetail,
        )

        //Atmospheric
        HourlyDataUiBox(
            title = "Atmospheric Moisture",
            subTitle = subTitleAtmospheric,
            content = contentAtmosphericDetail,
        )

        //Wind
        HourlyDataUiBox(
            title = "Wind",
            subTitle = subTitleWind,
            content = contenWindDetail,
        )

        //Other
        HourlyDataUiBox(
            title = "Other",
            subTitle = subTitleOther,
            content = contentOtherDetail,
        )
    }
}


@Preview
@Composable
fun HomeScreenHourlyPreview() {
    val weather1 = WeatherModel(
        id = 804, main = "Clouds", description = "overcast clouds", icon = "04n"
    )
    val weather2 = WeatherModel(
        id = 804, main = "Clouds", description = "overcast clouds", icon = "04d"
    )
    val weatherList = ArrayList(listOf(weather1, weather2))

    val HourlyList1 = HourlyList(
        dt = 1703581200,
        main = MainModel(
            temp = 9.07,
            feels_like = 9.07,
            temp_min = 9.07,
            temp_max = 10.51,
            pressure = 1022.0,
            sea_level = 1022.0,
            grnd_level = 1022.0,
            humidity = 87.0,
            temp_kf = -1.44
        ),
        weather = weatherList,
        clouds = CLoudModel(all = 0),
        wind = WindModel(speed = 1.03, deg = 121, gust = 1.27),
        visibility = 10000,
        pop = 0.0,
        sys = HourlySysModel(pod = "n"),
        dtTxt = "2023 - 12 - 26 09:00:00"
    )

    val HourlyList2 = HourlyList(
        dt = 1703584800,
        main = MainModel(
            temp = 9.3,
            feels_like = 8.8,
            temp_min = 9.3,
            temp_max = 10.21,
            pressure = 1022.0,
            sea_level = 1022.0,
            grnd_level = 1021.0,
            humidity = 86.0,
            temp_kf = -0.91
        ),
        weather = weatherList,
        clouds = CLoudModel(all = 20),
        wind = WindModel(speed = 1.55, deg = 139, gust = 2.02),
        visibility = 10000,
        pop = 0.0,
        sys = HourlySysModel(pod = "n"),
        dtTxt = "2023 - 12 - 26 10:00:00"
    )

    val hourlyListArray = ArrayList(listOf(HourlyList1, HourlyList2))

    HomeScreenHourly(
        hourlyFromCurrentSelected = HourlyList(
            dt = 1703797200,
            main = MainModel(
                temp = 24.22,
                feels_like = 25.02,
                temp_min = 24.22,
                temp_max = 24.22,
                pressure = 1011.0,
                sea_level = 1011.0,
                grnd_level = 1010.0,
                humidity = 89.0,
                temp_kf = 0.0
            ),
            weather = weatherList,
            clouds = CLoudModel(all = 6),
            wind = WindModel(speed = 0.62, deg = 293, gust = 0.91),
            visibility = 10000,
            pop = 0.0,
            rain = RainModel(d1h = 0.0),
            snow = SnowModel(d1h = 0.0),
            sys = HourlySysModel(pod = "n"),
            dtTxt = "2023 - 12 - 28 21:00:00"
        ),
        hourlyResultData = HourlyResult(
            cod = "200", message = 0.0, cnt = 96, list = hourlyListArray, city = CityModel(
                id = 5375480,
                name = "Mountain View",
                coord = Coordinates(lat = 37.422, lon = -122.084),
                country = "US",
                population = 74066,
                timezone = -28800,
                sunrise = 1703517648,
                sunset = 1703552146
            )
        )
    )
}