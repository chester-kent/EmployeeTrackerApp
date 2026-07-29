package com.example.employeetrackerapp.ui.employees

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.employeetrackerapp.data.dao.EmployeeDao
import com.example.employeetrackerapp.data.database.DatabaseHelper
import com.example.employeetrackerapp.data.model.Employees
import com.example.employeetrackerapp.data.repository.EmployeeRepository
import com.example.employeetrackerapp.databinding.ActivityRegisterEmployeeBinding

class RegisterEmployeeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterEmployeeBinding
    private lateinit var viewModel: EmployeeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityRegisterEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = EmployeeDao(DatabaseHelper(this))
        val repository = EmployeeRepository(dao)
        val factory = EmployeeViewModelFactory(repository)

        viewModel = ViewModelProvider(
            this,
            factory
        )[EmployeeViewModel::class.java]

        // Gender
        val genderList = listOf(
            "Male",
            "Female",
            "Rather not say"
        )
        val genderAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            genderList
        )
        binding.actGender.setAdapter(genderAdapter)
        // Gender

        // Department
        val departmentList = listOf(
            "IT",
            "HR",
            "Finance",
            "Marketing",
            "Operations"
        )
        val departmentAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            departmentList
        )
        binding.actDepartment.setAdapter(departmentAdapter)
        // Department

        binding.btnSave.setOnClickListener {
            val firstName = binding.etFirstName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val gender = binding.actGender.text.toString().trim()
            val department = binding.actDepartment.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val repeatPassword = binding.etRepeatPassword.text.toString().trim()

            if (firstName.isEmpty() ||
                lastName.isEmpty() ||
                gender.isEmpty() ||
                department.isEmpty() ||
                username.isEmpty() ||
                password.isEmpty()
                ) {
                Toast.makeText(this, "Please complete all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != repeatPassword) {
                Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val employee = Employees(
                firstname = firstName,
                lastname = lastName,
                gender = gender,
                department = department,
                username = username,
                password = password,
            )
            viewModel.registerEmployee(employee)
        }

        viewModel.registerResult.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "Employee Registered!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Registration Failed!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }

    }
}