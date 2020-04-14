package com.example.kotlinmvvmdemo.data.network.responses

import com.example.kotlinmvvmdemo.data.db.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)