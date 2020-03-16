package com.example.filehandlingexample

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileWriter


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var contentView: EditText? = null
    var btn: Button? = null

    var fileReadPermission = false
    var fileWritePermission = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        contentView = findViewById(R.id.content)
        btn = findViewById(R.id.btn)
        btn!!.setOnClickListener(this)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            fileReadPermission = true
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            fileWritePermission = true
        }
        if (!fileReadPermission || !fileWritePermission) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), 200
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 200 && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) fileReadPermission = true
            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) fileWritePermission = true
        }
    }

    override fun onClick(v: View?) {
        val content = contentView!!.text.toString()
        if (fileReadPermission && fileWritePermission) {
            val writer: FileWriter
            try {
                val dirPath =
                    Environment.getExternalStorageDirectory().absolutePath + "/myApp/"
                val dir = File(dirPath)
                if (!dir.exists()) {
                    dir.mkdirs()
                    Log.d("dddd",dir.exists().toString())
                }
                val file = File("$dir/myfile.txt")
                if (!file.exists()) {
                    file.createNewFile()
                }
                writer = FileWriter(file, true)
                writer.write(content)
                writer.flush()
                writer.close()
                val intent = Intent(this, ReadFileActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            showToast("permission 이 부여 안되어 기능 실행이 안됩니다.")
        }
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
    }
}
