package com.example.realmmemo.presenter

import com.example.realmmemo.view.itemView
import com.example.realmmemo.model.Memo

class presenterRecyclerView {
    var listItems:List<Memo>
    lateinit var item:Memo
    constructor(listitems:List<Memo>){
        this.listItems=listitems
    }
    fun onBindRecyclerView(position: Int,holder: itemView){
        item = listItems[position]
        holder.setItemText(item.itemName)
    }
    fun getRecyclerViewItemSize():Int{
        return listItems.size
    }
}