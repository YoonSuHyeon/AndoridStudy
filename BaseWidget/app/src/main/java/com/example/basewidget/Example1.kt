package com.example.basewidget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_example1.*

class Example1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example1)
        Btn_Add.setOnClickListener {
            val num1=Edit1.text.toString().toInt()
            val num2 =Edit2.text.toString().toInt()
            val result =num1+num2
            TextResult.text= "계산 결과 : $result"
        }
        Btn_Sub.setOnClickListener {
            val num1=Edit1.text.toString().toInt()
            val num2 =Edit2.text.toString().toInt()
            val result =num1-num2
            TextResult.text= "계산 결과 : $result"
        }
        Btn_Mul.setOnClickListener {
            val num1=Edit1.text.toString().toInt()
            val num2 =Edit2.text.toString().toInt()
            val result =num1*num2
            TextResult.text= "계산 결과 : $result"
        }
        Btn_Div.setOnClickListener {
            val num1=Edit1.text.toString().toInt()
            val num2 =Edit2.text.toString().toInt()
            val result =num1/num2
            TextResult.text= "계산 결과 : $result"
        }
    }
}