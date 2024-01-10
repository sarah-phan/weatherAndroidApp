package com.example.weatherapp.ui.components

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
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
import com.example.weatherapp.ui.theme.LightColorPalette
import com.example.weatherapp.ui.theme.Manjari
import com.example.weatherapp.viewmodel.MainViewModel


@Composable
fun HomeScreenMainWeatherInfor(
    weatherDataList: WeatherResult,
    darkTheme: Boolean,
    onThemeUpdated: (Boolean)-> Unit,


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
        Row(modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
            )
            Switch(
                checked = darkTheme,
                onCheckedChange = onThemeUpdated,
            )
        }


        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = cityName,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontFamily = Manjari,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 30.dp, end = 5.dp)

            )
            Text(
                text = countryName,
                color = MaterialTheme.colorScheme.tertiary,
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
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontFamily = Manjari,
                fontSize = 32.sp,
            )
            Text(
                text = weatherDescription,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontFamily = Manjari,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(end = 5.dp)
                )
                Text(
                    text = "Min: $minTemp",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontFamily = Manjari,
                        fontSize = 16.sp,
                        modifier = Modifier
                    )
                    Text(
                        text = "$winDescription mps",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontFamily = Manjari,
                        fontSize = 16.sp,
                        modifier = Modifier
                    )
                    Text(
                        text = "$dryDescription %",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
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