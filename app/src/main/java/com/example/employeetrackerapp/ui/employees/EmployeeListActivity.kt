package com.example.employeetrackerapp.ui.employees

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
                EmployeeAdapter(
                    employees,

                    onEditClick = {
                        employees ->
                                val intent = Intent(
                                    this,
                                    RegisterEmployeeActivity::class.java
                                )
                        intent.putExtra(
                            "EMPLOYEE_ID",
                            employees.id
                        )
                        startActivity(intent)
                    },

                    onDeleteClick = {
                        employees ->

                        AlertDialog.Builder(this)
                            .setTitle("Confirmation")
                            .setMessage(
                                "Are you sure you want to delete " +
                                "${employees.firstname} ${employees.lastname}?"
                            )
                            .setNegativeButton("Cancel", null)
                            .setPositiveButton("Delete") { _,_ ->
                                val success =
                                    viewModel.deleteEmployee(employees.id)

                                if (success) {
                                    Toast.makeText(
                                        this,
                                        "Employee deleted successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    viewModel.loadEmployees()
                                } else {
                                    Toast.makeText(
                                        this,
                                        "Failed to deleted employee",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                            .show()
                    }
                )
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