package com.example.shoppingexample3

import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = ArrayList<ShoppingItem>()



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            list.add(ShoppingItem(getDrawable(R.drawable.image01)!!,getString(R.string.title01)))
            list.add(ShoppingItem(getDrawable(R.drawable.image02)!!,getString(R.string.title02)))
            list.add(ShoppingItem(getDrawable(R.drawable.image03)!!,getString(R.string.title03)))
            list.add(ShoppingItem(getDrawable(R.drawable.image04)!!,getString(R.string.title04)))
            list.add(ShoppingItem(getDrawable(R.drawable.image05)!!,getString(R.string.title05)))
            list.add(ShoppingItem(getDrawable(R.drawable.image06)!!,getString(R.string.title06)))
            list.add(ShoppingItem(getDrawable(R.drawable.image07)!!,getString(R.string.title07)))
            list.add(ShoppingItem(getDrawable(R.drawable.image08)!!,getString(R.string.title08)))
            list.add(ShoppingItem(getDrawable(R.drawable.image09)!!,getString(R.string.title09)))
            list.add(ShoppingItem(getDrawable(R.drawable.image10)!!,getString(R.string.title10)))
        }
        //list.add(ShoppingItem(ResourcesCompat.getDrawable(resources, R.drawable.image01, null)!!,getString(R.string.title01)))
        // 최소 API 19 이상 가능
        // https://claire-design.tistory.com/entry/android-Deprecated%EB%90%9C-%ED%95%A8%EC%88%98-%EB%8D%94-%EC%9D%B4%EC%83%81-%EC%82%AC%EC%9A%A9%EB%90%98%EC%A7%80-%EC%95%8A%EB%8A%94-getDrawable%EA%B3%BC-getColor


        val adapter = RecyclerAdapter(list)
        recyclerView.adapter=adapter

        recyclerView.addItemDecoration( //구분선
            DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        )
    }
}
