package com.example.employeetrackerapp.data.dao

import android.database.sqlite.SQLiteDatabase
import com.example.employeetrackerapp.data.model.User

class UserDao(
    private val database: SQLiteDatabase
) {
    fun login(
        username: String,
        password: String
    ): User? {
        val cursor =
            database.rawQuery(
                """
                   Select *
                   FROM employees
                   WHERE username=?
                   AND password=?
                """,

                arrayOf(
                    username,
                    password
                )
            )
        var user: User? =null

        if (cursor.moveToFirst()){
            user = User(
                id =
                    cursor.getInt(
                        cursor.getColumnIndexOrThrow("id")
                    ),
                username =
                    cursor.getString(
                        cursor.getColumnIndexOrThrow("username")
                    ),
                password =
                    cursor.getString(
                        cursor.getColumnIndexOrThrow("password")
                    ),
                firstname =
                    cursor.getString(
                        cursor.getColumnIndexOrThrow("firstname")
                    )
            )
        }
        cursor.close()

        return user
    }
}