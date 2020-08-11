package com.example.activitylifecycleexample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() , View.OnClickListener{
    var count = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        detail_btn.setOnClickListener(this)
        if(savedInstanceState != null) //화면전환이 되어도 바뀌지않게 하는법
        {
            count=savedInstanceState.getInt("data2")
        }



    }

    override fun onClick(v: View?) {
        count++
        detail_count.text=count.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("data2",count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)


        val data2 =savedInstanceState.getInt("data2")

        detail_count.text=data2.toString()
    }
}
