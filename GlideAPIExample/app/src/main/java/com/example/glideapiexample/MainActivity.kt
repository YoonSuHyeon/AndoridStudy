package com.example.glideapiexample


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Glide.with(this).asGif().load(R.raw.loading).diskCacheStrategy(DiskCacheStrategy.ALL).override(200,200).into(lab3_gif)
        val url ="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20120806_128%2Fstory7310_13442359573394SGXU_JPEG%2F7485901800_bc0186a92b_z_large.jpg&type=sc960_832"
        Glide.with(this).load(url).override(400,400).error(R.drawable.error).into(lab3_network)
    }
}