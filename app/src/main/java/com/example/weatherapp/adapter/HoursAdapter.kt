package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.retrofit.Weather
import java.text.SimpleDateFormat
import java.util.*


class HoursAdapter( var weather: Weather) :RecyclerView.Adapter<HoursAdapter.ViewHolder>() {

       companion object DIFF_UTIL: DiffUtil.ItemCallback<Weather>() {
            override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem.main == newItem.main
            }
       }
  //  private lateinit var binding: DaysListItemBinding

   // private  var currWeather : Weather? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.hours_list_item,parent,false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: HoursAdapter.ViewHolder, position: Int) {

                holder.itemHigh.text =weather?.main?.temp_max.toString().substring(0, 2) + "°C"
        holder.itemView.apply {

            Glide.with(holder.itemView).load(" http://openweathermap.org/img/wn/10d.png").into(holder.itemImage2)
              var time = weather?.dt
        fun fromHoursstamp(time: Long?):String {
            val netDate = Date(time!! *1000L)
            val sDF = SimpleDateFormat("h a")
            return sDF.format(netDate)
        }
        holder.itemTime.text = fromHoursstamp(time?.toLong())
        }
    }
    inner class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        var itemTime = itemView.findViewById<TextView>(R.id.time)
        var itemHigh = itemView.findViewById<TextView>(R.id.high)
        var itemImage2 = itemView.findViewById<ImageView>(R.id.imageView2)
    }
    override fun getItemCount(): Int {
        return 12
    }
}