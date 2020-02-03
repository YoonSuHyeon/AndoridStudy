package com.example.musicexample1

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
   var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //재생 버튼 눌렀을때
        btn_play.setOnClickListener {
            mediaPlayer=MediaPlayer.create(this,R.raw.music)
            mediaPlayer!!.start()
        }
        btn_stop.setOnClickListener {
            if(mediaPlayer!!.isPlaying) {
                mediaPlayer!!.stop()
                mediaPlayer!!.reset()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mediaPlayer !=null){
            mediaPlayer!!.release()
            mediaPlayer=null
        }
    }
}
