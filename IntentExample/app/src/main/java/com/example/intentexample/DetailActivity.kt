package com.example.intentexample

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity(),AdapterView.OnItemClickListener {
    lateinit var listView: ListView
    lateinit var adapter: ArrayAdapter<String>
    private lateinit var datas:ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val category=intent.getStringExtra("category")
        listView=findViewById(R.id.detail_list)
        listView.onItemClickListener = this

        val helper = DBHelpler(this)
        val db =helper.writableDatabase
        val cursor =db.rawQuery("select location from tb_data where category=?", arrayOf(category))
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
        intent.putExtra("location",textView.text.toString())
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}
