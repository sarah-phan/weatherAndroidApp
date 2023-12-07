package com.example.weatherapp.ui.screen

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.weatherapp.ui.theme.LightColorPalette
import com.example.weatherapp.ui.theme.theme_light_tertiary
import com.example.weatherapp.ui.theme.Poppins
import com.example.weatherapp.ui.theme.ReemKufi
import com.example.weatherapp.ui.theme.searchShape
import com.example.weatherapp.ui.theme.theme_light_onPrimary
import com.example.weatherapp.ui.theme.theme_light_primaryContainer
import com.example.weatherapp.ui.theme.theme_light_secondary

@Composable
fun HomeScreen(){
//    Column(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text ="Search for city",
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 20.dp),
//            textAlign = TextAlign.Center,
//            fontFamily = ReemKufi,
//            color = theme_light_tertiary
//        )
//        SearchBox()
//        Cards()
//        Divider(
//            color = Color.Gray, thickness = 3.dp
//        )
//    }
    Scaffold()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(){
    var text by remember{ mutableStateOf(value="") }

    TextField(
        value = text ,
        onValueChange = {
            text = it
        },
        label = null,
        placeholder = { Text(text = "Search")},
        colors = TextFieldDefaults.textFieldColors(
            containerColor = White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp, bottom = 8.dp)
            .clip(searchShape.medium),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "",
                tint = theme_light_tertiary,
                modifier = Modifier.size(20.dp))
        }
    )
}

@Composable
fun Cards() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CardUI(
            temperature = "30oC",
            city = "Ho Chi Minh",
            resourceId = R.drawable.ic_weather2,
            humidity = "77%",
            wind = "3 km/h"
        )

        CardUI(
            temperature = "25oC",
            city = "Ha Noi",
            resourceId = R.drawable.ic_weather2,
            humidity = "45%",
            wind = "4 km/h"
        )
    }
}

@Composable
fun CardUI(temperature: String,city:String,resourceId:Int,humidity:String,wind:String) {
    Box(
        modifier = Modifier
            .padding(10.dp))
    {
        Card(modifier = Modifier.padding(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = White),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp))
        {
            Column(modifier = Modifier.padding(10.dp)){
                Text(text = city, fontFamily = Poppins, fontSize = 14.sp, color = theme_light_tertiary)
                Spacer(modifier = Modifier.height(0.dp))
                Text(
                    text = getTemperature(temp = temperature).toAnnotatedString(),
                    fontFamily = Poppins,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(0.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(resourceId),
                        contentDescription = "",
                        Modifier.size(14.dp),
                        tint = theme_light_tertiary
                    )
                    Text(
                        text = humidity,
                        fontFamily = Poppins,
                        fontSize = 12.sp,
                        color = theme_light_tertiary
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        painter = painterResource(R.drawable.ic_wind),
                        contentDescription = "",
                        Modifier.size(14.dp),
                        tint = theme_light_tertiary
                    )
                    Text(
                        text = wind,
                        fontFamily = Poppins,
                        fontSize = 12.sp,
                        color = theme_light_tertiary
                    )
                }
            }


        }
        Image(
            painter = painterResource(id = R.drawable.ic_weather2),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(50.dp)
                .offset(y = (-10).dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scaffold(){
    BottomSheetScaffold(
        sheetContent = {
            BSheet()
        },
        scaffoldState = rememberBottomSheetScaffoldState(),
        sheetPeekHeight = 250.dp,
        sheetContainerColor = theme_light_onPrimary,
    ) {
//        Column(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text ="Search for city",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 20.dp),
//                textAlign = TextAlign.Center,
//                fontFamily = ReemKufi,
//                color = theme_light_tertiary
//            )
//            SearchBox()
//            Cards()
//            Divider(
//                color = Color.Gray, thickness = 3.dp
//            )
        //}
        Image(painterResource(id = R.drawable.bg_light), contentDescription ="",
            modifier = Modifier.fillMaxHeight(), contentScale = ContentScale.Crop)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BSheet(){
    val list = listOf("MON","TUE","WED","THU","FRI","SAT","SUN")
    val periods = listOf("6","12","18","24")
    val details = listOf("Max Temperature","Min Temperature","Rain","Humidity","Visibility","Cloud"
        ,"Snow probability","Ice probability","Wind direction","Wind gust","Wind speed")
    Column(
        verticalArrangement = Arrangement.Center,

        modifier = Modifier
            .fillMaxWidth()
            .background(color = LightColorPalette.secondary)
            .verticalScroll(rememberScrollState())
    ){

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            Button(
                onClick = { /*TODO*/ },
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
        LazyRow(modifier= Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly){
            items(items = list){item->
                WeeklyBox(day = item,date = "2/12")
            }
        }
        LazyColumn(modifier= Modifier
            .padding(20.dp)
            .height(290.dp),
             ){
            items(items = periods){item->
                PeriodData(period = item,temp = "10", feels = "10")
            }
        }
        val card = RoundedCornerShape(20.dp)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .border(BorderStroke(2.dp, Color.Gray), shape = RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(
                containerColor = LightColorPalette.secondary
            )
        ) {
            Text(
                text = "Details",
                fontWeight = FontWeight.SemiBold ,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp))
            LazyColumn(modifier= Modifier
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .height(380.dp), ){
                items(items = details){item->
//                    Text(
//                        modifier = Modifier
//                            .padding(vertical = 5.dp),
//                        color = LightColorPalette.onSurface,
//                        text= buildAnnotatedString {
//                            append("$item       ")
//                            withStyle(style =SpanStyle(fontWeight = FontWeight.Bold)){
//                                append("32°")
//                            }
//                        },
//                    )
                    Row(modifier = Modifier.padding(vertical = 5.dp)){
                        Text(modifier = Modifier.width(200.dp),
                            color = LightColorPalette.onSurface,
                            text= "$item",
                            textAlign = TextAlign.Start
                        )
                        Text(modifier = Modifier.padding(horizontal = 20.dp),
                            color = LightColorPalette.onSurface,
                            text= "32°",
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

        }
    }
}
@Composable
fun WeeklyBox(day: String,date:String){
    Box(
        modifier = Modifier
            .height(100.dp)
            .background(
                color = LightColorPalette.primaryContainer,
                shape = RoundedCornerShape(50.dp)
            )
    ){
        Text(text="$day\r\n$date", textAlign = TextAlign.Center,modifier = Modifier
            .align(Alignment.Center)
            .padding(6.dp), color = White)
//        Icon(
//            painterResource(id = R.drawable.ic_weather1),
//            modifier = Modifier.align(Alignment.Center).size( 10.dp),
//            contentDescription = "")
//        Text(text="$date",modifier = Modifier.align(Alignment.BottomCenter))

    }
}
@Composable
fun PeriodData(period: String,temp:String,feels:String){
    Row(){
        Box(
            modifier = Modifier
                .width(50.dp)
                .padding(top = 15.dp)
                .background(color = LightColorPalette.tertiary, shape = RoundedCornerShape(2.dp))
        ){
            Text(text ="$period h",textAlign = TextAlign.Center,modifier = Modifier
                .align(Alignment.Center)
                .padding(6.dp), color = White)
        }
        Box(){
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 50.dp, vertical = 6.dp),
                color = LightColorPalette.onSurface,
                text= buildAnnotatedString {
                    append("Temperature  ")
                    withStyle(style =SpanStyle( fontWeight = FontWeight.Bold)){
                        append("$temp° \r\n")
                    }
                    append("Feels like  ")
                    withStyle(style =SpanStyle( fontWeight = FontWeight.Bold)){
                        append("$feels° \r\n")
                    }
                },
            )

        }
    }
}

fun getTemperature(temp: String): AnnotatedString.Builder{
    val annotatedString = AnnotatedString.Builder(temp)
        .apply {
            addStyle(
                SpanStyle(
                    fontSize = 18.sp,
                    baselineShift = BaselineShift.Superscript
                ), start = 2, end = 3
            )
        }
    return annotatedString
}