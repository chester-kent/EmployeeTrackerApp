package com.example.employeetrackerapp.data.repository

import com.example.employeetrackerapp.data.dao.UserDao
import com.example.employeetrackerapp.data.model.User

class UserRepository(private val userDao: UserDao) {

    fun login(
        username:String,
        password:String
    ): User? {
        return userDao.login(
            username,
            password
        )
    }

}