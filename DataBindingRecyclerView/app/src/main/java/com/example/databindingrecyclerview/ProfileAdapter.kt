package com.example.databindingrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.databindingrecyclerview.databinding.ItemBinding

class ProfileAdapter(private val context : Context) : RecyclerView.Adapter<BaseVH>() {
    var data = listOf<ProfileData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH {
        val binding = ItemBinding.inflate(LayoutInflater.from(context),parent,false)

        return BaseVH(binding)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: BaseVH, position: Int) {
        holder.onBind(data[position])
    }
}