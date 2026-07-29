package com.example.employeetrackerapp.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.employeetrackerapp.databinding.ActivityDashboardBinding
import com.example.employeetrackerapp.ui.employees.EmployeeListActivity

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getIntExtra("USER_ID", -1)
        val username = intent.getStringExtra("USERNAME")
        val firstname = intent.getStringExtra("FIRSTNAME")
        Toast.makeText(this, "Welcome to Employee Tracker App $firstname!", Toast.LENGTH_LONG).show()
        Log.d("printUser: ", "$userId $username")

        binding.llEmployees.setOnClickListener {
            val intent = Intent(
                this,
                EmployeeListActivity::class.java
            )
            startActivity(intent)
        }

    }
}