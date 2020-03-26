package com.example.phoneapplogscreenexample

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context,"calldb",null,DATABASE_VERSION) {



    override fun onCreate(db: SQLiteDatabase?) {
        val tableSql = "create table tb_calllog (" +
                "_id integer primary key autoincrement," +
                "name not null," +
                "photo," +
                "date," +
                "phone)"

        db!!.execSQL(tableSql)

        db.execSQL("insert into tb_calllog (name, photo, date, phone) values ('홍길동','yes','휴대전화, 1일전','010001')")
        db.execSQL("insert into tb_calllog (name, photo, date, phone) values ('류현진','no','휴대전화, 1일전','010001')")
        db.execSQL("insert into tb_calllog (name, photo, date, phone) values ('강정호','no','휴대전화, 2일전','010001')")
        db.execSQL("insert into tb_calllog (name, photo, date, phone) values ('김현수','yes','휴대전화, 2일전','010001')")
        db.execSQL("insert into tb_calllog (name, photo, date, phone) values ('오승환','no','휴대전화, 2일전','010001')")
        db.execSQL("insert into tb_calllog (name, photo, date, phone) values ('이대호','no','휴대전화, 3일전','010001')")
        db.execSQL("insert into tb_calllog (name, photo, date, phone) values ('박병호','no','휴대전화, 3일전','010001')")
        db.execSQL("insert into tb_calllog (name, photo, date, phone) values ('추신수','no','휴대전화, 3일전','010001')")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(newVersion == DATABASE_VERSION){
            db!!.execSQL("drop table tb_calllog");
            onCreate(db);
        }
    }

    companion object{
        const val DATABASE_VERSION=1
    }
}