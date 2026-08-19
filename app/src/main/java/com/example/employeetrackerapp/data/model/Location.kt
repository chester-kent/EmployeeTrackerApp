package com.example.employeetrackerapp.data.model

data class Location (
    val id : Int = 0,
    val locationName: String,
    val description: String,
    var qrCode: String
)
