package com.example.todolistex

import android.app.Application
import io.realm.Realm

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}