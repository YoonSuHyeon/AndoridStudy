package com.example.contentproviderexample

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemLongClickListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity(), View.OnClickListener, OnItemClickListener,
    OnItemLongClickListener {
    var listView: ListView? = null
    var nameView: EditText? = null
    var phoneView: EditText? = null
    var btn: Button? = null

    var isUpdate = false
    var _id: String? = null

    var datas: ArrayList<HashMap<String, String>>? = null

    var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.lab1_listview)
        nameView = findViewById(R.id.lab1_name)
        phoneView = findViewById(R.id.lab1_phone)
        btn = findViewById(R.id.lab1_btn)
        lab1_listview.onItemClickListener = this
        lab1_listview.onItemLongClickListener = this
        lab1_btn.setOnClickListener(this)

        uri= Uri.parse("content://com.example.part.Provider")
        setAdapter()
    }

    private fun setAdapter() {
        datas= ArrayList()
        val cursor = uri?.let { contentResolver.query(it,null,null,null,null) }
        while(cursor!!.moveToNext()){
            var map = HashMap<String,String>()
            map["id"] = cursor.getString(0)
            map["name"] = cursor.getString(1)
            map["phone"] = cursor.getString(2)
            datas!!.add(map)
        }
        val adapter  = SimpleAdapter(this,datas,android.R.layout.simple_list_item_2, arrayOf("name","phone"),
            intArrayOf(android.R.id.text1,android.R.id.text2))
        lab1_listview.adapter=adapter
    }

    override fun onClick(v: View?) {
        if (isUpdate) {
            //update.......
            val name =lab1_name.text.toString()
            val phone =lab1_phone.text.toString()
            if(name !="" && phone != ""){
                val values = ContentValues()
                values.put("name",name)
                values.put("phone",phone)
                contentResolver.update(uri!!,values,"_id=?", arrayOf(_id))
                setAdapter()
            }
            lab1_name.setText("")
            lab1_phone.setText("")
            isUpdate=false
        } else {
            //insert.......
            val name =lab1_name.text.toString()
            val phone =lab1_phone.text.toString()
            if(name !="" && phone != ""){
                val values = ContentValues()
                values.put("name",name)
                values.put("phone",phone)
                contentResolver.insert(uri!!,values)
                setAdapter()
            }
            lab1_name.setText("")
            lab1_phone.setText("")

        }
    }

    override fun onItemClick(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        val map = datas!![position]
        nameView!!.setText(map["name"])
        phoneView!!.setText(map["phone"])
        _id = map["id"]
        isUpdate = true
    }

    override fun onItemLongClick(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ): Boolean {

        val map = datas!![position]
        contentResolver.delete(uri!!,"_id=?", arrayOf(map["id"]))
        setAdapter()
        return false
    }
}

