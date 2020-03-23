package com.example.addressbookexample2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val helper=DBHelper(this)
        val db = helper.writableDatabase
        val cursor = db.rawQuery("select name ,phone , email from tb_contact order by _id desc limit 1",null)

        while(cursor.moveToNext()){
            tv_name.text=cursor.getString(0)
            tv_phone.text=cursor.getString(1)
            tv_email.text=cursor.getString(2)
        }

        db.close()
    }
}
