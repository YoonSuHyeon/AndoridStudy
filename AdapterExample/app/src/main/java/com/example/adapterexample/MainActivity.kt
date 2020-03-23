package com.example.adapterexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.database.sqlite.SQLiteDatabase
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    lateinit var arrayDatas: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayView : ListView = findViewById(R.id.main_listview_array)
        arrayView.onItemClickListener = this
        val simpleView  :ListView = findViewById(R.id.main_listview_simple)
        val cursorView  :ListView = findViewById(R.id.main_listview_cursor)

        arrayDatas = resources.getStringArray(R.array.location)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayDatas)
        arrayView.adapter = arrayAdapter


        val simpleDatas =ArrayList<HashMap<String,String>>()
        val helper = DBHelper(this)
        val db = helper.getWritableDatabase()
        val cursor = db.rawQuery("select * from tb_data", null)
        while (cursor.moveToNext()) {
            val map = HashMap<String,String>()
            map.put("name", cursor.getString(1))
            map.put("content", cursor.getString(2))
            simpleDatas.add(map)
        }

        val adapter = SimpleAdapter(
            this, simpleDatas, android.R.layout.simple_list_item_2,
            arrayOf("name", "content"), intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        simpleView.setAdapter(adapter)

        val cursorAdapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_2,
            cursor,
            arrayOf("name", "content"),
            intArrayOf(android.R.id.text1, android.R.id.text2),
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )
        cursorView.setAdapter(cursorAdapter)

    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val t = Toast.makeText(this, arrayDatas[position], Toast.LENGTH_SHORT)
        t.show()
    }
}
