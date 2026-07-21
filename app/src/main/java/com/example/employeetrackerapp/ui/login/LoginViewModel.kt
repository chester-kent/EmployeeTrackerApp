package com.example.employeetrackerapp.ui.login

import androidx.lifecycle.ViewModel
import com.example.employeetrackerapp.data.model.User
import com.example.employeetrackerapp.data.repository.UserRepository

class LoginViewModel(
    private val repository:UserRepository
):ViewModel(){

    fun login(
        username:String,
        password:String
    ): User?{
        return repository.login(
            username,
            password
        )
    }

}