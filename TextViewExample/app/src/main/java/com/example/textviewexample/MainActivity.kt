package com.example.textviewexample

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Custom Font 적용
        val typeface = Typeface.createFromAsset(assets,"xmas.ttf")
        fontView.typeface = typeface

        //CheckBox 이벤트
        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                checkbox.text = "is Checked"
            }else{
                checkbox.text = "is unChecked"
            }
        }
    }
}
