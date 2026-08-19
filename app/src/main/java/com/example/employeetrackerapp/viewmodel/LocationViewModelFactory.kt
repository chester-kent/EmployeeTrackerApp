package com.example.employeetrackerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.employeetrackerapp.data.repository.LocationRepository

class LocationViewModelFactory(
    private val repository: LocationRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (
            modelClass.isAssignableFrom(
                LocationViewModel::class.java
            )
        ) {
            @Suppress("UNCHECKED_CAST")
            return LocationViewModel(
                repository
            ) as T
        }
        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}