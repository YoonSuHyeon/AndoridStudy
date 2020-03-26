package com.example.phoneapplogscreenexample

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat


class CallLogAdapter(context:Context,var resId:Int,var datas:ArrayList<CallLogVO>):ArrayAdapter<CallLogVO>(context,resId) {



    override fun getCount(): Int {
        return  datas.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(resId, null)
            val wrapper = CallLogWrapper(convertView)
            convertView.tag = wrapper
        }
        val wrapper=convertView!!.tag as CallLogWrapper

        val personImageView: ImageView = wrapper.personImageView
        val nameView = wrapper.nameView
        val dateView = wrapper.dateView
        val diralerImageView: ImageView = wrapper.dialerImageView

        val vo = datas[position]

        nameView.text = vo.name
        dateView.text=vo.date

        if(vo.photo=="yes"){
            personImageView.setImageDrawable(ResourcesCompat.getDrawable(context.resources, R.drawable.hong, null))
        }else{
            personImageView.setImageDrawable(ResourcesCompat.getDrawable(context.resources,R.drawable.ic_person,null))
        }

        if (vo.phone != "") {
            diralerImageView.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_CALL
                intent.data = Uri.parse("tel:" + vo.phone)
                context.startActivity(intent)
            }
        }
        return convertView

    }
}