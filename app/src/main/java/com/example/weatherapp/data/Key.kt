package com.example.weatherapp.data

class Key{
    companion object{
        val permissions = arrayOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
        )
        // API key for OpenWeatherAPI
        const val APIKey = "8074036093edd66c5646ad04416c0aa4"
    }
}