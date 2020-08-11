package com.example.activitylifecycleexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var datas:ArrayList<String>
    lateinit var adapter:ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_btn_detail.setOnClickListener(this)
        main_btn_dialog.setOnClickListener(this)



        if(savedInstanceState != null) //화면전환이 되어도 바뀌지않게 하는법
        {
            datas=savedInstanceState.getStringArrayList("adapter")!!
            adapter=ArrayAdapter(this,R.layout.item_main_list,datas)
            main_list.adapter=adapter
        }else{
            datas= ArrayList()
            datas.add("onCreate...")

            adapter=ArrayAdapter(this,R.layout.item_main_list,datas)
            main_list.adapter=adapter
        }


    }

    override fun onClick(v: View?) {

        if(v==main_btn_detail){
            val intent = Intent(this,DetailActivity::class.java)
            startActivity(intent)

        }else if(v==main_btn_dialog){
             val intent = Intent(this,DialogActivity::class.java)
             startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        datas.add("onResume...")
        adapter.notifyDataSetChanged()
    }
    override fun onPause(){
        super.onPause()
        datas.add("onPause...")
        adapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        datas.add("onStart...")
        adapter.notifyDataSetChanged()
    }

    override fun onStop() {
        super.onStop()
        datas.add("onStop...")
        adapter.notifyDataSetChanged()
    }

    override fun onRestart() {
        super.onRestart()
        datas.add("onRestart...")
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        datas.add("onDestroy...")
        adapter.notifyDataSetChanged()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        datas.add("onSaveInstanceStatel...")
        adapter.notifyDataSetChanged()

        outState.putStringArrayList("adapter",datas)
        outState.putString("data1","hello")
        outState.putInt("data2",100)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        datas.add("onRestoreInstanceState...")
        adapter.notifyDataSetChanged()

        val data1 = savedInstanceState.getString("data1")
        val data2 =savedInstanceState.getInt("data2")

        Toast.makeText(this, "$data1:$data2",Toast.LENGTH_SHORT).show()
    }
}
