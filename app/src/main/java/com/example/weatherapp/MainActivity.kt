package com.example.weatherapp

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.Key.Companion.permissions
import com.example.weatherapp.model.Coord
import com.example.weatherapp.ui.screen.HomeScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.theme.theme_light_primary
import com.example.weatherapp.viewmodel.MainViewModel
import com.example.weatherapp.viewmodel.STATE
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.coroutineScope

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var mainViewModel: MainViewModel
    private var locationRequired: Boolean = false
    override fun onResume() {
        super.onResume()
        if(locationRequired)startLocationUpdate()
    }

    override fun onPause() {
        super.onPause()
        locationCallback?.let{
            fusedLocationProviderClient?.removeLocationUpdates(it)
        }
    }
    @SuppressLint("MissingPermission")
    private fun startLocationUpdate(){
        locationCallback?.let{
            val request = LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY, 100
            ).setWaitForAccurateLocation(false)
                .setMinUpdateIntervalMillis(1000)
                .setMaxUpdateDelayMillis(100)
                .build()
            fusedLocationProviderClient?.requestLocationUpdates(
                request,
                it,
                Looper.getMainLooper()
            )
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLocation()
        initViewModel()
        setContent {
            var get = false
            var currentLocation by remember{
                mutableStateOf(Coord(0.0,0.0))
            }
            locationCallback = object: LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    for(location in p0.locations){
                        currentLocation = Coord(
                            location.latitude,
                            location.longitude
                        )
                    }
                    if(!get){
                        fetchWeatherData(mainViewModel,currentLocation)
                        get = true
                    }
                    //fetchWeatherData(mainViewModel,currentLocation)
                }
            }
            WeatherAppTheme {
                Surface(
                    color = theme_light_primary,
                    modifier = Modifier.fillMaxSize()) {
                    var locationRequired: Boolean = false
                    val launcherMultiplePermissions = rememberLauncherForActivityResult(
                        ActivityResultContracts.RequestMultiplePermissions() ){
                            permissionMap ->
                        val isGranted = permissionMap.values.reduce{
                                accepted, next -> accepted && next
                        }
                        if(isGranted){
                            locationRequired = true;
                            startLocationUpdate()
                        }
                        else{

                        }
                    }
                    LaunchedEffect(key1 = currentLocation, block ={
                        coroutineScope {
                            if(permissions.all{
                                ContextCompat.checkSelfPermission(this@MainActivity,it) == PackageManager.PERMISSION_GRANTED
                                }){
                                startLocationUpdate()
                            }
                            else{
                                launcherMultiplePermissions.launch(permissions)
                            }
                        }
                    } )
                    when (mainViewModel.state) {
                        STATE.LOADING -> {
                            LoadingScreen()
                        }
                        STATE.FAILED -> {
                            ErrorScreen(mainViewModel.errorMsg,currentLocation)
                        }
                        STATE.SUCCESS -> {
//                            HomeScreenWeekly(mainViewModel.weeklyResponse)
//                            HomeScreenHourly(mainViewModel.hourlyResponse)
                            HomeScreen(mainViewModel.weatherResponse, mainViewModel.weeklyResponse, mainViewModel.hourlyResponse, mainViewModel.airPollutionForecastResponse, mainViewModel.airPollutionCurrentResponse)
                        }
                    }
                }
            }
        }
    }
    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
    }

    private fun initLocation() {
        fusedLocationProviderClient = LocationServices
            .getFusedLocationProviderClient(this)
    }
    private fun fetchWeatherData(mainViewModel: MainViewModel, currentLocation:Coord){
        mainViewModel.state = STATE.LOADING
        mainViewModel.getWeatherResponse(currentLocation)
        mainViewModel.getWeeklyResponse(currentLocation)
        mainViewModel.getHourlyResponse(currentLocation)
        mainViewModel.getAirPollutionResponse(currentLocation)
        mainViewModel.getAirPollutionCurrentResponse(currentLocation)
        mainViewModel.state = STATE.SUCCESS
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {

}

@Composable
fun LoadingScreen(){
    return Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(color = Color.White)
    }
}

@Composable
fun ErrorScreen(msg:String,currentLocation: Coord){
    return Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text="${msg},${currentLocation.lat}/${currentLocation.lon}")
        Button(onClick = { /*TODO*/ }) {
            Text(text="Refresh")
        }
    }
}