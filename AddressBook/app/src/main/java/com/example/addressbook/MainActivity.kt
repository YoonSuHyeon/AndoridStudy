package com.example.addressbook

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lab2_btn.setOnClickListener(this)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !== PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 100)
        }
    }

    override fun onClick(v: View) {
        if (v == lab2_btn) {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = Uri.parse("content://com.android.contacts/data/phones")
            startActivityForResult(intent,10)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 10 && resultCode == Activity.RESULT_OK){
            val id =Uri.parse(data!!.dataString).lastPathSegment
            val cursor =contentResolver.query(ContactsContract.Data.CONTENT_URI,
            arrayOf(ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER),ContactsContract.Data._ID+ "=" +id , null,null )
            cursor!!.moveToFirst()
            val name =cursor!!.getString(0)
            val phone = cursor.getString(1)

            lab2_name.text = name
            lab2_phone.text = phone
        }

    }

}