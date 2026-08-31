package com.example.employeetrackerapp.data.repository

import com.example.employeetrackerapp.data.dao.EmployeeLocationRecordDao

class EmployeeLocationRecordRepository(
    private val dao: EmployeeLocationRecordDao
) {
    fun recordLocation(
        employeeId: Int,
        locationId: Int
    ): Boolean {
        return dao.recordLocation(
            employeeId,
            locationId
        )
    }
}