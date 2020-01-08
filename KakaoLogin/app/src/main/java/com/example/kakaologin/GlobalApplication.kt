package com.example.kakaologin

import android.app.Application
import android.util.Log
import com.kakao.auth.KakaoSDK
import com.kakao.util.helper.CommonProtocol
import com.kakao.util.helper.Utility


class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this
        Log.d("aa",Utility.getMetadata(this.getGlobalApplicationContext(), CommonProtocol.APP_KEY_PROPERTY))
        KakaoSDK.init(KakaoSDKAdapter())
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }

    fun getGlobalApplicationContext(): GlobalApplication {
        checkNotNull(instance) { "this application does not inherit com.kakao.GlobalApplication" }
        return instance!!
    }

    companion object {
        var instance: GlobalApplication? = null
    }
}