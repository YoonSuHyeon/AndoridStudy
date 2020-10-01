package com.example.masterpieceexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title="갤러리"
        loadData()
        imageView.setOnClickListener {//별이 빛나는 밤
            saveData(textView.text.toString())
            toast(textView.text.toString())
            startActivity<ResultActivity>(
                "masterpiece" to textView.text.toString()
            )

        }
        imageView2.setOnClickListener {//모나리자
            saveData(textView3.text.toString())
            toast(textView3.text.toString())
            startActivity<ResultActivity>(
                "masterpiece" to textView3.text.toString()
            )

        }


    }

    private  fun loadData(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val masterpiece = pref.getString("KEY_MASTERPIECE","")

        if(masterpiece!=""){
            tv_preview.text="마지막 클릭 명화는 $masterpiece 입니다."
        }

    }
    private  fun saveData(masterpiece:String){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putString("KEY_MASTERPIECE",masterpiece)
            .apply()
    }

}