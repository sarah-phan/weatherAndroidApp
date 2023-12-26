package com.example.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.TimeZone

class Utils{
    companion object{
        fun timestampToHumanDate(timestamp: Long,format:String): String {
            val sdf = SimpleDateFormat(format)
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            return sdf.format(timestamp*1000)
        }
    }
}