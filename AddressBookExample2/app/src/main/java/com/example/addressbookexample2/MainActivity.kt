package com.example.addressbookexample2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var name: EditText
    private lateinit var phone:EditText
    private lateinit var email:EditText
    private lateinit var add:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name=findViewById(R.id.et_name)
        phone=findViewById(R.id.et_phone)
        email=findViewById(R.id.et_email)

        add=findViewById(R.id.bt_add)

        add.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if(view==bt_add){
            if(name.text.isEmpty()){
                Toast.makeText(this,"이름이 등록 되지 않았습니다.",Toast.LENGTH_SHORT).show()
            }else{
               //DB에 데이터를 삽입하고 결과화면 Activity 로 화면 전환
                val nametext=name.text.toString()
                val phonetext=phone.text.toString()
                val emailtext=email.text.toString()
                val helpler=DBHelper(this)
                val db = helpler.writableDatabase
                db.execSQL(
                    "insert into tb_contact (name,phone,email) values(?,?,?)",
                    arrayOf(nametext,phonetext,emailtext)
                )
                db.close()

                val intent = Intent(this,ResultActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
