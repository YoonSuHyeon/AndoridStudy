package com.example.usernotificationexample

import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() , View.OnClickListener {


    private lateinit var vibrationBtn: Button
    private lateinit var systembeepBtn :Button
    private lateinit var customsoundBtn :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vibrationBtn=findViewById(R.id.btn_vibration)
        systembeepBtn=findViewById(R.id.btn_system_beep)
        customsoundBtn=findViewById(R.id.btn_custom_sound)


        vibrationBtn.setOnClickListener(this)
        systembeepBtn.setOnClickListener(this)
        customsoundBtn.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        if(v==vibrationBtn){
            val vib = getSystemService(VIBRATOR_SERVICE) as (Vibrator)
            vib.vibrate(1000)
        }else if(v==systembeepBtn){
            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(applicationContext,notification)
            ringtone.play()
        }else if(v ==customsoundBtn){
            var player = MediaPlayer.create(this,R.raw.fallbackring)
            player.start()
        }
    }
}



