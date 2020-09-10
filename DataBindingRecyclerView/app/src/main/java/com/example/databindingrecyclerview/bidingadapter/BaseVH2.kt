package com.example.databindingrecyclerview.bidingadapter

import androidx.recyclerview.widget.RecyclerView
import com.example.databindingrecyclerview.databinding.Item2Binding


class BaseVH2(val binding : Item2Binding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(data:ProfileData2){
        binding.user2=data
    }

}