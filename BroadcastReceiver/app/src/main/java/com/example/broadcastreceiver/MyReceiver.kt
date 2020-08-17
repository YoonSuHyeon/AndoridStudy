package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if(action.equals("android.intent.action.NEW_OUTGOING_CALL")){
            val phoneNumber =intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
            val intent1 = Intent(context,DialogActivity::class.java)
            intent1.putExtra("number",phoneNumber)
            intent1.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent1)
        }else if(action.equals("android.intent.action.PHONE_STATE")){
            val bundle = intent.extras
            val state =bundle!!.getString(TelephonyManager.EXTRA_STATE)
            val phoneNumber = bundle!!.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
            if(state!! == TelephonyManager.EXTRA_STATE_RINGING && phoneNumber !=null){
                val intent1 = Intent(context,DialogActivity::class.java)
                intent1.putExtra("number",phoneNumber)
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent1)
            }
        }
    }
}
