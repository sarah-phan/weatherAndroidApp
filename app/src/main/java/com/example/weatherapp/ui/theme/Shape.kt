package com.example.weatherapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(10.dp),
    small = RoundedCornerShape(20.dp),
    medium = RoundedCornerShape(35.dp),
)

val bottomSheetShape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)