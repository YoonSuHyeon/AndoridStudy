package com.example.mvvmexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.mvvmexample.databinding.ActivityMainBinding
import com.example.mvvmexample.databinding.ItemMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        var viewModel=ViewModelProviders.of(this).get(MyViewModel::class.java)


       viewModel.getNews().observe(this, Observer{
               val adapter = MyAdapter(it)
               binding.recyclerView.adapter = adapter
       })

    }
    inner class ItemViewHolder(var binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
    inner class MyAdapter //~~~~~~~~~~~~~~~~~~~~~~~~~~~
        (var articles: List<ItemModel>) :
        RecyclerView.Adapter<ItemViewHolder?>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            var binding =
                ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return articles.size
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val model = articles[position]
            holder.binding.item = model
        }
    }
    companion object {



        @BindingAdapter("bind:publishedAt")
        @JvmStatic
        fun publishedAt(view: TextView, date: String) {
            view.text = AppUtils.getDate(date) + "at" + AppUtils.getTime(date)
        }

        @BindingAdapter("bind:urlToImage")
        @JvmStatic
        fun urlToImage(view: ImageView?, url: String?) {
            MyApplication.getAppContext()?.let {
                Glide.with(it).load(url).override(250, 200).into(view!!)
            }
        }


    }
}