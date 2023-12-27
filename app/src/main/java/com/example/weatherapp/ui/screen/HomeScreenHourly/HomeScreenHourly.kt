package com.example.weatherapp.ui.screen.HomeScreenHourly

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.model.CLoudModel
import com.example.weatherapp.model.CityModel
import com.example.weatherapp.model.Coordinates
import com.example.weatherapp.model.HourlyList
import com.example.weatherapp.model.HourlyResult
import com.example.weatherapp.model.HourlySysModel
import com.example.weatherapp.model.MainModel
import com.example.weatherapp.model.WeatherModel
import com.example.weatherapp.model.WindModel


@Composable
fun HomeScreenHourly(
    hourlyDetailSelected: HourlyList,
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
    val contentTempDetail: ArrayList<String> = arrayListOf()
    val contentConditionDetail: ArrayList<String> = arrayListOf()


    hourlyDetailSelected.main.let {
        contentTempDetail.add(it?.feels_like.toString())
        contentTempDetail.add(it?.temp_max.toString())
        contentTempDetail.add(it?.temp_min.toString())

        contentConditionDetail.add(it?.temp.toString())
    }

    hourlyDetailSelected.weather.forEach { conditionItem ->
        contentConditionDetail.add(conditionItem.main ?: "")
        contentConditionDetail.add(conditionItem.description ?: "")
    }

    Column {
        //Condition
        DataUiSame(
            title = "Condition",
            subTitle = subTitleConditionDetail,
            content = contentConditionDetail,
            boxWidth = 1f
        )

        //Temperature
        DataUiSame(
            title = "Temperature",
            subTitle = subTitleTemp,
            content = contentTempDetail,
            boxWidth = 0.5f
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
        hourlyDetailSelected = HourlyList(
            dt = 1703577600,
            main = MainModel(
                temp = 9.29,
                feels_like = 9.29,
                temp_min = 9.29,
                temp_max = 10.6,
                pressure = 1023.0,
                sea_level = 1023.0,
                grnd_level = 1022.0,
                humidity = 87.0,
                temp_kf = -1.31
            ),
            weather = weatherList,
            clouds = CLoudModel(all = 0),
            wind = WindModel(speed = 0.59, deg = 82, gust = 0.78),
            visibility = 10000,
            pop = 0.0,
            sys = HourlySysModel(pod = "n"),
            dtTxt = "2023-12-26 08:00:00"
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