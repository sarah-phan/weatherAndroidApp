package com.example.weatherapp.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat

class Utils{
    companion object{
        fun timestampToHumanDate(timestamp: Long,format:String): String {
            val sdf = SimpleDateFormat(format)
            return sdf.format(timestamp*1000)
        }
    }
}