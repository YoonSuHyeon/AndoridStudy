package com.example.intentexample

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity(),AdapterView.OnItemClickListener {
    lateinit var listView:ListView
    lateinit var adapter: ArrayAdapter<String>
    lateinit var category:String
    private lateinit var datas:ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView=findViewById(R.id.main_list)
        listView.onItemClickListener = this
        val helper =DBHelpler(this)
        val db = helper.writableDatabase
        val cursor =db.rawQuery("select location from tb_data where category='0'",null)
        datas=ArrayList()

        while(cursor.moveToNext()){
            datas.add(cursor.getString(0))
        }
        db.close()

        adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,datas)
        listView.adapter=adapter
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val textView = view as TextView
        category=textView.text.toString()

        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("category",category)
        startActivityForResult(intent,10)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==10&&resultCode== Activity.RESULT_OK){
            val location = data!!.getStringExtra("location")
            Toast.makeText(this, "$category:$location",Toast.LENGTH_SHORT).show()
        }
    }
}
