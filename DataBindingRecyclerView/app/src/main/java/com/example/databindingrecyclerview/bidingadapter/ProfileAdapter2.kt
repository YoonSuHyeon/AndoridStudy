package com.example.databindingrecyclerview.bidingadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.databindingrecyclerview.databinding.Item2Binding

class ProfileAdapter2(private val context : Context) : RecyclerView.Adapter<BaseVH2>() {
    var data = listOf<ProfileData2>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH2 {
        val binding = Item2Binding.inflate(LayoutInflater.from(context),parent,false)

        return BaseVH2(binding)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: BaseVH2, position: Int) {
        holder.onBind(data[position])
    }
}