package com.example.employeetrackerapp.ui.employees

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeetrackerapp.data.dao.EmployeeDao
import com.example.employeetrackerapp.data.database.DatabaseHelper
import com.example.employeetrackerapp.data.repository.EmployeeRepository
import com.example.employeetrackerapp.databinding.ActivityEmployeeListBinding

class EmployeeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmployeeListBinding
    private lateinit var viewModel: EmployeeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityEmployeeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val databaseHelper = DatabaseHelper(this)
        val employeeDao = EmployeeDao(databaseHelper)
        val repository = EmployeeRepository(employeeDao)
        val factory = EmployeeViewModelFactory(repository)

        viewModel = ViewModelProvider (
            this,
            factory
        )[EmployeeViewModel::class.java]

        binding.rvEmployees.layoutManager =
            LinearLayoutManager(this)

        viewModel.employees.observe(this){ employees ->
            binding.rvEmployees.adapter =
                EmployeeAdapter(employees)
        }


        binding.fabAddEmployee.setOnClickListener {
            val intent = Intent (
                this,
                RegisterEmployeeActivity::class.java
            )
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadEmployees()
    }
}