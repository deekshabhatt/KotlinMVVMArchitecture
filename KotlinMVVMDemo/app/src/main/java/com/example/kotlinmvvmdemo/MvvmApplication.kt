package com.example.kotlinmvvmdemo

import android.app.Application
import com.example.kotlinmvvmdemo.data.db.AppDatabase
import com.example.kotlinmvvmdemo.data.network.ApiInterface
import com.example.kotlinmvvmdemo.data.network.NetworkConInterceptor
import com.example.kotlinmvvmdemo.data.repository.UserRepository
import com.example.kotlinmvvmdemo.ui.auth.AuthFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MvvmApplication: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MvvmApplication))

        bind() from singleton { NetworkConInterceptor(instance()) }
        bind() from singleton { ApiInterface(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from provider { AuthFactory(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
    }
}