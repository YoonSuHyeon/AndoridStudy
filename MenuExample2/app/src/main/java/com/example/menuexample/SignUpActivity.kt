package com.example.menuexample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.io.*

class SignUpActivity : AppCompatActivity() {
    val folderName:String = Environment.getExternalStorageDirectory().absolutePath+"/TestUser"
    val fileName="user.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        title= "회원가입"

        btn_sign.setOnClickListener {
            saveUser()
        }

    }

    fun saveUser(){

        val id=et_userId.text.toString()
        val name=et_userName.text.toString()
        val pwd=et_userPwd.text.toString()
        if(writeTxt(id,name,pwd)){
            Toast.makeText(this,"회원가입 완료",Toast.LENGTH_LONG).show()
            onBackPressed()
        }else{
            Toast.makeText(this,"회원가입 실패",Toast.LENGTH_LONG).show()
        }

    }
    fun writeTxt(id:String,name:String,pwd:String):Boolean {
        //MODE_APPEND 이어서 적기 , MODE_PRIVATE 덮어쓰기
        if(id =="" || name =="" || pwd =="") return false
        val openFileOutput = openFileOutput("user.txt", Context.MODE_APPEND)
        openFileOutput.bufferedWriter().use {
            it.write("$id/$pwd/$name\n")
        }
        return true


    }
}
