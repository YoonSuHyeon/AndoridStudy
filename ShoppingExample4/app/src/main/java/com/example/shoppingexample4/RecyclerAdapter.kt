package com.example.shoppingexample4

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class RecyclerAdapter(private val items:ArrayList<ShoppingItem>,private  val mContext:Context)
    :RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view = v

        fun bind(listener: View.OnClickListener, item: ShoppingItem) {
            view.thumbnail.setImageDrawable(item.image)
            view.title.text = item.title
            view.setOnClickListener(listener)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return RecyclerAdapter.ViewHolder(inflatedView)
    }

    override fun getItemCount()=items.size


    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val item= items[position]
        val listener = View.OnClickListener {

            Toast.makeText(it.context,"Clicked:${item.title}", Toast.LENGTH_SHORT).show()
            mOnPopupClick(it,item.title)
        }
        holder.apply {
            bind(listener,item)
            itemView.tag=item
        }
    }

    fun mOnPopupClick(v :View,title:String){
        var intent = Intent(mContext,PopupActivity::class.java)
        intent.putExtra("data",title)
        mContext.startActivity(intent)
    }
}
