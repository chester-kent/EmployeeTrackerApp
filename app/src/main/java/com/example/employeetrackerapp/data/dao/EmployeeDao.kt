package com.example.employeetrackerapp.data.dao
import android.content.ContentValues
import com.example.employeetrackerapp.data.database.DatabaseHelper
import com.example.employeetrackerapp.data.model.Employees

class EmployeeDao(
    private val dbHelper: DatabaseHelper
) {
     fun getAllEmployees(): List<Employees> {
        val list = mutableListOf<Employees>()

        val db = dbHelper.readableDatabase

        var cursor = db.rawQuery(
            "Select * from employees",
            null
        )
        while (cursor.moveToNext()) {
            list.add( Employees(
                id = cursor.getInt(0),
                firstname = cursor.getString(1),
                lastname = cursor.getString(2),
                gender = cursor.getString(3),
                department = cursor.getString(4),
                username = cursor.getString(5),
                password = cursor.getString(6),
                )
            )
        }
        return  list;
    }

    fun insertEmployee(employees: Employees): Boolean {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("firstname", employees.firstname)
            put("lastname", employees.lastname)
            put("gender", employees.gender)
            put("department", employees.department)
            put("username", employees.username)
            put("password", employees.password)
        }

        val result = db.insert("employees", null, values)

        db.close()
        return result != 1L

    }

    fun getEmployeeById(id: Int): Employees? {
        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            """
                Select *
                From employees
                where id = ?    
            """,
            arrayOf(id.toString())
        )

        var employee: Employees? = null

        if (cursor.moveToFirst()) {
            employee = Employees(
                id = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id")
                ),
                firstname = cursor.getString(
                    cursor.getColumnIndexOrThrow("firstname")
                ),
                lastname = cursor.getString(
                    cursor.getColumnIndexOrThrow("lastname")
                ),
                gender = cursor.getString(
                    cursor.getColumnIndexOrThrow("gender")
                ),
                department = cursor.getString(
                    cursor.getColumnIndexOrThrow("department")
                ),
                username = cursor.getString(
                    cursor.getColumnIndexOrThrow("username")
                ),
                password = cursor.getString(
                    cursor.getColumnIndexOrThrow("password")
                )
            )
        }
        cursor.close()
        return employee
    }

    fun updateEmployee(employees: Employees): Boolean {
        val db = dbHelper.writableDatabase

        val values = ContentValues()
        values.put("firstname", employees.firstname)
        values.put("lastname", employees.lastname)
        values.put("gender", employees.gender)
        values.put("department", employees.department)
        values.put("username", employees.username)
        values.put("password", employees.password)

        val result = db.update(
            "employees",
            values,
            "id = ?",
            arrayOf(employees.id.toString())
        )

        return result > 0

    }

    fun deleteEmployee(id: Int): Boolean {
        val db = dbHelper.writableDatabase

        val result = db.delete(
            "employees",
            "id = ?",
            arrayOf(id.toString())
        )
        return result > 0
    }
}