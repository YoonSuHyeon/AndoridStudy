package com.example.kakaologin

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kakao.auth.ISessionCallback
import com.kakao.auth.KakaoSDK
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException
import com.kakao.util.helper.log.Logger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import com.kakao.auth.KakaoAdapter as KakaoAdapter1


class MainActivity : AppCompatActivity() {
    private lateinit var callback: SessionCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // getHashKey()

        callback = SessionCallback()
        Session.getCurrentSession().addCallback(callback)
        Session.getCurrentSession().checkAndImplicitOpen()
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (Session.getCurrentSession().handleActivityResult(
                requestCode,
                resultCode,
                data
            )
        ) {
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(callback)
    }
    private class SessionCallback : ISessionCallback {
        override fun onSessionOpened() {
            //redirectSignupActivity()
        }

        override fun onSessionOpenFailed(exception: KakaoException) {
            if (exception != null) {
                Logger.e(exception)
            }
        }
    }


    private fun getHashKey(){
        var packageInfo : PackageInfo? =null
        try{
            packageInfo =packageManager.getPackageInfo(packageName,PackageManager.GET_SIGNATURES)
        }catch (e:PackageManager.NameNotFoundException ){
            e.printStackTrace()
        }
        if (packageInfo == null) Log.e("KeyHash","Null")
        for (signature in packageInfo!!.signatures) {
            try {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            } catch (e: NoSuchAlgorithmException) {
                Log.e(
                    "KeyHash",
                    "Unable to get MessageDigest. signature=$signature",
                    e
                )
            }
        }


    }
}
