package com.example.sqliteexample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var titleView :EditText
    private lateinit var contentView :EditText
    private lateinit var addBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        titleView=findViewById(R.id.add_title)
        contentView=findViewById(R.id.add_content)
        addBtn=findViewById(R.id.add_btn)
        addBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?){
        val title =titleView.text.toString()
        val content =contentView.text.toString()
        val helper=DBHelper(this)
        val db=helper.writableDatabase
        db.execSQL(
            "insert into tb_memo (title, content) values (?,?)",
            arrayOf(title, content)
        )
        db.close()

        val intent = Intent(this, ReadDBActivity::class.java)
        startActivity(intent)
    }
}
