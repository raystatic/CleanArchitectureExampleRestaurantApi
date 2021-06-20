package com.raystatic.cleanarchrestaurant.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raystatic.domain.Resource
import com.raystatic.domain.model.Restaurant
import com.raystatic.domain.usecase.GetRestaurantListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRestaurantListUseCase: GetRestaurantListUseCase,
):ViewModel(){

    private val _restaurants = MutableLiveData<Resource<List<Restaurant>>>(Resource.Loading)
    val restaurants : LiveData<Resource<List<Restaurant>>> get() = _restaurants

    fun getRestaurantsList() = viewModelScope.launch {
        getRestaurantListUseCase.execute(LAT, LNG,0,50)
            .collect {
                _restaurants.value = it
            }
    }

    companion object {
        // Hardcoded LatLng for simplicity here
        const val LAT = 37.422740
        const val LNG = -122.139956
    }

}