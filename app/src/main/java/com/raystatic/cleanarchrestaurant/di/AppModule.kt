package com.raystatic.cleanarchrestaurant.di

import com.google.gson.Gson
import com.raystatic.data.RestaurantApi
import com.raystatic.data.RestaurantEndPoint
import com.raystatic.data.mapper.RestaurantMapper
import com.raystatic.data.repository.RestaurantRepositoryImpl
import com.raystatic.domain.repository.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =  Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.doordash.com/v2/")
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(RestaurantEndPoint::class.java)

    @Provides
    @Singleton
    fun provideRestaurantRepository(restaurantApi: RestaurantApi, restaurantMapper: RestaurantMapper): RestaurantRepository {
        return RestaurantRepositoryImpl(restaurantApi, restaurantMapper)
    }

}