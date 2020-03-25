package com.example.customviewexample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity(),OnMyChangeListener {
    private var barView: View?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val plusMinusView :MyPlusMinusView = findViewById(R.id.customView)
        barView=findViewById(R.id.barView)

        plusMinusView.setOnMyChangeListener(this)
    }

    override fun onChange(value: Int) {
        when {
            value<0 -> {
                barView!!.setBackgroundColor(Color.RED);
            }
            value<30 -> {
                barView!!.setBackgroundColor(Color.YELLOW);
            }
            value<60 -> {
                barView!!.setBackgroundColor(Color.BLUE);
            }
            else -> {
                barView!!.setBackgroundColor(Color.GREEN);
            }
        }
    }
}
