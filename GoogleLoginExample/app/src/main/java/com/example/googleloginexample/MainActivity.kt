package com.example.googleloginexample
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    private lateinit var auth:FirebaseAuth
    private lateinit var googleApiClient: GoogleApiClient
    private final val REQ_SIGN_GOOGLE=100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleApiClient=GoogleApiClient.Builder(this)
            .enableAutoManage(this,this)
            .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
            .build()
        auth =FirebaseAuth.getInstance()
        sign_in_button.setOnClickListener {
            var intent =Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
            startActivityForResult(intent,REQ_SIGN_GOOGLE)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {//구글로그인 인증을 요청 했을때 결과 값을 되돌려 받는곳
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQ_SIGN_GOOGLE){
            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if(result.isSuccess){
                var account=result.signInAccount
                resultLogin(account!!)
            }
        }
    }

    private fun resultLogin(account: GoogleSignInAccount) {//acco
        // unt 라는 데이터는 구글로그인 정보를 담고있다. (닉네임,프로필사진Url,이메일주소)
        var credential = GoogleAuthProvider.getCredential(account.idToken,null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this,"로그인 성공",Toast.LENGTH_SHORT).show()
                    var intent=Intent(this,ResultActivity::class.java)
                    intent.putExtra("nickname",account.displayName)
                    intent.putExtra("photoUrl",account.photoUrl.toString())
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"로그인 실패",Toast.LENGTH_SHORT).show()
                }

            }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }


}