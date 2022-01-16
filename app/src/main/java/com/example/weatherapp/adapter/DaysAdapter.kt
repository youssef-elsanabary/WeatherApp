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
import com.example.weatherapp.databinding.DaysListItemBinding
import com.example.weatherapp.retrofit.Weather
import java.text.SimpleDateFormat
import java.util.*

class DaysAdapter(private var weather: Weather) :RecyclerView.Adapter<DaysAdapter.ViewHolder>() {

    companion object DIFF_UTIL : DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem.main == newItem.main
        }
    }
    private lateinit var binding: DaysListItemBinding
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemDay: TextView
        var itemHighest: TextView
        var itemLowest: TextView
        var itemImage: ImageView
        init {
            itemDay = itemView.findViewById(R.id.day)
            itemHighest = itemView.findViewById(R.id.highest_temp)
            itemLowest = itemView.findViewById(R.id.lowest_temp)
            itemImage = itemView.findViewById(R.id.imageView)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.days_list_item, parent, false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.itemView).load(" http://openweathermap.org/img/wn/10d.png").into(holder.itemImage)
        holder.itemHighest.text = weather?.main?.temp_max.toString().substring(0, 2) + "°C"
        holder.itemLowest.text = weather?.main?.temp_min.toString().substring(0, 2) + "°C"
        var time = weather?.dt
        fun fromHoursstamp(time: Long?): String {
            val netDate = Date(time!! * 1000L)
            val sDF = SimpleDateFormat("h a")
            return sDF.format(netDate)
        }
        holder.itemDay.text = fromHoursstamp(time?.toLong())
    }
    override fun getItemCount(): Int {
        return 7
    }
}