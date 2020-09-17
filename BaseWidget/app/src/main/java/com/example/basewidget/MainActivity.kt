package com.example.basewidget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.radio_example.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main) //기본
        setContentView(R.layout.radio_example) //radioButton 사용

        /*MainActivity일 때 사용
        button.setOnClickListener {
            Toast.makeText(this,"버튼을 클릭했습니다.",Toast.LENGTH_SHORT).show()
        }*/

        /*redio_example*/
        male.setOnClickListener {
            Toast.makeText(this,"남성입니다.",Toast.LENGTH_SHORT).show()
        }

        female.setOnClickListener {
            Toast.makeText(this,"여성입니다.",Toast.LENGTH_SHORT).show()
        }
    }
}