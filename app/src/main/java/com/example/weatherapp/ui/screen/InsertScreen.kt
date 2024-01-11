package com.example.weatherapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.weatherapp.ui.theme.theme_light_onPrimary
import com.example.weatherapp.R
import com.example.weatherapp.model.CLoudModel
import com.example.weatherapp.model.Coordinates
import com.example.weatherapp.model.MainModel
import com.example.weatherapp.model.SnowModel
import com.example.weatherapp.model.SysModel
import com.example.weatherapp.model.WeatherModel
import com.example.weatherapp.model.WeatherResult
import com.example.weatherapp.model.WindModel
import com.example.weatherapp.ui.theme.Manjari
import com.example.weatherapp.ui.theme.Screen
import com.example.weatherapp.ui.theme.theme_light_onSurface
import com.example.weatherapp.ui.theme.theme_light_primary
import com.example.weatherapp.ui.theme.theme_light_tertiary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertScreen(
    weatherLocationData: WeatherResult,
    navController: NavController
) {
    val cityName: String? = weatherLocationData.name
    val countryName: String? = weatherLocationData.sys?.country
    val weatherIcon = weatherLocationData.weather?.firstOrNull()?.icon
    val temperature: String? = weatherLocationData.main?.temp.toString()
    val maxTemp: String? = weatherLocationData.main?.temp_max.toString()
    val minTemp: String? = weatherLocationData.main?.temp_min.toString()
    Column (
        modifier = Modifier
            .background(color = theme_light_onPrimary)
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Location",
                    color = theme_light_onSurface,
                    fontFamily = Manjari,
                    fontWeight = FontWeight.Bold
                )
                    },
            navigationIcon = { IconButton(onClick = { navController.navigate(route = Screen.Home.route) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = theme_light_primary
                    )
                }
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = theme_light_primary
                    )
                }
            }
        )
        Column (
            modifier = Modifier
                .padding(top = 35.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    if (cityName != null) {
                        Text(
                            text = cityName,
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = theme_light_onSurface
                        )
                    }
                    if (countryName != null) {
                        Text(
                            text = countryName,
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = theme_light_tertiary
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .weight(4f)
                )
                Column (horizontalAlignment = Alignment.End) {
                    Row {
                        AsyncImage(
                            model = "https://openweathermap.org/img/wn/${weatherIcon}.png",
                            contentDescription = null,
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                        )
                        if (temperature != null) {
                            Text(
                                text = temperature
                            )
                        }
                    }
                    Text(
                        text = "$maxTemp/$minTemp"
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun InsertScreenPreview() {
//    val weather1 = WeatherModel(
//        id = 501, main = "Rain", description = "moderate rain", icon = "10d"
//    )
//    val weatherList = ArrayList(listOf(weather1))
//    InsertScreen(
//        weatherLocationData = WeatherResult(
//            coord = Coordinates(
//                lon = 10.99,
//                lat = 44.34
//            ),
//            weather = weatherList,
//            base = "stations",
//            main = MainModel(
//                temp = 298.48,
//                feels_like = 298.74,
//                temp_min = 297.56,
//                temp_max = 300.05,
//                pressure = 1015.00,
//                humidity = 64.00,
//                sea_level = 1015.00,
//                grnd_level = 933.00
//            ),
//            visibility = 10000,
//            wind = WindModel(
//                speed = 0.62,
//                deg = 349,
//                gust = 1.18
//            ),
//            clouds = CLoudModel(
//                all = 100
//            ),
//            dt = 1661870592,
//            sys = SysModel(
//                type = 2,
//                id = 2075663,
//                country = "IT",
//                sunrise = 1661834187,
//                sunset = 1661882248
//            ),
//            timezone = 7200,
//            id = 3163858,
//            name = "Zocca",
//            cod = 200,
//            snow = SnowModel(
//                d1h = 3.16
//            )
//        )
//    )
//}