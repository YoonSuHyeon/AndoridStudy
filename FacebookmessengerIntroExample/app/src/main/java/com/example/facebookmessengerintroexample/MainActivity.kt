package com.example.facebookmessengerintroexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_ok.setOnClickListener {
            Toast.makeText(this,"ok button click",Toast.LENGTH_SHORT).show()
        }
    }
}
