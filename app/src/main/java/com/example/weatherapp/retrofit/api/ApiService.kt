package com.example.weatherapp.retrofit.api

import com.example.weatherapp.retrofit.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    //https://api.openweathermap.org/data/2.5/weather?q=Damietta&units=metric&exclude=hourly,daily&appid=93cabf29078d894ede46b4b842d2afa7
    @GET("data/2.5/weather?q=Damietta&units=metric&exclude=hourly,daily&appid=93cabf29078d894ede46b4b842d2afa7")
    suspend fun getData(
        @Query("q")q:String,
        @Query("appid")appid:String

    ):Response<Weather>
}