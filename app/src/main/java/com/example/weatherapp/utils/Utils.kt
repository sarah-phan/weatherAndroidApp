package com.example.weatherapp.utils

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import java.util.Date

class Utils {
    companion object {
        fun timestampToHumanDate(timestamp: Long, format: String): String {
            val sdf = SimpleDateFormat(format)
            sdf.timeZone = TimeZone.getDefault()
            return sdf.format(Date(timestamp * 1000))
        }
    }
}