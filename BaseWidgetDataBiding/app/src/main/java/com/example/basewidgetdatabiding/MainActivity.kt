package com.example.basewidgetdatabiding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.basewidgetdatabiding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var ActivityMainBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding=DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        ActivityMainBinding.data=this

    }
    fun click(view:View){
        Toast.makeText(this,"버튼을 클릭했습니다.",Toast.LENGTH_SHORT).show()
    }
}