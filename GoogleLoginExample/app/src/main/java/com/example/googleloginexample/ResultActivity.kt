package com.example.googleloginexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        var nickname=intent.getStringExtra("nickname")
        var photoUrl=intent.getStringExtra("photoUrl")

        tv_result.text=nickname
        Glide.with(this).load(photoUrl).into(iv_profile)

    }
}
