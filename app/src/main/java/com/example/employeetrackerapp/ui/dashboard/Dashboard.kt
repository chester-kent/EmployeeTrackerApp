package com.example.employeetrackerapp.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.employeetrackerapp.R
import com.example.employeetrackerapp.ui.login.LoginViewModel

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        val userId = intent.getIntExtra("USER_ID", -1)
        val username = intent.getStringExtra("USERNAME")
        Toast.makeText(this, "Welcome to Employee Tracker App!", Toast.LENGTH_LONG).show()
        Log.d("printUser: ", "$userId $username")
    }
}