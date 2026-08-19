package com.example.employeetrackerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeetrackerapp.data.model.Location
import com.example.employeetrackerapp.data.repository.LocationRepository

class LocationViewModel(
    private val repository: LocationRepository
) : ViewModel() {
    private val _locations =
        MutableLiveData<List<Location>>()

    val location: LiveData<List<Location>>
        get() = _locations

    fun loadLocations() {
        _locations.value =
            repository.getAllLocations()
    }

    fun getLocationById(id: Int): Location? {
        return repository.getLocationById(id)
    }

    fun insertLocation(location: Location): Boolean {
        return repository.insertLocation(location)
    }

    fun updateLocation(location: Location): Boolean {
        return repository.updateLocation(location)
    }

    fun deleteLocation(id: Int): Boolean {
        return repository.deleteLocation(id)
    }
}