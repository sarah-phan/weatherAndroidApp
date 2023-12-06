package com.example.weatherapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

// delete
val ReemKufi = FontFamily(Font(R.font.reemkufi))
val Poppins = FontFamily(Font(R.font.poppins))

val Manjari = FontFamily(
    Font(R.font.manjari_regular, FontWeight.Normal),
    Font(R.font.manjari_bold, FontWeight.Bold)
)
val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = Manjari,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Manjari,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Manjari,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    )
)