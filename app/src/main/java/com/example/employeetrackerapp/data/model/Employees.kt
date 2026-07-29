package com.example.employeetrackerapp.data.model

data class Employees(
    val id : Int = 0,
    val firstname: String,
    val lastname: String,
    var gender: String,
    var department: String,
    val username: String,
    val password: String,
)