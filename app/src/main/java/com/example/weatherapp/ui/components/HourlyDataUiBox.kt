package com.example.weatherapp.ui.components

import android.util.Log
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
import com.example.weatherapp.ui.theme.Shapes
import com.example.weatherapp.ui.theme.lineColor

@Composable
fun HourlyDataUiBox(
    title: String,
    subTitle: List<Int>,
    content: List<String>,
) {
    val combinedList = content.zip(subTitle)
    Log.d("combinedList", combinedList.toString())
    Box(
        modifier = Modifier
            .border(
                border = BorderStroke(2.dp, lineColor), shape = Shapes.medium
            )
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_extra_small))
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.displayMedium,
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

@Composable
fun SunriseSunsetUiBox(){

}