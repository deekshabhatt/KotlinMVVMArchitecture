package com.example.kotlinmvvmdemo.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinmvvmdemo.R
import com.example.kotlinmvvmdemo.data.db.entities.User
import com.example.kotlinmvvmdemo.databinding.ActivitySignUpBinding
import com.example.kotlinmvvmdemo.ui.home.HomeActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(),AuthListner,KodeinAware{
    override val kodein by kodein()
    private val factory : AuthFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySignUpBinding
                = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        val authviewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel = authviewModel

        authviewModel.authListener = this

        authviewModel.getLoggedInUser().observe(this, Observer { user ->
            if(user != null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onSuccess(user: User) {
        TODO("Not yet implemented")
    }

    override fun onFailure(message: String) {
        TODO("Not yet implemented")
    }
}
