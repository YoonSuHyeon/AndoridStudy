package com.example.shoppingexample2

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingexample2.databinding.ItemShopBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val items = arrayListOf<ShopItem>()
        items.add(ShopItem("강아지0",R.drawable.sample_0))
        items.add(ShopItem("강아지1",R.drawable.sample_1))
        items.add(ShopItem("강아지2",R.drawable.sample_2))
        items.add(ShopItem("강아지3",R.drawable.sample_3))
        items.add(ShopItem("강아지4",R.drawable.sample_4))
        items.add(ShopItem("강아지5",R.drawable.sample_5))
        items.add(ShopItem("강아지6",R.drawable.sample_6))
        items.add(ShopItem("강아지7",R.drawable.sample_7))

        rv_shopList.apply {
            layoutManager= GridLayoutManager(this@MainActivity,2)
            adapter=ShopAdapter(items){
                Toast.makeText(this@MainActivity,"$it",Toast.LENGTH_SHORT).show()
            }

        }
    }
}
class ShopAdapter(val items : List<ShopItem>,
                  private val clickListener:(shopItem:ShopItem)->Unit):RecyclerView.Adapter<ShopAdapter.ShopViewHolder>(){
    class ShopViewHolder(val binding: ItemShopBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shop,parent,false)
        val viewHolder = ShopViewHolder(ItemShopBinding.bind(view))
        view.setOnClickListener {
            clickListener.invoke(items[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
            holder.binding.shop=items[position]
         }

}


