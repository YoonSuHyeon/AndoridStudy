package com.example.shoppingexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingexample.databinding.ItemBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val items= arrayListOf<Item>()
        for(i in 0..30){
            items.add(Item("상품 $i",i))
        }
        rv_item.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=ShopAdapter(items){shop->
                Toast.makeText(this@MainActivity,"$shop ",Toast.LENGTH_SHORT).show()
            }
        }

    }
}
class ShopAdapter(val items :List<Item>,
                  private  val clickListener :(citem:Item)->Unit):RecyclerView.Adapter<ShopAdapter.ShopViewHolder>(){
    class ShopViewHolder(val binding:ItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item,parent,false)

        val viewHolder=ShopViewHolder(ItemBinding.bind(view))
        view.setOnClickListener {
            clickListener.invoke(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int =items.size

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.binding.item=items[position]
    }
}
