package com.example.addressbookexample2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?):SQLiteOpenHelper(context,"addodb",null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val addSQL="create table tb_contact("+
                "_id integer primary key autoincrement,"+
                "name not null,"+
                "phone,"+
                "email)"
        db.execSQL(addSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if(newVersion== DATABASE_VERSION){
            db.execSQL("drop table tb_contact")
            onCreate(db)

        }
    }


    companion object{
        const val DATABASE_VERSION = 1
    }
}