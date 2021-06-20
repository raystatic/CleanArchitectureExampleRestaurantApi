package com.raystatic.cleanarchrestaurant.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raystatic.cleanarchrestaurant.databinding.ListItemRestaurantBinding
import com.raystatic.domain.model.Restaurant

class RestaurantAdapter:RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    var item = listOf<Restaurant>()

    fun submitData(list: List<Restaurant>){
        item = list
        notifyDataSetChanged()
    }

    inner class RestaurantViewHolder(private val binding:ListItemRestaurantBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:Restaurant){
            binding.apply {
                Glide.with(itemView)
                    .load(item.coverImgUrl)
                    .into(image)

                name.text = item.name
                description.text = item.description
                minutes.text = item.status

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = ListItemRestaurantBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(item[position])
    }

    override fun getItemCount(): Int {
        return item.size
    }
}