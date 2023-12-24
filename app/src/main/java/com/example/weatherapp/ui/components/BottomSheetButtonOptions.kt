package com.example.weatherapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.model.List2
import com.example.weatherapp.model.WeekResult
import com.example.weatherapp.ui.theme.LightColorPalette
import com.example.weatherapp.ui.theme.Shapes
import com.example.weatherapp.ui.theme.lineColor

@Composable
fun BSheetOptions(
    weeklyResult: WeekResult,
    sheetContent: @Composable (List2) -> Unit,
) {
    var optionButtonsChosen by remember { mutableStateOf(0) }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LightColorPalette.surfaceVariant)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.padding_small))
        ) {
            val optionButtons = listOf("Hourly Forecast", "Weekly Forecast")
            for (optionButtonIndex in optionButtons.indices) {
                val isOptionButtonsChosen = optionButtonsChosen == optionButtonIndex
                Button(
                    onClick = {
                        optionButtonsChosen = optionButtonIndex
                    },
                    shape = Shapes.extraSmall,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isOptionButtonsChosen) LightColorPalette.secondary else Color.Transparent,
                    ),
                    modifier = Modifier.widthIn(max = 150.dp),
                    border = BorderStroke(
                        width = 1.dp, color = LightColorPalette.secondary
                    )
                ) {
                    Text(
                        text = optionButtons[optionButtonIndex],
                        color = if (isOptionButtonsChosen) LightColorPalette.onSecondary else LightColorPalette.onSurfaceVariant,
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(0.dp)
                    )
                }
            }
        }

        Divider(
            color = lineColor,
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small))
        )
        if (optionButtonsChosen == 1) {
            weeklyTabList(weeklyResult = weeklyResult, sheetContent = sheetContent)
        }
        if (optionButtonsChosen == 0) {
            hourlyTabList()
        }
    }
}