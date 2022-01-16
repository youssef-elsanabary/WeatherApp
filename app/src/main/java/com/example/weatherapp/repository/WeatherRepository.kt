package com.example.weatherapp.repository

import com.example.weatherapp.retrofit.api.ApiService
import com.example.weatherapp.utils.Constants
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: ApiService) {

suspend fun getWeather() = apiService.getData("q",Constants.API_KEY)

}