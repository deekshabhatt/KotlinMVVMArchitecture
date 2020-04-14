package com.example.kotlinmvvmdemo.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinmvvmdemo.R
import com.example.kotlinmvvmdemo.data.db.AppDatabase
import com.example.kotlinmvvmdemo.data.db.entities.User
import com.example.kotlinmvvmdemo.data.network.ApiInterface
import com.example.kotlinmvvmdemo.data.network.NetworkConInterceptor
import com.example.kotlinmvvmdemo.data.repository.UserRepository
import com.example.kotlinmvvmdemo.databinding.ActivityLoginBinding
import com.example.kotlinmvvmdemo.ui.home.HomeActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListner, KodeinAware {

    // Dependency injection using koein

    override val kodein by kodein()
    private val factory : AuthFactory by instance()

    override fun onSuccess(user: User) {
       Toast.makeText(this,user.email,Toast.LENGTH_LONG).show()
    }

    override fun onFailure(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLoginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login)
        val authViewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel=authViewModel
        authViewModel.authListener=this

        authViewModel.getLoggedInUser().observe(this, Observer { user ->
            if(user!=null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

    }
}
