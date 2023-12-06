package com.example.weatherapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(18.dp),
    large = RoundedCornerShape(0.dp)
)

val bottomShape = Shapes(medium = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp))
val searchShape = Shapes(medium = RoundedCornerShape(12.dp))