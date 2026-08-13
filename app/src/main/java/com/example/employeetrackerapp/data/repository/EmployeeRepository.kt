package com.example.employeetrackerapp.data.repository

import com.example.employeetrackerapp.data.dao.EmployeeDao
import com.example.employeetrackerapp.data.model.Employees

class EmployeeRepository(private val employeeDao: EmployeeDao) {
    fun getEmployees(): List<Employees> {
        return employeeDao.getAllEmployees()
    }

    fun insertEmployee(employee: Employees): Boolean {
        return employeeDao.insertEmployee(employee)
    }

    fun getEmployeeById(id: Int): Employees? {
        return employeeDao.getEmployeeById(id)
    }

    fun updateEmployee(employees: Employees) {
         employeeDao.updateEmployee(employees)
    }
}