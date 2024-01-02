package com.example.weatherapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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
import com.example.weatherapp.ui.theme.theme_light_onSurfaceContainer
import com.example.weatherapp.ui.theme.theme_light_tertiary

@Composable
fun HomeScreenMainWeatherInfor(
    weatherDataList: WeatherResult
){
    val cityName: String = weatherDataList.name.toString()
    val countryName: String = weatherDataList.sys?.country.toString()
    val weatherDescription: String? = weatherDataList.weather?.get(0)?.main
    val weatherDescriptionDetail: String? = weatherDataList.weather?.get(0)?.description
    val weatherIcon: String? = weatherDataList.weather?.get(0)?.icon
    val weatherTemperature: String? = weatherDataList.main?.temp.toString()
    val maxTemp: String? = weatherDataList.main?.temp_max.toString()
    val minTemp: String? = weatherDataList.main?.temp_min.toString()
    val image = painterResource(id = R.drawable.download)
    val windImage = painterResource(id = R.drawable.ic_wind_color)
    val winDescription: String? = weatherDataList.wind?.speed.toString()
    val keepDryImage = painterResource(id = R.drawable.keep_dry_color)
    val dryDescription: String? = weatherDataList.main?.humidity.toString()
    Image(
        painter = image,
        contentDescription = null
    )
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(40.dp)
            .fillMaxSize()
    ) {
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = cityName,
                color = theme_light_onSurfaceContainer,
                fontFamily = Manjari,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(end = 5.dp)

            )
            Text(
                text = countryName,
                color = theme_light_tertiary,
                fontFamily = Manjari,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 5.dp)
            )
        }
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (weatherTemperature != null) {
                Text(
                    text = weatherTemperature,
                    color = theme_light_onSurfaceContainer,
                    fontFamily = Manjari,
                    fontSize = 32.sp,

                )
            }
            if (weatherDescription != null) {
                Text(
                    text = weatherDescription,
                    color = theme_light_onSurfaceContainer,
                    fontFamily = Manjari,
                    fontSize = 14.sp,
                )
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Max: ${maxTemp}",
                    color = theme_light_onSurfaceContainer,
                    fontFamily = Manjari,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(end = 5.dp)
                )
                Text(
                    text = "Min: ${minTemp}",
                    color = theme_light_onSurfaceContainer,
                    fontFamily = Manjari,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 5.dp)
                )
            }
        }
        Column (
            modifier = Modifier
                .padding(top = 40.dp)
        ) {
            Row {
                AsyncImage(
                    model = "https://openweathermap.org/img/wn/${weatherIcon}.png",
                    contentDescription = null,
                    modifier = Modifier
                        .height(22.dp)
                        .width(29.dp)
                        .padding(end = 6.dp, bottom = 5.dp)
                )
                if (weatherDescriptionDetail != null) {
                    Text(
                        text = weatherDescriptionDetail,
                        color = theme_light_onSurfaceContainer,
                        fontFamily = Manjari,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(start = 5.dp)
                    )
                }
            }
            Row {
                Image(
                    painter = windImage,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 5.dp, bottom = 5.dp)
                )
                if (weatherDescriptionDetail != null) {
                    Text(
                        text = "${winDescription} mps",
                        color = theme_light_onSurfaceContainer,
                        fontFamily = Manjari,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(start = 5.dp)
                    )
                }
            }
            Row {
                Image(
                    painter = keepDryImage,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .height(22.dp).width(24.dp)
                )
                if (weatherDescriptionDetail != null) {
                    Text(
                        text = "${dryDescription} ",
                        color = theme_light_onSurfaceContainer,
                        fontFamily = Manjari,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(start = 5.dp)
                    )
                }
            }
        }
    }
}

//
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