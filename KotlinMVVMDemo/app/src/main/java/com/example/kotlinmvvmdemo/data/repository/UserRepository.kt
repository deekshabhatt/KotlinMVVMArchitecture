package com.example.kotlinmvvmdemo.data.repository

import com.example.kotlinmvvmdemo.data.db.AppDatabase
import com.example.kotlinmvvmdemo.data.network.ApiInterface
import com.example.kotlinmvvmdemo.data.network.SafeApiRequest
import com.example.kotlinmvvmdemo.data.network.responses.AuthResponse
import retrofit2.Response
import retrofit2.Retrofit
import com.example.kotlinmvvmdemo.data.db.entities.User
import retrofit2.converter.gson.GsonConverterFactory

class UserRepository(
    private val api: ApiInterface,
    private val db: AppDatabase
) : SafeApiRequest() {

    //calling user login from API interface
   /* suspend fun userLogin(email: String, password: String): Response<AuthResponse> {
        return ApiInterface().userLogin(email, password) }*/


    /* code for Handing Api Exceptions */

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) } }

    suspend fun userSignup(name: String,email: String, password: String): AuthResponse {
        return apiRequest { api.userSignup(name,email, password) } }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser()= db.getUserDao().getuser()
    }
