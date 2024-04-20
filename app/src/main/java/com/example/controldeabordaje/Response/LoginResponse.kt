package com.example.controldeabordaje.Response

import com.example.controldeabordaje.Model.User

data class LoginResponse(
    val success : Boolean,
    val user : User,
    val token : String
)