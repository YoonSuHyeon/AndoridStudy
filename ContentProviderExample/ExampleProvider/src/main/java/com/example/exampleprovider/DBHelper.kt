package com.example.exampleprovider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, "datadb", null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val tableSql = "create table tb_data (" +
                "_id integer primary key autoincrement," +
                "name not null," +
                "phone)"
        db.execSQL(tableSql)
        db.execSQL("insert into tb_data (name,phone) values ('박찬호','0101111111')")
        db.execSQL("insert into tb_data (name,phone) values ('류현진','0102222222')")
        db.execSQL("insert into tb_data (name,phone) values ('오승환','0103333333')")
        db.execSQL("insert into tb_data (name,phone) values ('김현수','0105555555')")
        db.execSQL("insert into tb_data (name,phone) values ('추신수','0106666666')")
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table tb_data")
            onCreate(db)
        }
    }

    companion object {
        const val DATABASE_VERSION = 6
    }
}