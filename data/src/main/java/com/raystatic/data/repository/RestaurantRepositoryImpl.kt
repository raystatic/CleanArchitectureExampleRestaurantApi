package com.raystatic.data.repository

import android.util.Log
import com.raystatic.data.RestaurantApi
import com.raystatic.data.mapper.RestaurantMapper
import com.raystatic.domain.Resource
import com.raystatic.domain.model.Restaurant
import com.raystatic.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class RestaurantRepositoryImpl(
    private val restaurantApi:RestaurantApi,
    private val restaurantMapper: RestaurantMapper
):RestaurantRepository {

    override suspend fun getRestaurantList(
        lat: Double,
        lng: Double,
        offset: Int,
        limit: Int
    ): Flow<Resource<List<Restaurant>>> {
        Log.d("RAYDEBUG", "getRestaurantList: this is called.")
        return flow {
            emit(Resource.Loading)
            try {
                val result = restaurantApi.getRestaurantList(lat, lng, offset, limit)
                    .map { restaurantMapper.map(it) }
                emit(Resource.Success(result))
            }catch (e:Exception){
               emit( Resource.ERROR(e))
            }
        }
    }

    override  suspend fun getRestaurant(id: Int): Flow<Resource<Restaurant>> {
        return flow {
           emit( Resource.Loading)
            try {
                val response =  restaurantApi.getRestaurant(id)
                val result = restaurantMapper.map(response)
                emit(Resource.Success(result))
            }catch (e:Exception){
                emit(Resource.ERROR(e))
            }
        }
    }
}