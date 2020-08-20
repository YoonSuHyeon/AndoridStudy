package com.example.serviceexample

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private var filePath: String? = null
    private var runThread = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lab1_play.setOnClickListener(this)
        lab1_stop.setOnClickListener(this)
        lab1_title.text="music.mp3"
        lab1_stop.isEnabled=false


       filePath="/sdcard/Download/Music/music.mp3"// sd 카드 경로를 하드코딩함


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !== PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 100)
        }

        registerReceiver(receiver, IntentFilter("com.example.PLAY_TO_ACTIVITY"))
        val intent =Intent(this,PlayService::class.java)
        intent.putExtra("filePath",filePath)
        startService(intent)
    }


    override fun onClick(p0: View?) {
      if(p0==lab1_play){
          val intent = Intent("com.example.PLAY_TO_SERVICE")
          intent.putExtra("mode","start")
          sendBroadcast(intent)
          runThread=true
          val thread = ProgressThread()
          thread.start()
          lab1_play.isEnabled = false
          lab1_stop.isEnabled= true

      }else if(p0==lab1_stop){
          val intent =Intent("com.example.PLAY_TO_SERVICE")
          intent.putExtra("mode","stop")
          sendBroadcast(intent)
          runThread=false
          lab1_progress.progress=0

          lab1_play.isEnabled=true
          lab1_stop.isEnabled=false
      }
    }

    var receiver =  object:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val mode=intent?.getStringExtra("mode")
            if(mode !=null){
                if(mode=="start"){
                    val duration  = intent.getIntExtra("duration",0)
                    lab1_progress.max = duration
                    lab1_progress.progress=0
                }else if(mode == "stop"){
                    runThread =false
                }else if(mode=="restart"){
                    val duration= intent.getIntExtra("duration",0)
                    val current=intent.getIntExtra("current",0)
                    lab1_progress.max=duration
                    lab1_progress.progress=current
                    runThread = true
                    val thread = ProgressThread()
                    thread.start()

                    lab1_play.isEnabled=false
                    lab1_stop.isEnabled=true
                }
            }
        }
    }



    inner class ProgressThread : Thread() {
        override fun run() {
            while (runThread) {
                lab1_progress.incrementProgressBy(1000)
                SystemClock.sleep(1000)
                if (lab1_progress.progress ==lab1_progress.max) {
                    runThread = false
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}