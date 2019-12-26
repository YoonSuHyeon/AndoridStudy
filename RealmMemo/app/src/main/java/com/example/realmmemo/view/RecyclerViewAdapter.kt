package com.example.realmmemo.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.realmmemo.R
import com.example.realmmemo.presenter.presenterRecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.rec_view_item.view.*

class RecyclerViewAdapter(private val presenter: presenterRecyclerView, private val context : Context):RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.rec_view_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return  presenter.getRecyclerViewItemSize()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.onBindRecyclerView(position,holder)
    }

    class  ViewHolder(view: View):RecyclerView.ViewHolder(view),
        itemView {
        var itemTxt = view.tv_item
        override fun setItemText(txt: String?) {
           itemTxt.text = txt

        }
    }

}