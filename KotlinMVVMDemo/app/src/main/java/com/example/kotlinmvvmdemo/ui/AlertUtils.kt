package com.example.kotlinmvvmdemo.ui

import android.content.Context
import android.widget.Toast

class AlertUtils {
    fun Context.toast(message : String)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}