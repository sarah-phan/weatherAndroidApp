package com.example.weatherapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(10.dp),
    small = RoundedCornerShape(20.dp),
    medium = RoundedCornerShape(35.dp),
)

//delete
val bottomShape = Shapes(medium = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp))
val searchShape = Shapes(medium = RoundedCornerShape(12.dp))

val bottomSheetShape = Shapes(large = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))