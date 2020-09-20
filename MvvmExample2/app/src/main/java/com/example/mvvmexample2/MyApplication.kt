package com.example.mvvmexample2

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext,myDiModule)
    }
}