package com.example.intent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      /*  //명시적 인텐트   :의도 명확
       //       // val explicitIntent = Intent(this,SubActivity::class.java)
       //       // startActivity(explicitIntent )*/

        //암시적 인텐트  : 클래스 명이나 패키지 명을 넣어주지 않는다 .
       /* val implicitIntent= Intent(Intent.ACTION_VIEW)
        val TEST_DIAL_NUMBER = Uri . fromParts("tel","5551212",null)
        implicitIntent.setData(TEST_DIAL_NUMBER)
        startActivity(implicitIntent)*/
       btn.setOnClickListener {
           val implicitIntent= Intent(Intent.ACTION_VIEW)
           implicitIntent.data = Uri.parse("issues://JakeWharton/hugo/issues")
           startActivity(implicitIntent)
       }


        //IntentFilter는  AndroidManifest.xml 에 컴포넌트 태그 아래에 등록 할 수 있으며 , 이 컴포넌트가 어떤 인텐트를 처리할 수 있는 지 PackageManager에게 알려주는 코드다.

    }
}