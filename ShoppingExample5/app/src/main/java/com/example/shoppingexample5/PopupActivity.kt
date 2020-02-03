package com.example.shoppingexample5

import android.app.Activity
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import kotlinx.android.synthetic.main.activity_popup.*

class PopupActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_popup)
        val intent = getIntent().getStringExtra("data")

        txtText.text=intent
    }
    fun mOnClose(v: View){
        finish()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event?.action== MotionEvent.ACTION_OUTSIDE){
            return false
        }
        return true
    }
}