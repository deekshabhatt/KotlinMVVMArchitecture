package com.example.kotlinmvvmdemo.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmdemo.data.repository.UserRepository
import com.example.kotlinmvvmdemo.util.Coroutines
import com.example.kotlinmvvmdemo.util.Exception

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var passwordconfirm: String? = null
    var authListener: AuthListner? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }
        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: Exception.ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: Exception.NoInternetException) {
                authListener?.onFailure(e.message!!)
            } catch (e: Exception.NoInternetException) {
                authListener?.onFailure(e.message!!)
            }


        }
    }

    fun onSignUp(view: View) {
        Intent(view.context, SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    fun onLogin(view: View){
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    fun onSignupButtonClick(view: View) {
        if (name.isNullOrEmpty()) {
            authListener?.onFailure("Name is required")
            return
        }

        if (email.isNullOrEmpty()) {
            authListener?.onFailure("Email is required")
            return
        }

        if (password.isNullOrEmpty()) {
            authListener?.onFailure("Please enter a password")
            return
        }

        if (password != passwordconfirm) {
            authListener?.onFailure("Password did not match")
            return
        }
        Coroutines.main {
            try {
                val authResponse = repository.userSignup(name!!, email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: Exception.ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: Exception.NoInternetException) {
                authListener?.onFailure(e.message!!)
            } catch (e: Exception.NoInternetException) {
                authListener?.onFailure(e.message!!)
            }


        }
    }
}