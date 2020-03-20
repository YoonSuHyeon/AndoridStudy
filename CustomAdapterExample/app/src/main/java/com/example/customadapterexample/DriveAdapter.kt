package com.example.customadapterexample

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



class DriveAdapter( context:Context, var resId: Int,var datas: ArrayList<DriveVO>) : ArrayAdapter<DriveVO>(context, resId) {

    override fun getCount(): Int {
        return datas.size
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(resId, null)
            val holder = DriveHolder(convertView)
            convertView.tag = holder
        }

        val holder = convertView!!.tag as DriveHolder

        val typeImageView = holder.typeImageView
        val titleView = holder.titleView
        val dateView = holder.dateView
        val menuImageView = holder.menuImageView

        val vo = datas[position]

        titleView.setText(vo.title)
        dateView.setText(vo.date)

        if (vo.type.equals("doc")) {
            typeImageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.ic_type_doc,
                    null
                )
            )

        } else if (vo.type.equals("file")) {
            typeImageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.ic_type_file,
                    null
                )
            )

        } else if (vo.type.equals("img")) {
            typeImageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.ic_type_image,
                    null
                )
            )

        }

        menuImageView.setOnClickListener {
                val toast = Toast.makeText(context, vo.title + " menu click", Toast.LENGTH_SHORT)
                toast.show()
        }

        return convertView
    }
}