package com.example.shoppingexample2


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind_image")
fun bindImage(view: ImageView, res: Int?) {
    Glide.with(view.context)
        .load(res)
        .into(view)
}
