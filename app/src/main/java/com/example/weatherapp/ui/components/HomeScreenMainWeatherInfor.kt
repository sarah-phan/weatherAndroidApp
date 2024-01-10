package com.example.weatherapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherResult
import com.example.weatherapp.ui.theme.Manjari
import com.example.weatherapp.ui.theme.Screen
import com.example.weatherapp.ui.theme.theme_light_onSurfaceContainer
import com.example.weatherapp.ui.theme.theme_light_tertiary

@Composable
fun HomeScreenMainWeatherInfor(
    weatherDataList: WeatherResult,
    navController: NavController
) {
    val cityName: String = weatherDataList.name.toString()
    val countryName: String = weatherDataList.sys?.country.toString()
    val weatherDescription = weatherDataList.weather?.firstOrNull()?.main.toString()
    val weatherDescriptionDetail = weatherDataList.weather?.firstOrNull()?.description.toString()
    val weatherIcon = weatherDataList.weather?.firstOrNull()?.icon.toString()
    val weatherTemperature: String = weatherDataList.main?.temp.toString()
    val maxTemp: String = weatherDataList.main?.temp_max.toString()
    val minTemp: String = weatherDataList.main?.temp_min.toString()
    val image = painterResource(id = R.drawable.download)
    val windImage = painterResource(id = R.drawable.ic_wind_color)
    val winDescription: String = weatherDataList.wind?.speed.toString()
    val keepDryImage = painterResource(id = R.drawable.keep_dry_color)
    val dryDescription: String = weatherDataList.main?.humidity.toString()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .clickable { navController.navigate(route = Screen.Insert.route) }
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = cityName,
                color = theme_light_onSurfaceContainer,
                fontFamily = Manjari,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 30.dp, end = 5.dp)

            )
            Text(
                text = countryName,
                color = theme_light_tertiary,
                fontFamily = Manjari,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 30.dp, start = 5.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = weatherTemperature,
                color = theme_light_onSurfaceContainer,
                fontFamily = Manjari,
                fontSize = 32.sp,
            )
            Text(
                text = weatherDescription,
                color = theme_light_onSurfaceContainer,
                fontFamily = Manjari,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Max: $maxTemp",
                    color = theme_light_onSurfaceContainer,
                    fontFamily = Manjari,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(end = 5.dp)
                )
                Text(
                    text = "Min: $minTemp",
                    color = theme_light_onSurfaceContainer,
                    fontFamily = Manjari,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 5.dp)
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxWidth()
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                Column (
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AsyncImage(
                        model = "https://openweathermap.org/img/wn/${weatherIcon}.png",
                        contentDescription = null,
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp)
                    )
                    Image(
                        painter = windImage,
                        contentDescription = null,
                        modifier = Modifier
                    )
                    Image(
                        painter = keepDryImage,
                        contentDescription = null,
                        modifier = Modifier
                            .height(25.dp)
                            .width(24.dp)
                    )

                }
                Column (
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = weatherDescriptionDetail,
                        color = theme_light_onSurfaceContainer,
                        fontFamily = Manjari,
                        fontSize = 16.sp,
                        modifier = Modifier
                    )
                    Text(
                        text = "$winDescription mps",
                        color = theme_light_onSurfaceContainer,
                        fontFamily = Manjari,
                        fontSize = 16.sp,
                        modifier = Modifier
                    )
                    Text(
                        text = "$dryDescription %",
                        color = theme_light_onSurfaceContainer,
                        fontFamily = Manjari,
                        fontSize = 16.sp,
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun HomeScreenMainWeatherInforPreview() {
//    val weather1 = WeatherModel(
//        id = 501, main = "Rain", description = "moderate rain", icon = "10d"
//    )
//    val weatherList = ArrayList(listOf(weather1))
//    HomeScreenMainWeatherInfor(
//        weatherDataList = WeatherResult(
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