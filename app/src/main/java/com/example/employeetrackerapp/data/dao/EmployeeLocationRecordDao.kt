package com.example.employeetrackerapp.data.dao

import android.content.ContentValues
import com.example.employeetrackerapp.data.database.DatabaseHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EmployeeLocationRecordDao(
    private val dbHelper: DatabaseHelper
) {
    fun recordLocation(
        employeeId: Int,
        locationId: Int
    ): Boolean {
        val db =
            dbHelper.writableDatabase

        val dataFormat =
            SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss",
                Locale.getDefault()
            )

        val scannedAt =
            dataFormat.format(
                Date()
            )

        val values =
            ContentValues().apply {
                put(
                    "employee_id",
                    employeeId
                )
                put(
                    "location_id",
                    locationId
                )
                put("scanned_at",
                    scannedAt)
            }

        val result =
            db.insert(
                "employee_location_records",
                null,
                values
            )

        return result != 1L
    }
}