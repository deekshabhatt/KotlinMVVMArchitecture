package com.example.kotlinmvvmdemo.util

import java.io.IOException

class Exception {
    class ApiException(message: String) : IOException(message)
    class NoInternetException(message: String) : IOException(message)
}