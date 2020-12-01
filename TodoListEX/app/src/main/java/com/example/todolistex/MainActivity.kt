package com.example.todolistex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where

class MainActivity : AppCompatActivity() {
    val realm = Realm.getDefaultInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val realmResult = realm.where<Todo>().findAll().sort("date",Sort.DESCENDING)

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}