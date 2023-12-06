package com.example.weatherapp.ui.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.LightText
import com.example.weatherapp.ui.theme.Poppins
import com.example.weatherapp.ui.theme.ReemKufi
import com.example.weatherapp.ui.theme.searchShape


@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text ="Search for city",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            textAlign = TextAlign.Center,
            fontFamily = ReemKufi,
            color = LightText
        )
        SearchBox()
        Cards()
    }
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
                tint = LightText,
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
                Text(text = city, fontFamily = Poppins, fontSize = 14.sp, color = LightText)
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
                        tint = LightText
                    )
                    Text(
                        text = humidity,
                        fontFamily = Poppins,
                        fontSize = 12.sp,
                        color = LightText
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        painter = painterResource(R.drawable.ic_wind),
                        contentDescription = "",
                        Modifier.size(14.dp),
                        tint = LightText
                    )
                    Text(
                        text = wind,
                        fontFamily = Poppins,
                        fontSize = 12.sp,
                        color = LightText
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