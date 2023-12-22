package com.example.weatherapp.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.model.List2
import com.example.weatherapp.model.WeatherResult
import com.example.weatherapp.model.WeekResult
import com.example.weatherapp.ui.theme.LightColorPalette
import com.example.weatherapp.utils.Utils.Companion.timestampToHumanDate
import kotlin.math.roundToInt

@Composable
fun HomeScreen(weatherResult: WeatherResult, weeklyResult: WeekResult){

    Scaffold(weatherResult,weeklyResult)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scaffold(weatherResult: WeatherResult,weeklyResult:WeekResult){
    BottomSheetScaffold(
        sheetContainerColor = LightColorPalette.surfaceVariant,
        sheetContent = {
            BSheet(weeklyResult)
        },
        scaffoldState = rememberBottomSheetScaffoldState(),
        sheetPeekHeight = 250.dp,
        sheetDragHandle = {
                          Surface(modifier=Modifier.fillMaxWidth(), color = LightColorPalette.secondary) {
                          }
        },
//        topBar = {
//            //TopBar()
//            Text(text = "${weeklyResult.city.coord?.lat}/${weeklyResult.city.coord?.lon}")
//        }
    ) {
        Image(painterResource(id = R.drawable.bg_light), contentDescription ="",
            modifier = Modifier.fillMaxHeight(), contentScale = ContentScale.Crop)
    }
}

@Composable
fun BSheet(weeklyResult: WeekResult){
    val periods = listOf("6","12","18","24")
    val details = listOf("Max Temperature","Min Temperature","Rain","Humidity","Visibility","Cloud"
        ,"Snow probability","Ice probability","Wind direction","Wind gust","Wind speed")
    Column(
        verticalArrangement = Arrangement.Center,

        modifier = Modifier
            .fillMaxWidth()
            .background(color = LightColorPalette.surfaceVariant)
            .verticalScroll(rememberScrollState())
    ){

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            Button(
                onClick = {

                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                ) ) {
                Text(text ="Hourly Forecast",color = Color.Black)
            }
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(text ="Weekly Forecast",color = Color.Black)
            }
        }
        Divider(
            color = Color.Gray, thickness = 1.dp
        )
        var selectedIndex by remember{ mutableIntStateOf(0) }
        weeklyResult.list.let{ listForecast->
            if(listForecast.size >0){
                LazyRow(modifier= Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)){
                    itemsIndexed(listForecast){index,item->
                        var day = ""
                        var time = ""
                        item.dt.let{
                                dateTime->
                            day = if(dateTime == null) "00"
                            else timestampToHumanDate(dateTime.toLong(),"EEE")
                            time = if(dateTime == null) "00"
                            else timestampToHumanDate(dateTime.toLong(),"M/dd")
                        }
                        Box(
                            modifier = Modifier
                                .height(100.dp)
                                .background(
                                    color = if (index == selectedIndex) LightColorPalette.primary else LightColorPalette.primaryContainer,
                                    shape = RoundedCornerShape(50.dp)
                                )
                                .selectable(
                                    selected = item.dt==selectedIndex,
                                    onClick = {
                                        if(selectedIndex != index)
                                            selectedIndex  = index
                                    }
                                )
                        ){
                            Text(text="${day.uppercase()}\r\n$time", textAlign = TextAlign.Center,modifier = Modifier
                                .align(Alignment.Center)
                                .padding(5.dp), color = White)
                        }

                    }
                }
                LazyColumn(modifier= Modifier
                    .padding(20.dp)
                    .height(290.dp),
                ){
                    items(items = periods){item->
                        PeriodData(period = item,  listForecast[selectedIndex])
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .border(BorderStroke(2.dp, Color.Gray), shape = RoundedCornerShape(20.dp)),
                ) {
                    Text(
                        text = "Details",
                        fontWeight = FontWeight.SemiBold ,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp),
                        color = LightColorPalette.onSurface )
                    LazyColumn(modifier= Modifier
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                        .height(380.dp), ){
                        itemsIndexed(items = details){index,item->
                            Row(modifier = Modifier.padding(vertical = 5.dp)){
                                Text(modifier = Modifier.width(200.dp),
                                    color = LightColorPalette.onSurface,
                                    text= item,
                                    textAlign = TextAlign.Start
                                )
                                var string = ""
                                val details2 = listOf("Max Temperature","Min Temperature","Rain","Humidity","Visibility","Cloud","Snow probability","Ice probability","Wind direction","Wind gust","Wind speed")

                                var selectedData = listForecast[selectedIndex]
                                when(index){
                                    0 ->
                                        string = "${selectedData.temp?.max?.roundToInt()}°"

                                    1 ->
                                        string = "${selectedData.temp?.min?.roundToInt()}°"

                                    2 ->
                                        string = "${(selectedData.rain?.roundToInt())}%"

                                    3 ->
                                        string = "${selectedData.humidity?.roundToInt()}%"

                                    4 ->
                                        string = "${(selectedData.visibility?.roundToInt())}°"

                                    5 ->
                                        string = "${(selectedData.clouds)}%"

                                    6 ->
                                        string = "${(selectedData.snow)}%"

                                    7 ->
                                        string = "${(selectedData.ice)}%"

                                    8 ->
                                        string = "${selectedData.deg?.roundToInt()}°${toDirection(selectedData.deg)}"

                                    9 ->
                                        string = "${selectedData.gust} km/h"

                                    10 ->
                                        string = "${selectedData.speed} km/h"

                                }
                                Text(modifier = Modifier.padding(horizontal = 15.dp),
                                    color = LightColorPalette.onSurface,
                                    text= string,
                                    textAlign = TextAlign.End,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                }
            }
        }

    }
}

@Composable
fun PeriodData(period: String,infor: List2){
    var temp:Int? =0
    var feels_like:Int? =0
    when (period) {
        "6h" -> {
            infor.let{ item->
                temp = item.temp?.morn?.roundToInt()
                feels_like = item.feel?.morn?.roundToInt()
            }
        }
        "12h" -> {
            infor.let{ item->
                temp = item.temp?.day?.roundToInt()
                feels_like = item.feel?.day?.roundToInt()
            }
        }
        "18h" -> {
            infor.let{ item->
                temp = item.temp?.eve?.roundToInt()
                feels_like = item.feel?.eve?.roundToInt()
            }
        }
        else -> {
            infor.let{ item->
                temp = item.temp?.night?.roundToInt()
                feels_like = item.feel?.night?.roundToInt()
            }
        }
    }

    Row(){
        Box(
            modifier = Modifier
                .width(50.dp)
                .padding(top = 15.dp)
                .background(color = LightColorPalette.tertiary, shape = RoundedCornerShape(2.dp))
        ){
            Text(text ="${period}h",textAlign = TextAlign.Center,modifier = Modifier
                .align(Alignment.Center)
                .padding(6.dp), color = White)
        }
        Box(){
            Text(
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 50.dp, vertical = 6.dp),
                color = LightColorPalette.onSurface,
                text= buildAnnotatedString {
                    append("Temperature  ")
                    withStyle(style =SpanStyle( fontWeight = FontWeight.Bold)){
                        append("${temp}° \r\n")
                    }
                    append("Feels like  ")
                    withStyle(style =SpanStyle( fontWeight = FontWeight.Bold)){
                        append("${feels_like}° \r\n")
                    }
                },
            )

        }
    }
}

@Composable
fun TopBar(){
    Surface(modifier = Modifier.fillMaxWidth(),color= Color.Transparent) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(painterResource(id = R.drawable.ic_search ) , contentDescription ="" )
        }
    }
}

fun  toDirection(degree: Double?):String{
    val directions = arrayListOf("N","NNE","NE","ENE","E","ESE","SE","SSE","S","SSW","SW","WSW","W","WNW","NW","NNW","N")
    if (degree != null) {
        return directions[(degree.div(22.5).toInt())]
    }
    return ""
}