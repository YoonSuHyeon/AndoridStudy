package com.example.googlemap

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception

/**
 * SHA-1 인증서
"C:\Program Files\Android\Android Studio\jre\bin\keytool" -list -v -keystore "%USERPROFILE%\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android

 */

/**
 *  <uses-library android:name="org.apache.http.legacy" android:required="false"/>
 *  android 9의 bootclasspath에서 apache라이브러리가 제거되어 기본적으로 앱에서 사용할 수 없으므로 추가해주어야 한다.
 * */
class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //지도 객체를 사용할 수 있을 때 onMapReady()함수가 자동으로 호출되면서 매개변수로 GoogleMap객체가 전달된다.
        (supportFragmentManager.findFragmentById(R.id.lab1_map) as SupportMapFragment).getMapAsync(
            this
        )

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val latLng = LatLng(37.566610, 126.978403)
        val position = CameraPosition.Builder().target(latLng).zoom(16f).build()
        map.moveCamera(CameraUpdateFactory.newCameraPosition(position))

        val markerOptions = MarkerOptions()
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
        markerOptions.position(latLng)
        markerOptions.snippet("Tel:01-120")

        map.addMarker(markerOptions)

        val address = "서울특별시 중구 서소문동 37-9"

        val reverseThread = MyReverseGeocodingThread(address)
        reverseThread.start()
    }

    inner class MyReverseGeocodingThread(var address: String) : Thread() {
        override fun run() {
            super.run()
            val geocoder = Geocoder(this@MainActivity)
            try {
                var results = geocoder.getFromLocationName(address, 1)
                val resultAddress = results[0]
                val latLng = LatLng(resultAddress.latitude, resultAddress.longitude)
                val msg = Message()
                msg.what = 200
                msg.obj = latLng
                handler.sendMessage(msg)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    val handler= Handler{

        if(it.what==200){
            val markerOptions = MarkerOptions()
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location))
            markerOptions.position(it.obj as LatLng)
            markerOptions.title("서울시립미술관")
            map.addMarker(markerOptions)
        }
        true
    }


}