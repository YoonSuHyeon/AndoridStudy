package com.example.broadcastreceiver

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView

class DialogActivity : Activity(), View.OnClickListener{
    var finishBtn: ImageView? = null
    var numberView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_dialog)
        numberView = findViewById<TextView>(R.id.lab1_phone_number)
        finishBtn = findViewById<ImageView>(R.id.lab1_remove_icon)
        finishBtn!!.setOnClickListener(this)
        val intent: Intent = getIntent()
        val number = intent.getStringExtra("number")
        numberView!!.text = number
    }

    override fun onClick(v: View?) {
        finish()
    }
}