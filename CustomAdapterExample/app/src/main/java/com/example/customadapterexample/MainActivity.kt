package com.example.customadapterexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()   {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val helper =DBHelper(this)
        val db = helper.writableDatabase
        val cursor=db.rawQuery("select * from tb_drive",null)

        val datas =ArrayList<DriveVO>()
        while(cursor.moveToNext()){
            val vo =DriveVO(cursor.getString(3),cursor.getString(1),cursor.getString(2))
            datas.add(vo)
        }
        db.close()


        val adapter =DriveAdapter(this,R.layout.custom_item,datas)
        custom_listview.adapter=adapter
    }

}
