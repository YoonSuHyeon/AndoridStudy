package com.example.realmexample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var titleView : EditText
    private lateinit var contentView : EditText
    private lateinit var addBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        titleView=findViewById(R.id.realm_add_title)
        contentView=findViewById(R.id.realm_add_content)
        addBtn=findViewById(R.id.realm_add_btn)
        addBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val title = titleView.text.toString()
        val content = contentView.text.toString()
        Realm.init(this)

        val mRealm = Realm.getDefaultInstance()

        mRealm.executeTransaction { realm ->
            val vo = realm.createObject(MemoVO::class.java)
            vo.title = title
            vo.content = content
        }
        val intent = Intent(this, RealmReadActivity::class.java)
        intent.putExtra("title", title)
        startActivity(intent)
    }
}
