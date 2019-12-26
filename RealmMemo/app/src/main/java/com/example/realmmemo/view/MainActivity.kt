package com.example.realmmemo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realmmemo.R
import com.example.realmmemo.model.Todo
import com.example.realmmemo.presenter.presenterRecyclerView
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var listItems:RealmResults<Todo>
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var presenter: presenterRecyclerView
    private lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)

        realm = Realm.getDefaultInstance()
       // realm.beginTransaction()

       listItems=realm.where<Todo>()
            .findAll()
            .sort("id",Sort.DESCENDING)

        Log.d("dddd",listItems.toString())

        btn_add.setOnClickListener {
            startActivity(Intent(this@MainActivity,EditActivity::class.java))
        }


        init()

    }


    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    override fun onRestart() {
        super.onRestart()
        adapter.notifyDataSetChanged()
    }

    private  fun init(){
       // listItems= ArrayList()
        presenter= presenterRecyclerView(listItems)
        adapter = RecyclerViewAdapter(presenter, this)
        rec_list.layoutManager = LinearLayoutManager(this)
        rec_list.adapter = adapter
    }
}
