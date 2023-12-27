package com.example.weatherapp.ui.screen.HomeScreenHourly

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.model.HourlyList
import com.example.weatherapp.ui.theme.Shapes
import com.example.weatherapp.ui.theme.lineColor

@Composable
fun ConditionDetail(hourlyDetailSelected: HourlyList) {
    val contentConditionDetail: ArrayList<String> = arrayListOf()
    hourlyDetailSelected.main.let { conditionItem ->
        contentConditionDetail.add(conditionItem?.temp.toString())
    }
    hourlyDetailSelected.weather.forEach { conditionItem ->
        contentConditionDetail.add(conditionItem.main ?: "")
        contentConditionDetail.add(conditionItem.description ?: "")
    }
    val subTitleConditionDetail = listOf(
        R.string.temperature,
        R.string.main_condition,
        R.string.condition_description,
    )
    val combinedList = contentConditionDetail.zip(subTitleConditionDetail)

    Box(
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_small),
                vertical = dimensionResource(id = R.dimen.padding_medium)
            )
            .border(
                border = BorderStroke(2.dp, lineColor),
                shape = Shapes.medium
            )
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            Text(
                text = "Condition Detail",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(
                    bottom = dimensionResource(id = R.dimen.padding_extra_small)
                )
            )
            combinedList.forEach { (content, subtitle) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = dimensionResource(id = R.dimen.padding_extra_small)
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = stringResource(id = subtitle),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "${content}".replaceFirstChar { it.uppercase() },
                        style = TextStyle(
                            fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                            fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                        ),
                        modifier = Modifier.padding(
                            top = 3.dp
                        )
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun ConditionDetailPreview() {
//    val weather1 = WeatherModel(
//        id = 804, main = "Clouds", description = "overcast clouds", icon = "04n"
//    )
//    val weather2 = WeatherModel(
//        id = 804, main = "Clouds", description = "overcast clouds", icon = "04d"
//    )
//    // Create a list of WeatherModel objects
//    val weatherList = ArrayList(listOf(weather1, weather2))
//    ConditionDetail(
//        hourlyDetailSelected = HourlyList(
//            dt = 1703577600,
//            main = MainModel(
//                temp = 9.29,
//                feels_like = 9.29,
//                temp_min = 9.29,
//                temp_max = 10.6,
//                pressure = 1023.0,
//                sea_level = 1023.0,
//                grnd_level = 1022.0,
//                humidity = 87.0,
//                temp_kf = -1.31
//            ),
//            weather = weatherList,
//            clouds = CLoudModel(all = 0),
//            wind = WindModel(speed = 0.59, deg = 82, gust = 0.78),
//            visibility = 10000,
//            pop = 0.0,
//            sys = HourlySysModel(pod = "n"),
//            dtTxt = "2023-12-26 08:00:00"
//        ),
//    )
//}