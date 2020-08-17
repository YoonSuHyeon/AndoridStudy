package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.BatteryManager
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    var listView: ListView? = null
    var datas: ArrayList<String>? = null
    var adapter: ArrayAdapter<String>? = null
    val brOn= object :BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            addListItem("screen on")
        }
    }
    val brOff= object :BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            addListItem("screen off")
        }
    }
    val batteryReceiver= object :BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            val action = intent.action
            if(action.equals(Intent.ACTION_POWER_CONNECTED)){
                addListItem("ON CONNECTED....")
            }else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)){
                addListItem("ON DISCONNECTED....")
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.lab1_list)
        datas = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, datas!!)
        listView!!.adapter = adapter

        val ifilter=IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus = registerReceiver(null,ifilter)

        val status = batteryStatus!!.getIntExtra(BatteryManager.EXTRA_STATUS,-1)
        val isCharging = status ==BatteryManager.BATTERY_STATUS_CHARGING



        if(isCharging){
            val chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1)
            val usbCharge= chargePlug==BatteryManager.BATTERY_PLUGGED_USB
            val acCharge= chargePlug==BatteryManager.BATTERY_PLUGGED_AC

            if(usbCharge){
                addListItem("Battery is USB Charging")
            }else if(acCharge){
                addListItem("Battery is AC Charging")
            }
        }else{
            addListItem("Battery State is not Charging")
        }
        val level =batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL,-1)
        val scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE,-1)
        val batterPct= (level/ scale.toFloat())*100
        addListItem("Current Battery : $batterPct %")

        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.PROCESS_OUTGOING_CALLS) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.PROCESS_OUTGOING_CALLS,android.Manifest.permission.READ_PHONE_STATE,android.Manifest.permission.READ_CALL_LOG),100)
        }

        registerReceiver(brOn, IntentFilter(Intent.ACTION_SCREEN_ON))
        registerReceiver(brOff,IntentFilter(Intent.ACTION_SCREEN_OFF))
        registerReceiver(batteryReceiver,IntentFilter(Intent.ACTION_POWER_CONNECTED))
        registerReceiver(batteryReceiver,IntentFilter(Intent.ACTION_POWER_DISCONNECTED))
    }

    override fun  onRequestPermissionsResult(
         requestCode: Int,
         permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions!!, grantResults)
        if (requestCode == 100 && grantResults.size > 0) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                val toast = Toast.makeText(this, "no permission", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }


    private fun addListItem(message: String) {
        datas!!.add(message)
        adapter!!.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(brOn)
        unregisterReceiver(brOff)
        unregisterReceiver(batteryReceiver)
    }


}