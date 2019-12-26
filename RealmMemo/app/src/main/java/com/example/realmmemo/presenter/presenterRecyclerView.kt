package com.example.realmmemo.presenter

import com.example.realmmemo.view.itemView

import com.example.realmmemo.model.Todo

class presenterRecyclerView {
    var listItems:List<Todo>
    lateinit var item:Todo
    constructor(listitems:List<Todo>){
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