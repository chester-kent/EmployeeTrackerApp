package com.example.employeetrackerapp.ui.login
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.employeetrackerapp.data.dao.UserDao
import com.example.employeetrackerapp.data.database.DatabaseHelper
import com.example.employeetrackerapp.data.repository.UserRepository
import com.example.employeetrackerapp.databinding.ActivityLoginBinding
import com.example.employeetrackerapp.ui.dashboard.Dashboard
import com.example.employeetrackerapp.viewmodel.LoginViewModel


class LoginActivity:AppCompatActivity(){
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        binding =
            ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val database =
            DatabaseHelper(this)
        val db =
            database.readableDatabase
        val userDao =
            UserDao(db)
        val repository =
            UserRepository(userDao)
        val viewModel =
            LoginViewModel(repository)
        binding.btnLogin.setOnClickListener{
            val username =
                binding.txtUsername.text.toString()
            val password =
                binding.txtPassword.text.toString()
            val user = viewModel.login(
                username,
                password
            )
            if(user != null){
                val intent = Intent(
                    this,
                    Dashboard::class.java
                )
                intent.putExtra(
                    "USER_ID",
                    user.id
                )
                intent.putExtra(
                    "USERNAME",
                    user.username
                )
                intent.putExtra(
                    "FIRSTNAME",
                    user.firstname
                )
                startActivity(
                    intent
                )
            }else{
                Toast.makeText(
                    this,
                    "Invalid Login!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}