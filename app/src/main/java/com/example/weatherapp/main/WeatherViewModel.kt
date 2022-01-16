package com.example.weatherapp.main

import android.util.Log
import androidx.lifecycle.*
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.retrofit.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) :ViewModel() {

    private val _resp = MutableLiveData<Weather>()
    val weatherResp:LiveData<Weather>
    get() = _resp


    init {
        getWeather()
    }

    private fun getWeather() = viewModelScope.launch {
        repository.getWeather().let { response ->
            if (response.isSuccessful){
                _resp.postValue(response.body())
            }else{
                Log.d("TAG", "getWeather Error Response: ${response.message()}")
            }
        }
    }


/*    private val query = MutableLiveData<String>("")
    val list = query.switchMap { query->
        Pager(PagingConfig(pageSize =10)){
            WeatherPaging(query,apiService)
        }.liveData.cachedIn(viewModelScope)
    }
    fun setQuery(q:String){
        query.postValue(q)
    }*/

}