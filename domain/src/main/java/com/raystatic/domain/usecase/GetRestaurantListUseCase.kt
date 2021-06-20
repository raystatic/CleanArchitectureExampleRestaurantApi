package com.raystatic.domain.usecase

import com.raystatic.domain.Resource
import com.raystatic.domain.model.Restaurant
import com.raystatic.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRestaurantListUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {

    suspend fun execute(lat:Double, lng:Double, offset:Int, limit:Int):Flow<Resource<List<Restaurant>>>{
        println("RAYDEBUG getRestaurantListUseCase: this is called.")
        return repository.getRestaurantList(lat,lng,offset, limit)
    }

}