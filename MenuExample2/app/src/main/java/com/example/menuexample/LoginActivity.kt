package com.example.menuexample

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    val folderName = Environment.getExternalStorageDirectory().absolutePath+"/TestUser"
    val fileName="user.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title="초기화면"
        btn_login.setOnClickListener {
            if(login()){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"로그인 실패",Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.login,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.menu_Id_pwd-> {
                val intent = Intent(this,FindIdPwdActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_signUp->{
                val intent = Intent(this,SignUpActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_contact->{
                val intent =Intent(Intent.ACTION_DIAL)
                intent.data= Uri.parse("tel:010-7469-4047")
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
    fun login():Boolean{
        val openFileInput = openFileInput("user.txt")
        var tempString:List<String>

        openFileInput.bufferedReader().use {br ->
                tempString=br.readLines()
        }
        tempString.forEach {
            val tt = it.split("/")
            if(tt[0] == et_id.text.toString() && tt[1] == et_pwd.text.toString()){
                return true
            }
        }
        return false

    }
}