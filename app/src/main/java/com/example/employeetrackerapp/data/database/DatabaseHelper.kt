package com.example.employeetrackerapp.data.database
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context)
    : SQLiteOpenHelper(context,"employeetracker.db",null,1){

    override fun onCreate(db: SQLiteDatabase) {

        // EMPLOYEES
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

        // SAMPLE EMPLOYEES
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

        // LOCATIONS
        db.execSQL(
            """
                CREATE TABLE locations(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    location_name TEXT NOT NULL,
                    description TEXT,
                    qr_code TEXT NOT NULL UNIQUE
                )
            """.trimIndent()
        )

        // SAMPLE LOCATIONS
        db.execSQL(
            """
        INSERT INTO locations(location_name,description,qr_code)
        VALUES('Reception','Reception Area','LOC-001')
            """
        )
        db.execSQL(
            """
        INSERT INTO locations(location_name,description,qr_code)
        VALUES('Pantry','Pantry Area','LOC-002')
            """
        )

        // EMPLOYEE LOCATION RECORDS
        db.execSQL(
            """
                CREATE TABLE employee_location_records(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    employee_id INTEGER NOT NULL,
                    location_id INTEGER NOT NULL,
                    scanned_at TEXT NOT NULL,
                    
                    FOREIGN KEY(employee_id)
                        REFERENCES employees(id),
                        
                    FOREIGN KEY(location_id)
                        REFERENCES locations(id)    
                )
            """.trimIndent()
        )

    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion:Int,
        newVersion:Int
    ){
        db.execSQL("DROP TABLE IF EXISTS employees")
        db.execSQL("DROP TABLE IF EXISTS locations")
        db.execSQL("DROP TABLE IF EXISTS employee_location_records")
        onCreate(db)
    }
}