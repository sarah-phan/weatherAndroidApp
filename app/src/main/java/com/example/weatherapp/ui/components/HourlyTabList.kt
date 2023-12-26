package com.example.weatherapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.model.HourlyList
import com.example.weatherapp.ui.theme.LightColorPalette
import com.example.weatherapp.ui.theme.Shapes
import com.example.weatherapp.utils.Utils
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun HourlyTabList(
    hourlyDataList: ArrayList<HourlyList>,
    selectedIndex: MutableState<Int>,
) {
    var hourlyFromCurrent: ArrayList<HourlyList> = ArrayList()
    val sdf = SimpleDateFormat("dd/MM/yyyy HH")
    val currentDate = sdf.format(Date())
    var shouldAddItems = false

    hourlyDataList.forEachIndexed() { index, item ->
        val dateHourDetail = if (item.dt == null) "00"
        else Utils.timestampToHumanDate(item.dt!!.toLong(), "dd/MM/yyyy HH")
        if (currentDate == dateHourDetail || shouldAddItems) {
            hourlyFromCurrent.add(item)
            shouldAddItems = true
        }
    }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 0.dp,
                start = dimensionResource(id = R.dimen.padding_small),
            ),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(hourlyFromCurrent) { index, item ->
            val dateHourDetail = if (item.dt == null) "00"
            else Utils.timestampToHumanDate(item.dt!!.toLong(), "dd/MM/yyyy HH")
            val hourAndMinuteDetail = if (item.dt == null) "00"
            else Utils.timestampToHumanDate(item.dt!!.toLong(), "HH:mm")
            val dateDetail = if (item.dt == null) "00"
            else Utils.timestampToHumanDate(item.dt!!.toLong(), "dd/MM")
            val weatherIcon = item.weather?.firstOrNull()?.icon
            val weatherMainInformation = item.weather?.firstOrNull()?.main
            var timeShow = hourAndMinuteDetail

            if (dateHourDetail == currentDate) {
                timeShow = "NOW"
            }
            Box(
                modifier = Modifier
                    .height(140.dp)
                    .width(60.dp)
                    .background(
                        color = if (index == selectedIndex.value) LightColorPalette.primary else LightColorPalette.primaryContainer,
                        shape = Shapes.large
                    )
                    .selectable(selected = item.dt == selectedIndex.value, onClick = {
                        if (selectedIndex.value != index) selectedIndex.value = index
                    })
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = dimensionResource(id = R.dimen.padding_small))
                ) {
                    Text(
                        text = "${dateDetail.uppercase()}",
                        color = LightColorPalette.onPrimaryContainer,
                        style = MaterialTheme.typography.titleSmall,
                    )
                    Text(
                        text = "${timeShow}",
                        color = LightColorPalette.onPrimaryContainer,
                        style = MaterialTheme.typography.titleSmall
                    )
                    if (weatherIcon != null) {
                        AsyncImage(
                            model = "https://openweathermap.org/img/wn/${weatherIcon}@2x.png",
                            contentDescription = weatherMainInformation,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1.6f)
                                .scale(1.7f)
                        )
                    }
                }
            }
        }
    }
}
