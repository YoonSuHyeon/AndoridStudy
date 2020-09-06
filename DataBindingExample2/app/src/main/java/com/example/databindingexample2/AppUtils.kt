package com.example.databindingexample2

import java.text.DateFormat
import java.text.SimpleDateFormat

object AppUtils {

    fun getDate(dateString: String?): String? {
        return try {
            val format1 =
                SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
            val date = format1.parse(dateString)
            val sdf: DateFormat = SimpleDateFormat("MMM d, yyyy")
            sdf.format(date)
        } catch (ex: Exception) {
            ex.printStackTrace()
            "xx"
        }
    }

    fun getTime(dateString: String?): String? {
        return try {
            val format1 =
                SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
            val date = format1.parse(dateString)
            val sdf: DateFormat = SimpleDateFormat("h:mm a")
            sdf.format(date)
        } catch (ex: Exception) {
            ex.printStackTrace()
            "xx"
        }
    }
}