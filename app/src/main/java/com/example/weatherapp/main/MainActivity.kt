package com.example.weatherapp.main

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.adapter.DaysAdapter
import com.example.weatherapp.adapter.HoursAdapter
import com.example.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity() : AppCompatActivity() , LifecycleOwner {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter:RecyclerView.Adapter<HoursAdapter.ViewHolder>? =null
    private var layoutManagerDays: RecyclerView.LayoutManager? = null
    private var adapterDays:RecyclerView.Adapter<DaysAdapter.ViewHolder>? =null
    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()

   @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // SET HOURS ADAPTER
       layoutManager = LinearLayoutManager(MainActivity(), LinearLayoutManager.HORIZONTAL,false)
       hours_recycler.layoutManager = layoutManager
      // adapter = HoursAdapter()
      // hours_recycler.adapter = adapter

       //SET DAYS ADAPTER
       layoutManagerDays = LinearLayoutManager(MainActivity(), LinearLayoutManager.VERTICAL,false)
       days_recycler.layoutManager = layoutManagerDays
      // adapterDays = DaysAdapter(null)
       //days_recycler.adapter = adapterDays


       viewModel.weatherResp.observe(this,{weather ->
            binding.apply {

                days_recycler.adapter = DaysAdapter(weather)
                hours_recycler.adapter = HoursAdapter(weather)

                var rise = weather.sys.sunrise
                fun fromTimestamp(rise: Long?, format:String = "K:mm a"): String? {
                    val sdf = SimpleDateFormat("h:mm a")
                    val netDate = Date(rise!! *1000L)
                   return sdf.format(netDate)
                }
                var set = weather.sys.sunset
                fun fromSunSetStamp(set: Long?,  format:String = "K:mm a"):String?{
                    val sdf = SimpleDateFormat("h:mm a")
                    val net = Date(set!! *1000L)
                    return sdf.format(net)
                }
                city.text = weather?.name
                temprature.text =weather.main.temp.toString().substring(0,2) + "°C"
                highest.text = weather.main.temp_max.toString().substring(0,2)+ "°C"
                lowest.text = weather.main.temp_min.toString().substring(0,2)+ "°C"
                sunrise.text = fromTimestamp(rise.toLong()).toString()
                sunset.text = fromSunSetStamp(set.toLong()).toString()
                humidity.text = weather.main.humidity.toString() + "%"
                wind.text = weather.wind.speed.toString()+"km/hr"
                feels.text = weather.main.feels_like.toString()+"°C"
                pre.text = weather.main.sea_level.toString()
                pressure.text = weather.main.pressure.toString()+"hPa"
                visi.text = weather.visibility.toString()+"km"
                val desc = weather.weather[0]
                description.text = "${desc.description}"
                alldescription.text = "${desc.description}"
      }
        })
    }
   /* private fun setupRecyclerView() {


        binding.hoursRecycler.apply {
            adapter = HoursAdapter()
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        viewModel.weatherResp.observe(this, { response ->

            if (response != null) {
                adapter.submitList(response)
            }
        })


    }*/
}

