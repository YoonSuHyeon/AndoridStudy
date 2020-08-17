package com.example.notification


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.graphics.drawable.IconCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var manager: NotificationManager
    private lateinit var builder: NotificationCompat.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lab2_basic.setOnClickListener(this)
        lab2_bigpicture.setOnClickListener(this)
        lab2_bigtext.setOnClickListener(this)
        lab2_inbox.setOnClickListener(this)
        lab2_progress.setOnClickListener(this)
        lab2_headsup.setOnClickListener(this)
        lab2_message.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one-channel"
            val channelName = " My Channel One"
            val channelDescription = " My Channel One Description"

            var channel: NotificationChannel
            if (v == lab2_headsup) {
                channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            } else {
                channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            }
            channel.description = channelDescription
            builder = NotificationCompat.Builder(this, channelId)
           manager.createNotificationChannel(channel)
        } else {
            builder = NotificationCompat.Builder(this)
        }

        builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
        builder.setContentTitle("Content Title")
        builder.setContentText("Content Message")
        builder.setAutoCancel(true)

        val intent = Intent(this, MainActivity::class.java)
        val pIntent = PendingIntent.getActivity(this, 10, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pIntent)

        val pIntent1 = PendingIntent.getBroadcast(this, 0, Intent(this, NotiRerceiver::class.java), PendingIntent.FLAG_UPDATE_CURRENT)
        builder.addAction(NotificationCompat.Action.Builder(android.R.drawable.ic_menu_share, "ACTION1", pIntent1).build())

        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.noti_large)
        builder.setLargeIcon(largeIcon)

        if (v == lab2_bigpicture) {
            val bigPicture = BitmapFactory.decodeResource(resources, R.drawable.noti_big)
            val bigStyle = NotificationCompat.BigPictureStyle(builder)
            bigStyle.bigPicture(bigPicture)
            builder.setStyle(bigStyle)
        } else if (v == lab2_bigtext) {
            val bigTextSytle = NotificationCompat.BigTextStyle(builder)
            bigTextSytle.setSummaryText("BigText Summary")
            bigTextSytle.bigText("동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리 나라 만세 !!!")
            builder.setStyle(bigTextSytle)
        }else if(v== lab2_inbox){

            val style =NotificationCompat.InboxStyle(builder)
            style.addLine("Activity")
            style.addLine("BroadcastReceiver")
            style.addLine("Service")
            style.addLine("ContentProvider")
            style.setSummaryText("Android Component")
            builder.setStyle(style)
        }else if(v== lab2_progress){
            val runnable= Runnable {
                for(i in 1..10){
                    builder.setAutoCancel(false)
                    builder.setOngoing(true)
                    builder.setProgress(10,i,false)
                    manager.notify(222,builder.build())
                    if(i>=10){
                        manager.cancel(222)
                    }
                    SystemClock.sleep(1000)
                }
            }
            val t= Thread(runnable)
            t.start()
        }else if( v==lab2_headsup){
            builder.setFullScreenIntent(pIntent,true)
        }else if(v==lab2_message){
            val sender1 = Person.Builder()
                    .setName("kkang")
                    .setIcon(IconCompat.createWithResource(this,R.drawable.person1))
                    .build()

            val sender2 = Person.Builder()
                    .setName("Kim")
                    .setIcon(IconCompat.createWithResource(this,R.drawable.person2))
                    .build()
            val message =NotificationCompat.MessagingStyle.Message("hello",System.currentTimeMillis(),sender2)

            val style =NotificationCompat.MessagingStyle(sender1)
                    .addMessage("world",System.currentTimeMillis(),sender1)
                    .addMessage(message)
            builder.setStyle(style)
        }
        manager.notify(222,builder.build())
    }
}