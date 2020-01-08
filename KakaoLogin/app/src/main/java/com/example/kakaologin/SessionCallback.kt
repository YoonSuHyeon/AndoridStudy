package com.example.kakaologin

import android.util.Log
import com.kakao.auth.ISessionCallback
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException

class SessionCallback : ISessionCallback {
    override fun onSessionOpenFailed(exception: KakaoException?) {
        Log.d("onSessionOpenFailed:", "${exception?.message}")
    }

    override fun onSessionOpened() {
        UserManagement.getInstance().me(object : MeV2ResponseCallback() {

            override fun onFailure(errorResult: ErrorResult?) {
                Log.d(" on failed", "${errorResult?.errorMessage}")
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                Log.e("onSessionClosed", "${errorResult?.errorMessage}")

            }

            override fun onSuccess(result: MeV2Response?) {
                checkNotNull(result) { "session response null" }
                // register or login
            }

        })
    }
}