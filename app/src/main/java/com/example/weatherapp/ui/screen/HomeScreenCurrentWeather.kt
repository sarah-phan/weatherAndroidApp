package com.example.weatherapp.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.model.AirPollutionList
import com.example.weatherapp.model.WeatherResult
import com.example.weatherapp.ui.components.AirPollutionBox
import com.example.weatherapp.ui.components.DataUiBox
import com.example.weatherapp.ui.components.SunriseSunsetUiBox
import com.example.weatherapp.ui.components.WindDirection
import com.example.weatherapp.ui.theme.Shapes
//import com.example.weatherapp.ui.theme.lineColor
import com.example.weatherapp.utils.Utils
import kotlin.math.roundToInt

@Composable
fun HomeScreenCurrentWeather(
    darkTheme: Boolean,
    weatherData: WeatherResult,
    airPollutionCurrentResultSelected: AirPollutionList
){
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

    weatherData.main.let {
        contentTempDetail.add("${it?.feels_like?.roundToInt()}°")
        contentTempDetail.add("${it?.temp_max?.roundToInt()}°")
        contentTempDetail.add("${it?.temp_min?.roundToInt()}°")

        contentConditionDetail.add("${it?.temp?.roundToInt()}°")
    }
    weatherData.weather?.firstOrNull()?.main?.let { contentConditionDetail.add(it) }
    weatherData.weather?.firstOrNull()?.description?.let { contentConditionDetail.add(it) }
    weatherData.rain.let {
        contentAtmosphericDetail.add("${it?.d1h?.roundToInt()}mm")
    }
    weatherData.main.let {
        contentAtmosphericDetail.add("${it?.humidity?.roundToInt()}%")
    }
    contentAtmosphericDetail.add("${weatherData.clouds?.all}%")

    contenWindDetail.add("${weatherData.wind?.deg}°${WindDirection(degree = weatherData.wind?.deg?.toDouble())}")
    contenWindDetail.add("${weatherData.wind?.gust?.roundToInt()} mps")
    contenWindDetail.add("${weatherData.wind?.speed?.roundToInt()} mps")

    contentOtherDetail.add("${weatherData.visibility}m")
    contentOtherDetail.add("${weatherData.snow?.d1h?.roundToInt()}%")

    val sunriseValue = weatherData.sys?.sunrise?.let {
        it?.toLong()?.let { it1 ->
            Utils.timestampToHumanDate(
                it1, "HH:mm"
        )
        }
    }
    val sunsetValue = weatherData.sys?.sunset?.let {
        Utils.timestampToHumanDate(
        it.toLong(), "HH:mm"
        )
    }

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
        DataUiBox(
            title = "Condition",
            subTitle = subTitleConditionDetail,
            content = contentConditionDetail,
        )

        //Temperature
        DataUiBox(
            title = "Temperature",
            subTitle = subTitleTemp,
            content = contentTempDetail,
        )

        //Atmospheric
        DataUiBox(
            title = "Atmospheric Moisture",
            subTitle = subTitleAtmospheric,
            content = contentAtmosphericDetail,
        )

        Row(
            modifier = Modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.padding_small)
            )
        ) {
            //Sunrise
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .border(
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
                        shape = Shapes.medium
                    ),
                contentAlignment = Alignment.Center
            )
            {
                if (sunriseValue != null) {
                    SunriseSunsetUiBox(
                        title = "Sunrise",
                        content = sunriseValue,
                        imageId = if(darkTheme) R.drawable.sunrise_2 else R.drawable.sunrise_image ,
                    )
                }
            }

            //Sunset
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .border(
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
                        shape = Shapes.medium
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (sunsetValue != null) {
                    SunriseSunsetUiBox(
                        title = "Sunset",
                        content = sunsetValue,
                        imageId = if(darkTheme) R.drawable.sunset_2 else R.drawable.sunset_image
                    )
                }
            }
        }

        //Air Quality
        AirPollutionBox(airPollutionForecastResultSelected = airPollutionCurrentResultSelected)

        //Wind
        DataUiBox(
            title = "Wind",
            subTitle = subTitleWind,
            content = contenWindDetail,
        )

        //Other
        DataUiBox(
            title = "Other",
            subTitle = subTitleOther,
            content = contentOtherDetail,
        )
    }
}