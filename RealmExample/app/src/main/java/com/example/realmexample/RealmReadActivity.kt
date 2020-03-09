package com.example.realmexample

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm


class RealmReadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm_read)

        val titleView: TextView = findViewById(R.id.realm_read_title)
        val contentView: TextView = findViewById(R.id.realm_read_content)

        val intent = intent
        val title = intent.getStringExtra("title")

        val mRealm = Realm.getDefaultInstance()
        val vo =
            mRealm.where(MemoVO::class.java).equalTo("title", title).findFirst()
        titleView.text = vo!!.title
        contentView.text = vo.content
    }
}
