package com.example.shoppingexample5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list =ArrayList<ShoppingItem>()
        list.add(ShoppingItem(ResourcesCompat.getDrawable(resources, R.drawable.bag, null)!!,"가방"))
        list.add(ShoppingItem(ResourcesCompat.getDrawable(resources, R.drawable.cosmetics, null)!!,"화장품"))
        list.add(ShoppingItem(ResourcesCompat.getDrawable(resources, R.drawable.jacket, null)!!,"자켓"))
        list.add(ShoppingItem(ResourcesCompat.getDrawable(resources, R.drawable.pants, null)!!,"바지"))
        list.add(ShoppingItem(ResourcesCompat.getDrawable(resources, R.drawable.shoes, null)!!,"신발"))
        list.add(ShoppingItem(ResourcesCompat.getDrawable(resources, R.drawable.socks, null)!!,"양말"))
        list.add(ShoppingItem(ResourcesCompat.getDrawable(resources, R.drawable.stationery, null)!!,"학용품"))
        list.add(ShoppingItem(ResourcesCompat.getDrawable(resources, R.drawable.coat, null)!!,"상의"))

        val adapter=RecyclerAdapter(list,this)
        recyclerView.adapter=adapter

        recyclerView.addItemDecoration(
            DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        )
        recyclerView.addItemDecoration(
            DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL)
        )
    }
}
