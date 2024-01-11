package com.example.weatherapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.model.AirPollutionList
import com.example.weatherapp.ui.theme.Shapes
import kotlin.math.roundToInt

@Composable
fun AirPollutionBox(
    airPollutionForecastResultSelected: AirPollutionList,
) {
    var airQualityvalue: Int
    var co: Int
    var no: Int
    var no2: Int
    var o3: Int
    var so2: Int
    var pm2_5: Int
    var pm10: Int
    var nh3: Int
    var airQualityDesc = ""
    var isExpanded by remember {
        mutableStateOf(false)
    }

    airPollutionForecastResultSelected.main.let {
        airQualityvalue = it.aqi!!
    }
    when (airQualityvalue) {
        1 -> airQualityDesc = "Good"
        2 -> airQualityDesc = "Fair"
        3 -> airQualityDesc = "Moderate"
        4 -> airQualityDesc = "Poor"
        5 -> airQualityDesc = "Very Poor"
    }
    airPollutionForecastResultSelected.componenets.let {
        co = it.co!!.roundToInt()
        no = it.no!!.roundToInt()
        no2 = it.no2!!.roundToInt()
        o3 = it.o3!!.roundToInt()
        so2 = it.so2!!.roundToInt()
        pm2_5 = it.pm2_5!!.roundToInt()
        pm10 = it.pm10!!.roundToInt()
        nh3 = it.nh3!!.roundToInt()
    }

    var sliderPosition by remember {
        mutableStateOf(airQualityvalue.toFloat())
    }
    Box(
        modifier = Modifier.border(
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline), shape = Shapes.medium
        ),
    ) {
        Column(
        ) {
            Text(
                text = "Air Quality",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.padding_small),
                    start = dimensionResource(id = R.dimen.padding_small),
                    end = dimensionResource(id = R.dimen.padding_small),
                    bottom = dimensionResource(id = R.dimen.padding_extra_small)
                )
            )
            Text(
                text = "${airQualityvalue} - ${airQualityDesc}",
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_small),
                ),
            )
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                valueRange = 1f..5f,
                steps = 3,
                enabled = false,
                modifier = Modifier.padding(
                    dimensionResource(id = R.dimen.padding_extra_small),
                )
            )
            Divider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.outline
            )

            if(isExpanded){
                Column(
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.padding_small)
                    )
                ) {
                    listOf(
                        "CO" to co,
                        "NO" to no,
                        "NO2" to no2,
                        "O3" to o3,
                        "SO2" to so2,
                        "PM2.5" to pm2_5,
                        "PM10" to pm10,
                        "NH3" to nh3
                    ).forEach{(label, value) ->
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 80.dp,
                                    end = 80.dp,
                                    top = dimensionResource(
                                        id = R.dimen.padding_extra_small
                                    )
                                )
                        ){
                            Text(
                                text = label,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "$value",
                                style = TextStyle(
                                    fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                                    fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                                ),
                            )
                        }
                    }
                }
            }

            Text(
                text = if(isExpanded) "Show less" else "Show more...",
                style = TextStyle(
                    fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                    fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                ),
                modifier = Modifier
                    .clickable { isExpanded = !isExpanded }
                    .padding(dimensionResource(id = R.dimen.padding_small))
            )
        }

    }

}