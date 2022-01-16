package com.example.weatherapp.utils

import android.provider.ContactsContract
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

object Constants {

    const val API_KEY = "93cabf29078d894ede46b4b842d2afa7"
    const val BASE_URL = "https://api.openweathermap.org/"

 /*   fun apiRequest(lat:String,lng:String):String{
        var sb = StringBuilder(BASE_URL)
        sb.append("/lat =${lat} & lon=${lng} & APPID =${API_KEY} & units = metric")
        return sb.toString()
    }
    fun unixTimeStampDateTime(unixTimeStamp:Double):String{
        val dateFormat = SimpleDateFormat("HH:mm")
        val date = Date()
        date.time = unixTimeStamp.toLong()*1000
        return dateFormat.format(date)
    }
    fun getImage (icon : String):String {
        return " http://openweathermap.org/img/wn/&{icon}.png"
    }

    val daeNow:String
        get(){
            val dateFormat = SimpleDateFormat("dd MM yyyy HH:mm")
            val date =Date()
            return dateFormat.format(date)

    }*/
}