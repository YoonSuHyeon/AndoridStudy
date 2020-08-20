package com.example.serviceexample

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import java.lang.Exception
import java.lang.UnsupportedOperationException

class PlayService : Service() , MediaPlayer.OnCompletionListener {
    private var player:MediaPlayer? =null
    private lateinit var filePath:String
    private var receiver = object:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val mode = intent?.getStringExtra("mode")
            if(mode !=null){
                if(mode=="start"){
                    try{
                        if(player != null && player!!.isPlaying){
                            player!!.stop()
                            player!!.release()
                            player = null
                        }
                        player= MediaPlayer()
                        player!!.setDataSource(filePath)
                        player!!.prepare()
                        player!!.start()

                        val aIntent =Intent("com.example.PLAY_TO_ACTIVITY")
                        aIntent.putExtra("mode","start")
                        aIntent.putExtra("duration",player!!.duration)
                        sendBroadcast(aIntent)
                    }catch(e:Exception){
                        e.printStackTrace()
                    }
                }else if(mode=="stop"){
                    if(player !=null && player!!.isPlaying){
                        player!!.stop()
                        player!!.release()
                        player=null
                    }
                }
            }
        }
    }
    override fun onBind(intent: Intent): IBinder {
       throw UnsupportedOperationException("Not yet Implemented")
    }

    override fun onCreate() {
        super.onCreate()
        registerReceiver(receiver, IntentFilter("com.example.PLAY_TO_SERVICE"))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        filePath = intent?.getStringExtra("filePath")!!
        Log.d("filePath",filePath)
        if(player !=null){
            val aIntent = Intent("com.example.PLAY_TO_ACTIVITY")
            aIntent.putExtra("mode","restart")
            aIntent.putExtra("duration",player!!.duration)
            aIntent.putExtra("current",player!!.currentPosition)
            sendBroadcast(intent)
        }
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onCompletion(mediaPlayer: MediaPlayer) {
       val intent =Intent("com.example.PLAY_TO_ACTIVITY")
        intent.putExtra("mode","stop")
        sendBroadcast(intent)

        stopSelf()
    }
}
