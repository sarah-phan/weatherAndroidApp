package com.example.weatherapp.ui.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.HomeActivity
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.theme_light_tertiary
import com.example.weatherapp.ui.theme.theme_light_primary
import com.example.weatherapp.ui.theme.ReemKufi
import com.example.weatherapp.ui.theme.Shapes
import com.example.weatherapp.ui.theme.bottomShape
import com.example.weatherapp.ui.theme.theme_light_onPrimaryContainer


@Composable
fun SplashScreen(){
    val mContext = LocalContext.current
    val intent = Intent(mContext, HomeActivity::class.java)
    val annotatedString = AnnotatedString.Builder(text="Find your weather predictions in your City")
        .apply{
            addStyle(
                SpanStyle(
                    color = theme_light_primary,
                    fontSize = 30.sp
                ), start = 0, end = 4
            )
        }
    Box(
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .padding(top=180.dp),
        contentAlignment = Alignment.TopCenter
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_weather1),
            contentDescription = ""
        )
    }
    Box(contentAlignment = Alignment.BottomCenter){

        Card(modifier = Modifier

            .clip(shape = bottomShape.medium)
            .fillMaxWidth()
            .height(350.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ){
            Column(modifier =
            Modifier
                .padding(top = 40.dp)
                .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(annotatedString.toAnnotatedString(),
                    fontFamily = ReemKufi,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text="Easy steps to predict the weather and make your day easier",
                    fontFamily = ReemKufi,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = theme_light_tertiary)
                Spacer(modifier = Modifier.height(36.dp))
                Button(

                    onClick = {
                        mContext.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = theme_light_primary,
                        contentColor = theme_light_onPrimaryContainer
                    ),
                    contentPadding = PaddingValues(horizontal = 30.dp,vertical = 10.dp),
                    modifier = Modifier.clip(shape = Shapes.medium)
                ) {
                    Text(
                        text = "Get Start",
                        fontSize = 18.sp)
                }
            }
        }
    }
}