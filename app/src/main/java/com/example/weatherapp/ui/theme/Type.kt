package com.example.weatherapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

val Manjari = FontFamily(
    Font(R.font.manjari_regular, FontWeight.Normal),
    Font(R.font.manjari_bold, FontWeight.Bold)
)
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = Manjari,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Manjari,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Manjari,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    )
)