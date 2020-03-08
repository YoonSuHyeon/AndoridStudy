package com.example.brunchexample

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onBackPressed() { //back 버튼을 이용
        val builder =AlertDialog.Builder(this)
        builder.setMessage("작성중인 내용을 저장하지 않고 나가시겠습니까?")
        builder.setPositiveButton("확인",dialogListener)
        builder.setNegativeButton("취소",null)
        val alertDialog = builder.create()
        alertDialog.show()
    }
    val dialogListener=DialogInterface.OnClickListener { dialogInterface, i ->
        finish()
    }
}
