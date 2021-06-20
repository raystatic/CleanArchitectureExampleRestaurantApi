package com.raystatic.domain.usecase

import com.raystatic.domain.Resource
import com.raystatic.domain.model.Restaurant
import com.raystatic.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRestaurantUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {

    suspend fun execute(id:Int):Flow<Resource<Restaurant>>{
        return repository.getRestaurant(id)
    }

}