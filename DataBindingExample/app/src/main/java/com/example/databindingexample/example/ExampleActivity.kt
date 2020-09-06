package com.example.databindingexample.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.databindingexample.R
import com.example.databindingexample.databinding.ActivityExampleBinding
import com.example.databindingexample.databinding.ActivityMainBinding
import com.example.databindingexample.databinding.ItemMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding =
            DataBindingUtil.setContentView<ActivityExampleBinding>(this, R.layout.activity_example)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val networkService = RetrofitFactory.create()
        networkService.getList(QUERY, API_KEY, 1, 10)
            ?.enqueue(object : Callback<PageListModel?> {
                override fun onResponse(
                    call: Call<PageListModel?>,
                    response: Response<PageListModel?>
                ) {
                    if (response.isSuccessful) {
                        val adapter = response.body()?.articles?.let { MyAdapter(it) }
                        binding.recyclerView.adapter = adapter
                    }
                }

                override fun onFailure(
                    call: Call<PageListModel?>,
                    t: Throwable
                ) {
                }
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
        private const val QUERY = "movies"
        private const val API_KEY = "42ae94278ea943948945616f4b661987"


        @BindingAdapter("bind:publishedAt")
        fun publishedAt(view: TextView, date: String) {
            view.text = AppUtils.getDate(date) + "at" + AppUtils.getTime(date)
        }

        @BindingAdapter("bind:urlToImage")
        fun urlToImage(view: ImageView, url: String) {
            MyApplication.appContext?.let {
                Glide.with(it).load(url).override(250, 200).into(view)
            }
        }


    }
}