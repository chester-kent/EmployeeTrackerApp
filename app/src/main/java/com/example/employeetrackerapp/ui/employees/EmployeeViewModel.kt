package com.example.employeetrackerapp.ui.employees

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeetrackerapp.data.model.Employees
import com.example.employeetrackerapp.data.repository.EmployeeRepository
import kotlinx.coroutines.launch

class EmployeeViewModel (private val repository : EmployeeRepository

): ViewModel() {

    private val _employees =
        MutableLiveData<List<Employees>>()

    val employees: LiveData<List<Employees>>
        get() = _employees

    val registerResult = MutableLiveData<Boolean>()

    fun loadEmployees() {
        viewModelScope.launch {
            val result =
                repository.getEmployees()

            _employees.value = result
        }
    }

    fun registerEmployee(employee: Employees) {
        registerResult.value = repository.insertEmployee(employee)
    }

    fun getEmployeeById(id: Int): Employees? {
        return repository.getEmployeeById(id)
    }

    fun updateEmployee(employees: Employees) {
        repository.updateEmployee(employees)
    }

}