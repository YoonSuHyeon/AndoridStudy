package com.example.sqliteexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_read_db.*

class ReadDBActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_db)

        val helper =DBHelper(this)
        val db =helper.writableDatabase
        val cursor=db.rawQuery("select title, content from tb_memo order by _id desc limit 1", null)
        while(cursor.moveToNext()){
            read_title.text = cursor.getString(0)
            read_content.text=cursor.getString(1)
        }

        db.close()
    }
}
