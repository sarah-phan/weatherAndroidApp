package com.example.weatherapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

val DarkColorPalette = darkColorScheme(
    primary = theme_dark_primary,
    onPrimary = theme_dark_onPrimary,
    primaryContainer = theme_dark_primaryContainer,
    onPrimaryContainer = theme_dark_onPrimaryContainer,
    secondary = theme_dark_secondary,
    onSecondary = theme_dark_onSecondary,
    secondaryContainer = theme_dark_secondaryContainer,
    onSecondaryContainer = theme_dark_onSecondaryContainer,
    tertiary = theme_dark_tertiary,
    onTertiary = theme_dark_onTertiary,
    tertiaryContainer = theme_dark_tertiaryContainer,
    onTertiaryContainer = theme_dark_onTertiaryContainer,
    surface = theme_dark_surface,
    onSurface = theme_dark_onSurface,
    error = theme_dark_error,
    onError = theme_dark_onError,
    surfaceVariant = theme_dark_surfaceContainer,
    onSurfaceVariant = theme_dark_onSurfaceContainer,
    outline = theme_dark_outline
)

val LightColorPalette = lightColorScheme(
    primary = theme_light_primary,
    onPrimary = theme_light_onPrimary,
    primaryContainer = theme_light_primaryContainer,
    onPrimaryContainer = theme_light_onPrimaryContainer,
    secondary = theme_light_secondary,
    onSecondary = theme_light_onSecondary,
    secondaryContainer = theme_light_secondaryContainer,
    onSecondaryContainer = theme_light_onSecondaryContainer,
    tertiary = theme_light_tertiary,
    onTertiary = theme_light_onTertiary,
    tertiaryContainer = theme_light_tertiaryContainer,
    onTertiaryContainer = theme_light_onTertiaryContainer,
    surface = theme_light_surface,
    onSurface = theme_light_onSurface,
    error = theme_light_error,
    onError = theme_light_onError,
    surfaceVariant = theme_light_surfaceContainer,
    onSurfaceVariant = theme_light_onSurfaceContainer,
    outline = theme_light_outline
)
@Composable
fun WeatherAppTheme(darktheme: Boolean= isSystemInDarkTheme(),content: @Composable() ()->Unit){
    val colors = if(darktheme){
        DarkColorPalette
    }
    else{
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}