package com.example.menuexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_find_id_pwd.*
import kotlinx.android.synthetic.main.activity_find_id_pwd.et_id
import kotlinx.android.synthetic.main.activity_login.*

class FindIdPwdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_id_pwd)
        title="아이디비밀번호 찾기"
        btn_id.setOnClickListener {
            val id=findId()
            if(id==""){
                tv_result.text = "없음"
            }else{
                tv_result.text = id
            }
        }
        btn_pwd.setOnClickListener {
            val pwd = findPwd()
            if(pwd ==""){
                tv_result.text = "없음"
            }else{
                tv_result.text = pwd
            }

        }
    }
    fun findId():String{
        val openFileInput = openFileInput("user.txt")
        var tempString:List<String>

        openFileInput.bufferedReader().use {br ->
            tempString=br.readLines()
        }
        tempString.forEach {
            val tt = it.split("/")
            if(tt[2] == et_name.text.toString()){
                return tt[0]
            }
        }
        return ""

    }

    fun findPwd():String{
        val openFileInput = openFileInput("user.txt")
        var tempString:List<String>

        openFileInput.bufferedReader().use {br ->
            tempString=br.readLines()
        }
        tempString.forEach {
            val tt = it.split("/")
            if(tt[0] == et_id.text.toString()){
                return tt[1]
            }
        }
        return ""

    }
}