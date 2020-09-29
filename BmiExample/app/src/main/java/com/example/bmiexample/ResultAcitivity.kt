package com.example.bmiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result_acitivity.*
import org.jetbrains.anko.toast
import kotlin.math.pow

class ResultAcitivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_acitivity)


        //몸무게(kg) ÷ (신장(m) × 신장(m))
        val weight =intent.getIntExtra("weight",0)
        val height =intent.getIntExtra("height",0)
        val bmi = weight/Math.pow (height / 100.0,2.0)

        when{
            bmi>= 35 -> resultTextView.text="고도비만"
            bmi>= 30 -> resultTextView.text="2단계 고도비만"
            bmi>= 25 -> resultTextView.text="1단계 고도비만"
            bmi>= 23 -> resultTextView.text="과체중"
            bmi>= 18.5 -> resultTextView.text="정상"
            else -> resultTextView.text="저체중"

        }

        when{
            bmi >=23 ->
                imageView.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24)
            bmi >=18.5 ->
                imageView.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_alt_24)

            else ->
                imageView.setImageResource(R.drawable.ic_baseline_sentiment_dissatisfied_24)


        }
        toast("키는 $height, 몸무게는 $weight")


        //bmi 계산후 이미지를 변경

        returnButton.setOnClickListener {
            finish()
        }
    }
}