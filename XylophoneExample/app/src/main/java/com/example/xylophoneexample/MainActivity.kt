package com.example.xylophoneexample

import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        SoundPool.Builder().setMaxStreams(8).build()
    } else {
        SoundPool(8, AudioManager.STREAM_MUSIC, 0)
    }

    private val sounds = listOf(
        Pair(R.id.do1, R.raw.do1),
        Pair(R.id.re, R.raw.re),
        Pair(R.id.mi, R.raw.mi),
        Pair(R.id.fa, R.raw.fa),
        Pair(R.id.sol, R.raw.sol),
        Pair(R.id.la, R.raw.ra),
        Pair(R.id.si,R.raw.si),
        Pair(R.id.do2,R.raw.do2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sounds.forEach {
            tune(it)
        }
    }

    private fun tune(it: Pair<Int, Int>) {
        val sountId=soundPool.load(this,it.second,1)
        val findViewById = findViewById<TextView>(it.first)
        findViewById.setOnClickListener {
            soundPool.play(sountId,1.0f,1.0f,0,0,1.0f)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}