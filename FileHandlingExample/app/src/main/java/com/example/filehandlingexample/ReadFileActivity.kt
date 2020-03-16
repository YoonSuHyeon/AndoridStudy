package com.example.filehandlingexample

import android.os.Bundle
import android.os.Environment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.File
import java.io.FileReader


class ReadFileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_file)

        val textView: TextView = findViewById(R.id.fileResult)

        val file =
            File(Environment.getExternalStorageDirectory().absolutePath + "/myApp/myfile.txt")
        try {
            val reader = BufferedReader(FileReader(file))
            val buffer = StringBuffer()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                buffer.append(line)
            }
            textView.text = buffer.toString()
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
