package com.example.employeetrackerapp.ui.employees

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.employeetrackerapp.data.repository.EmployeeRepository

class EmployeeViewModelFactory(
    private val repository: EmployeeRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(EmployeeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmployeeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}