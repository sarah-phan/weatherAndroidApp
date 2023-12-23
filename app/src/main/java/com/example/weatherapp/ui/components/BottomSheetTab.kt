package com.example.weatherapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.LightColorPalette
import com.example.weatherapp.ui.theme.bottomSheetShape

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Scaffold(peekHeight: Dp, sheetContentChosen: @Composable () -> Unit) {
    BottomSheetScaffold(
        sheetContainerColor = LightColorPalette.surfaceVariant,
        sheetContent = {
            sheetContentChosen()
        },
        scaffoldState = rememberBottomSheetScaffoldState(),
        sheetPeekHeight = peekHeight,
        sheetShape = bottomSheetShape
    ) {
        Image(
            painterResource(id = R.drawable.bg_light),
            contentDescription = "",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.Crop,
            alpha = 0.25f
        )
    }
}