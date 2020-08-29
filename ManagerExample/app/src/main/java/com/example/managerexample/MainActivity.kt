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
import android.net.wifi.WifiManager
import android.os.Bundle
import android.telephony.PhoneStateListener
import android.telephony.ServiceState
import android.telephony.TelephonyManager
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var datas: ArrayList<String>? = null
    var adapter: ArrayAdapter<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datas = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, datas!!)
        lab1_listview.adapter = adapter


        val telManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        telManager.listen(listener,PhoneStateListener.LISTEN_CALL_STATE |PhoneStateListener.LISTEN_SERVICE_STATE)
        datas!!.add("countryIso: ${telManager.networkCountryIso}" )
        datas!!.add("operatorName: ${telManager.networkOperatorName}")
        if(telManager.getNetworkType() == TelephonyManager.NETWORK_TYPE_LTE){
            datas!!.add("neworkType : LTE")
        }else if( telManager.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSDPA){
            datas!!.add("neworkType : 3G")
        }

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE )== PackageManager.PERMISSION_GRANTED){
            datas.add("PhoneNumber: ${telManager.line1Number}")
        }else{
            ActivityCompat.OnRequestPermissionsResultCallback(this, arrayOf(Manifest.permission.READ_PHONE_STATE),100)
        }

        //초기 액티비티 실행되면서 Network 상태 파악
        checkNetwork()
        //wifi상태 체크
        checkWifi()

        //wifi BroadcastReceiver 등록
        val wifiFilter =IntentFilter()
        wifiFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        wifiFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        wifiFilter.addAction(WifiManager.RSSI_CHANGED_ACTION)
        registerReceiver(wifiReceiver,wifiFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(wifiReceiver)
    }

    var listener: PhoneStateListener = object : PhoneStateListener() {
        override fun onServiceStateChanged(serviceState: ServiceState) {
           when(serviceState.state){
               ServiceState.STATE_EMERGENCY_ONLY -> {
                   datas!!.add("onServiceStateChanged STATE_EMERGENCY_ONLY")
               }
               ServiceState.STATE_IN_SERVICE -> {
                   datas!!.add("onServiceStateChanged STATE_IN_SERVICE")
               }
               ServiceState.STATE_OUT_OF_SERVICE -> {
                   datas!!.add("onServiceStateChanged STATE_OUT_OF_SERVICE")
               }
               ServiceState.STATE_POWER_OFF -> {
                   datas!!.add("onServiceStateChanged STATE_POWER_OFF")
               }
               else -> {
                   datas!!.add("onServiceStateChanged Unknown")
               }
           }
            adapter!!.notifyDataSetChanged()
        }

        override fun onCallStateChanged(state: Int, incomingNumber: String) {
            when(state){
                TelephonyManager.CALL_STATE_IDLE -> {
                    datas!!.add("onCallStateChanged: CALL_STATE_IDLE$incomingNumber")
                }
                TelephonyManager.CALL_STATE_RINGING -> {
                    datas!!.add("onCallStateChanged: CALL_STATE_RINGING$incomingNumber")
                }
                TelephonyManager.CALL_STATE_OFFHOOK -> {
                    datas!!.add("onCallStateChanged: CALL_STATE_OFFHOOK$incomingNumber")
                }
                else -> {

                }
            }
            adapter!!.notifyDataSetChanged()
        }
    }

    private fun checkNetwork() {
       val connectivityManager= getSystemService(Context.CONNECTIVITY_SERVICE)
    }

    private fun checkWifi() {
        //add~~~~~~~~~~~~~~~~
    }

    var wifiReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            //add~~~~~~~~~~~~~~~~~~~~
        }
    }
}