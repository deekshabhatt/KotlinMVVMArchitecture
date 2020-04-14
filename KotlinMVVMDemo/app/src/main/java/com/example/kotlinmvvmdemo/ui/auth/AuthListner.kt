package com.example.kotlinmvvmdemo.ui.auth

import com.example.kotlinmvvmdemo.data.db.entities.User

interface AuthListner {
    fun onSuccess(user: User)
    fun onFailure(message: String)
}