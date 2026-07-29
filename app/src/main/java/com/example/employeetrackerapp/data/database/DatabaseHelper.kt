package com.example.employeetrackerapp.data.database
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context)
    : SQLiteOpenHelper(context,"employeetracker.db",null,1){

    override fun onCreate(db: SQLiteDatabase) {

        val query =
            """
            CREATE TABLE employees(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                firstname TEXT NOT NULL,
                lastname TEXT NOT NULL,
                gender TEXT,
                department TEXT,
                username TEXT NOT NULL,
                password TEXT NOT NULL,
                isadmin INTEGER NOT NULL DEFAULT 0
            )
            """

        db.execSQL(query)

        db.execSQL(
            """
            INSERT INTO employees(firstname,lastname,gender,department,username,password,isadmin)
            VALUES('Chester','Kent','Male','IT','admin','12345', 1)
            """
        )
        db.execSQL(
            """
        INSERT INTO employees
        (firstname,lastname,gender,department,username,password,isadmin)
        VALUES
        ('John','Smith','Male','HR','john','11111',0)
        """
        )
        db.execSQL(
            """
        INSERT INTO employees
        (firstname,lastname,gender,department,username,password,isadmin)
        VALUES
        ('Mary','Jones','Female','Finance','mary','22222',0)
        """
        )

    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion:Int,
        newVersion:Int
    ){

        db.execSQL("DROP TABLE IF EXISTS employees")
        onCreate(db)

    }


}