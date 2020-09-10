package com.example.databindingrecyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.databindingrecyclerview.databinding.ItemBinding

class BaseVH(val binding :ItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(data:ProfileData){
        binding.user=data
    }

}