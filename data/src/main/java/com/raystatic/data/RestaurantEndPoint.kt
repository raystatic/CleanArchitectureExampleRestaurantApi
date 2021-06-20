package com.raystatic.data

import com.raystatic.data.response.RestaurantResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantEndPoint {

    @GET("restaurant/")
    suspend fun getRestaurantList(
        @Query("lat") lat:Double,
        @Query("lng") lng:Double,
        @Query("offset") offset:Int,
        @Query("limit") limit:Int
    ):List<RestaurantResponse>

    @GET("restaurant/{id}")
    suspend fun getRestaurant(
        @Path("id") id:Int
    ):RestaurantResponse

}