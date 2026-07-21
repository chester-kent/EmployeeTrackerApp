package com.example.employeetrackerapp.data.database
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context)
    : SQLiteOpenHelper(context,"employeetracker.db",null,1){

    override fun onCreate(db: SQLiteDatabase) {

        val query =
            """
            CREATE TABLE users(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT,
                password TEXT
            )
            """

        db.execSQL(query)


        db.execSQL(
            """
            INSERT INTO users(username,password)
            VALUES('admin','12345')
            """
        )

    }


    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion:Int,
        newVersion:Int
    ){

        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)

    }


}