package com.example.masterpieceexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.noHistory
import org.jetbrains.anko.startActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        title = "영화"

        val masterpiece = intent.getStringExtra("masterpiece")

        if (masterpiece == "Starry Night") {
            tv_title.text=masterpiece
            tv_artist.text="고흐"
            imageView3.setImageResource(R.drawable.image1)
            tv_explanation.setText(R.string.Starry_Night)
        }else{
            tv_title.text=masterpiece
            tv_artist.text="레오나르도 다 빈치"
            imageView3.setImageResource(R.drawable.image2)
            tv_explanation.setText(R.string.Mona_Lisa)
        }

        btn_back.setOnClickListener {
            finish()

            val intent =intentFor<MainActivity>().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)


        }
    }
}