package com.example.employeetrackerapp.data.dao
import android.content.ContentValues
import com.example.employeetrackerapp.data.model.Location
import com.example.employeetrackerapp.data.database.DatabaseHelper

class LocationDao(
    private val dbHelper: DatabaseHelper
) {
    fun getAllLocations(): List<Location> {
        val list = mutableListOf<Location>()

        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM locations",
            null
        )

        while (cursor.moveToNext()) {
            list.add(
                Location(
                    id = cursor.getInt(
                        cursor.getColumnIndexOrThrow("id")
                    ),
                    locationName = cursor.getString(
                        cursor.getColumnIndexOrThrow("location_name")
                    ),
                    description =  cursor.getString(
                        cursor.getColumnIndexOrThrow("description")
                    ),
                    qrCode = cursor.getString(
                        cursor.getColumnIndexOrThrow("qr_code")
                    )
                )
            )
        }
        cursor.close()
        return list
    }

    fun getLocationById(id: Int): Location? {
        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            """
                SELECT *
                FROM locations
                where id = ?
            """.trimIndent(),
                arrayOf(id.toString())
        )

        var location: Location? = null

        if (cursor.moveToFirst()) {
            location = Location(
                id = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id")
                ),
                locationName = cursor.getString(
                    cursor.getColumnIndexOrThrow("location_name")
                ),
                description =  cursor.getString(
                    cursor.getColumnIndexOrThrow("description")
                ),
                qrCode = cursor.getString(
                    cursor.getColumnIndexOrThrow("qr_code")
                )
            )
        }
        cursor.close()
        return location
    }

    fun insertLocation(location: Location): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(
                "location_name",
                location.locationName
            )

            put(
                "description",
                location.description
            )

            put(
                "qr_code",
                location.qrCode
            )

        }
        val result = db.insert(
            "locations",
            null,
            values
        )

        if (result == -1L) {
            return false
        }

        val qrValue = "LOC-%03d".format(result)

        val qrValues = ContentValues().apply {
            put(
                "qr_code",
                qrValue
            )
        }

        val updateResult = db.update(
            "locations",
            qrValues,
            "id = ?",
            arrayOf(result.toString())
        )
        return updateResult > 0
    }

    fun updateLocation(location: Location): Boolean {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(
                "location_name",
                location.locationName
            )

            put(
                "description",
                location.description
            )
        }

        val result = db.update(
            "locations",
            values,
            "id = ?",
            arrayOf(location.id.toString())
        )
        return result > 0
    }

    fun deleteLocation(id: Int): Boolean {
        val db = dbHelper.writableDatabase

        val result = db.delete(
            "locations",
            "id = ?",
            arrayOf(id.toString())
        )
        return result > 0
    }
}