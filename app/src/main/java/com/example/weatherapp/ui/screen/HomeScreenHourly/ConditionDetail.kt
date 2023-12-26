package com.example.weatherapp.ui.screen.HomeScreenHourly

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.weatherapp.R
import com.example.weatherapp.model.HourlyList

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

    Box(modifier = Modifier){
        Column(modifier = Modifier){
            Text(
                text = "Condition Detail",
                style = MaterialTheme.typography.displaySmall
            )
            combinedList.forEach{(content, subtitle) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = stringResource(id = subtitle.toInt()),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Box(){
                        Text(
                            text = "${content}",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_thermostat_24),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}
