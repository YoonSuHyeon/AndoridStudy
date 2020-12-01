package com.example.todolistex

import android.icu.text.MessageFormat.format
import android.text.format.DateFormat
import android.text.format.DateFormat.format
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.realm.OrderedRealmCollection
import io.realm.RealmBaseAdapter

import java.util.*

class TodoListAdapter(data: OrderedRealmCollection<Todo>?) : RealmBaseAdapter<Todo>(data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vh:CViewHolder
        val view:View

        if(convertView == null){
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_todo,parent,false)
            vh = CViewHolder(view)
            view.tag = vh
        }else{
            view = convertView
            vh = view.tag as CViewHolder

        }

        if(adapterData != null){
            val item = adapterData!![position]
            vh.textTextView.text = item.title
            vh.dateTextView.text = DateFormat.format("yyyy/MM/dd",item.date)
        }

        return view
    }
}

class CViewHolder(view:View){
    val dateTextView:TextView = view.findViewById(R.id.text1)
    val textTextView:TextView = view.findViewById(R.id.text2)
}}