package com.example.thebuttoncounterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var bomb= Random.nextInt(3)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            if(editText.text.toString().toInt()==bomb)
                textView.append("\n ${editText.text}  ==  $bomb")
            else
                textView.append("\n ${editText.text}  !=  $bomb")
            bomb=Random.nextInt(3)

        }
    }
}
