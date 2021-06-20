package com.raystatic.domain.repository

import com.raystatic.domain.Resource
import com.raystatic.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {

    suspend fun getRestaurantList(lat:Double,lng:Double, offset:Int, limit:Int):Flow<Resource<List<Restaurant>>>

    suspend fun getRestaurant(id:Int):Flow<Resource<Restaurant>>

}