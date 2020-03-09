package com.example.sqliteexample

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, "memodb", null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val memoSQL = "create table tb_memo (" +
                "_id integer primary key autoincrement," +
                "title," +
                "content)"
        db.execSQL(memoSQL)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table tb_memo")
            onCreate(db)
        }
    }

    companion object {
        const val DATABASE_VERSION = 1
    }
}