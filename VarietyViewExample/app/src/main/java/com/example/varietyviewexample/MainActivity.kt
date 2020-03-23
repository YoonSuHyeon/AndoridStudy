package com.example.varietyviewexample


import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val datas =
            resources.getStringArray(R.array.spinner_array)
        val aa =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, datas)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = aa
        val autoCompleteTextView =
            findViewById(R.id.auto) as AutoCompleteTextView
        val autoDatas =
            resources.getStringArray(R.array.auto_array)
        val autoAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            autoDatas
        )
        autoCompleteTextView.setAdapter(autoAdapter)
        progressBar = findViewById<View>(R.id.progress) as ProgressBar
        val thread = ProgressThread()
        thread.start()
    }

    inner class ProgressThread : Thread() {
        override fun run() {
            for (i in 0..9) {
                SystemClock.sleep(1000)
                progressBar.incrementProgressBy(10)
                progressBar.incrementSecondaryProgressBy(15)
            }
        }
    }
}
