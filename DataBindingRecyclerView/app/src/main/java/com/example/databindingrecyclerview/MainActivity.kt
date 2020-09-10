package com.example.databindingrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databindingrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.activity=this@MainActivity
        val profileAdapter = ProfileAdapter(this)
        binding.mainRcv.layoutManager= LinearLayoutManager(this)
        binding.mainRcv.adapter= profileAdapter
        profileAdapter.data= listOf(ProfileData("nn",25),ProfileData("mm",25))
        profileAdapter.notifyDataSetChanged()
    }
    fun btnClick(view: View){
        Toast.makeText(this,"Click",Toast.LENGTH_SHORT).show()


    }


}