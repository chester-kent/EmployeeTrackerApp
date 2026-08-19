package com.example.employeetrackerapp.data.repository

import com.example.employeetrackerapp.data.dao.LocationDao
import com.example.employeetrackerapp.data.model.Location

class LocationRepository(
    private val locationDao: LocationDao
) {
    fun getAllLocations(): List<Location> {
        return locationDao.getAllLocations()
    }

    fun getLocationById(id: Int): Location? {
        return locationDao.getLocationById(id)
    }

    fun insertLocation(location: Location): Boolean {
        return locationDao.insertLocation(location)
    }

    fun updateLocation(location: Location): Boolean {
        return locationDao.updateLocation(location)
    }

    fun deleteLocation(id: Int): Boolean {
        return locationDao.deleteLocation(id)
    }
}