package com.example.phoneapplogscreenexample

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var callPermission :Boolean=false
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED){
            callPermission=true
        }
        if(!callPermission){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),200)

        }
        val listView=findViewById<ListView>(R.id.main_list)
        var datas=ArrayList<CallLogVO>()

        val helper = DBHelper(this)
        val db = helper.writableDatabase
        val cursor =db.rawQuery("select name, photo, date, phone from tb_calllog", null)

        while(cursor.moveToNext()){
            var vo=CallLogVO(cursor.getString(0),cursor.getString(1)
            ,cursor.getString(2),cursor.getString(3))
            datas.add(vo)
        }

        db.close()
        listView.adapter=CallLogAdapter(this,R.layout.main_list_item,datas)
    }
}
