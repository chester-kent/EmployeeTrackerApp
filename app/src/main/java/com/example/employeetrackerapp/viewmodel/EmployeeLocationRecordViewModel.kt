package com.example.employeetrackerapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.employeetrackerapp.data.repository.EmployeeLocationRecordRepository

class EmployeeLocationRecordViewModel(
    private val repository: EmployeeLocationRecordRepository
): ViewModel() {
    fun recordLocation(
        employeeId: Int,
        locationId: Int
    ): Boolean {
        return repository.recordLocation(
            employeeId,
            locationId
        )
    }
}