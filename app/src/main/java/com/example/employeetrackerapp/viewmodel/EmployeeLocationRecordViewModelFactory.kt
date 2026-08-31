package com.example.employeetrackerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.employeetrackerapp.data.repository.EmployeeLocationRecordRepository

class EmployeeLocationRecordViewModelFactory(
    private val repository: EmployeeLocationRecordRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployeeLocationRecordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmployeeLocationRecordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}