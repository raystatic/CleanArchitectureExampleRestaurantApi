package com.raystatic.cleanarchrestaurant.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.raystatic.cleanarchrestaurant.databinding.ActivityMainBinding
import com.raystatic.cleanarchrestaurant.presentation.adapters.RestaurantAdapter
import com.raystatic.domain.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val vm by viewModels<MainViewModel>()

    private lateinit var restaurantAdapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        restaurantAdapter = RestaurantAdapter()

        binding.restaurantList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = restaurantAdapter
        }

        subscribeToObservers()

        vm.getRestaurantsList()

    }

    private fun subscribeToObservers() {
        vm.restaurants.observe(this, {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar1.isVisible = false
                    restaurantAdapter.submitData(it.data ?: listOf())
                }

                is Resource.ERROR -> {
                    binding.progressBar1.isVisible = false
                    Log.d("TAG", "subscribeToObservers: Error: ${it.exception.message}")
                    Toast.makeText(this, "Error.", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    binding.progressBar1.isVisible = true
                }
            }
        })
    }
}