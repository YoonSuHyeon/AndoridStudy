package com.example.realmmemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realmmemo.R
import com.example.realmmemo.model.Memo
import com.example.realmmemo.presenter.presenterRecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var listItems:ArrayList<Memo>
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var presenter: presenterRecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        initRecyclerView()
    }

    private  fun initRecyclerView(){
        for (i in 1..25)
            listItems.add(Memo("item $i"))
        adapter.notifyDataSetChanged()
    }

    private  fun init(){
        listItems= ArrayList()
        presenter= presenterRecyclerView(listItems)
        adapter = RecyclerViewAdapter(presenter, this)
        rec_list.layoutManager = LinearLayoutManager(this)
        rec_list.adapter = adapter
    }
}
