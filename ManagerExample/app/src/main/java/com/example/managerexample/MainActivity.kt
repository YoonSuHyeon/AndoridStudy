package com.example.managerexample

/**
 * TelephonyManager : PhonerStateListener를 구현한 클래스를 TelephonyManager에 등록해야한다.
 * ConnectivityManager:, WiFiManager
 * **/

import android.Manifest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Bundle
import android.telephony.PhoneStateListener
import android.telephony.ServiceState
import android.telephony.TelephonyManager
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*


class MainActivity : AppCompatActivity() {
    var listView: ListView? = null
    var datas: ArrayList<String>? = null
    var adapter: ArrayAdapter<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById<View>(R.id.lab1_listview) as ListView
        datas = ArrayList()

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, datas!!)
        listView!!.adapter = adapter

        //add~~~~~~~~~~~~~
        val telManager =
            getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        telManager.listen(
            listener,
            PhoneStateListener.LISTEN_CALL_STATE or PhoneStateListener.LISTEN_SERVICE_STATE
        )
        datas!!.add("countryIos:" + telManager.networkCountryIso)
        datas!!.add("operatorName:" + telManager.networkOperatorName)
        if (telManager.networkType == TelephonyManager.NETWORK_TYPE_LTE) {
            datas!!.add("networkType:LTE")
        } else if (telManager.networkType == TelephonyManager.NETWORK_TYPE_HSDPA) {
            datas!!.add("networkType:3G")
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            datas!!.add("PhoneNumber:" + telManager.line1Number)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                100
            )
        }
        checkNetwork()
        checkWifi()
        val wifiFilter = IntentFilter()
        wifiFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        wifiFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        wifiFilter.addAction(WifiManager.RSSI_CHANGED_ACTION)
        registerReceiver(wifiReceiver, wifiFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(wifiReceiver)
    }

    var listener: PhoneStateListener = object : PhoneStateListener() {
        override fun onServiceStateChanged(serviceState: ServiceState) {
            //add~~~~~~~~~~~~~~~
            when (serviceState.state) {
                ServiceState.STATE_EMERGENCY_ONLY -> datas!!.add("onServiceStateChanged STATE_EMERGENCY_ONLY")
                ServiceState.STATE_IN_SERVICE -> datas!!.add("onServiceStateChanged STATE_IN_SERVICE")
                ServiceState.STATE_OUT_OF_SERVICE -> datas!!.add("onServiceStateChanged STATE_OUT_OF_SERVICE")
                ServiceState.STATE_POWER_OFF -> datas!!.add("onServiceStateChanged STATE_POWER_OFF")
                else -> datas!!.add("onServiceStateChanged Unknown")
            }
            adapter!!.notifyDataSetChanged()
        }

        override fun onCallStateChanged(
            state: Int,
            incomingNumber: String
        ) {
            //add~~~~~~~~~~~~~~
            when (state) {
                TelephonyManager.CALL_STATE_IDLE -> datas!!.add("onCallStateChanged : CALL_STATE_IDLE : $incomingNumber")
                TelephonyManager.CALL_STATE_RINGING -> datas!!.add("onCallStateChanged : CALL_STATE_RINGING : $incomingNumber")
                TelephonyManager.CALL_STATE_OFFHOOK -> datas!!.add("onCallStateChanged : CALL_STATE_OFFHOOK : $incomingNumber")
            }
            adapter!!.notifyDataSetChanged()
        }
    }

    private fun checkNetwork() {
        //add~~~~~~~~~~~~~~~
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null) {
            if (networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                datas!!.add("Network Info : Online - " + networkInfo.typeName)
            } else if (networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                datas!!.add("Network Info : Online - " + networkInfo.typeName)
            }
        } else {
            datas!!.add("Network Info : Offline")
        }
        adapter!!.notifyDataSetChanged()
    }

    private fun checkWifi() {
        //add~~~~~~~~~~~~~~~~
        val wifiManager =
            applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (!wifiManager.isWifiEnabled) {
            datas!!.add("WifiManager : wifi disabled")
            if (wifiManager.wifiState != WifiManager.WIFI_STATE_ENABLED) {
                wifiManager.isWifiEnabled = true
            }
        } else {
            datas!!.add("WifiManager : wifi enabled")
        }
        adapter!!.notifyDataSetChanged()
    }

    var wifiReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(
            context: Context,
            intent: Intent
        ) {
            //add~~~~~~~~~~~~~~~~~~~~
            if (intent.action == WifiManager.WIFI_STATE_CHANGED_ACTION) {
                val state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1)
                if (state == WifiManager.WIFI_STATE_ENABLED) {
                    datas!!.add("WIFI_STATE_CHANGED_ACTION : enable")
                } else {
                    datas!!.add("WIFI_STATE_CHANGED_ACTION : disable")
                }
            } else if (intent.action == WifiManager.NETWORK_STATE_CHANGED_ACTION) {
                val networkInfo =
                    intent.getParcelableExtra<NetworkInfo>(WifiManager.EXTRA_NETWORK_INFO)
                val wifiManager =
                    context.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val wifiInfo = wifiManager.connectionInfo
                val ssid = wifiInfo.ssid
                if (networkInfo!!.state == NetworkInfo.State.CONNECTED) {
                    datas!!.add("NETWORK_STATE_CHANGED_ACTION : connected...$ssid")
                } else if (networkInfo.state == NetworkInfo.State.DISCONNECTED) {
                    datas!!.add("NETWORK_STATE_CHANGED_ACTION : disconnected...$ssid")
                }
            } else if (intent.action == WifiManager.RSSI_CHANGED_ACTION) {
                datas!!.add("RSSI_CHANGED_ACTION")
            }
            adapter!!.notifyDataSetChanged()
        }
    }
}