package com.example.databindingrecyclerview.bidingadapter

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databindingrecyclerview.R
import com.example.databindingrecyclerview.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        binding.activity2 = this
        val profileAdapter2 = ProfileAdapter2(this)
        binding.mainRcv.layoutManager = LinearLayoutManager(this)
        binding.mainRcv.adapter = profileAdapter2
        profileAdapter2.data = listOf(
            ProfileData2(
                "https://search.pstatic.net/common/?src=http%3A%2F%2Fcafefiles.naver.net%2FMjAyMDAxMTVfMjAx%2FMDAxNTc5MDI1NDI2MzEx.7yjoH28cUtaTE4hiTl3cvUV2oigG1hGdzP6WBpZuuSYg.zu_bEDpkAJp8MOTFbfnyW0nBGitvbgaogez7eYqCZwgg.JPEG%2Fwww_google_co_kr_20150813_124103.jpg&type=sc960_832",
                "nn",
                25
            ), ProfileData2("asdf ", "mm", 25)
        )
        profileAdapter2.notifyDataSetChanged()
    }

    fun btnClick(view: View) {
        Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()


    }
}