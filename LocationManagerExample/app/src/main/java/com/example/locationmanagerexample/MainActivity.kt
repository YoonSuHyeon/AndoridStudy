package com.example.locationmanagerexample

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    var manager: LocationManager? = null

    var enabledProviders: List<String>? = null
    var bestAccuracy = 0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager= getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),100)
        }else{
            getProviders()
            getLocation()
        }
    }
    private fun showToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.size > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getProviders()
                getLocation()
            } else {
                showToast("no permission...")
            }
        }
    }

    private fun getProviders() {
        var result = "All Providers : "
        val providers = manager!!.allProviders
        for(provider in providers){
            result += "$provider,"

        }
        lab1_allProviders.text = result

        result = "Enabled Providers: "
        enabledProviders = manager!!.getProviders(true)
        for(provider in enabledProviders!!){
            result +="$provider ,"

        }
        lab1_enableProviders.text=result
    }

    private fun getLocation() {
        for(provider in enabledProviders!!){
           var location :Location?=null
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                location= manager!!.getLastKnownLocation(provider)
            }else{
                showToast("no permission.. ")
            }
            if(location!=null){
                val accuracy = location.accuracy
                if(bestAccuracy==0f){
                    bestAccuracy = accuracy
                    setLocationInfo(provider,location)
                }else{
                    if(accuracy < bestAccuracy){
                        bestAccuracy=accuracy
                        setLocationInfo(provider, location)
                    }
                }
            }

        }
    }

    private fun setLocationInfo(provider: String, location: Location) {
        if(location !=null){
            lab1_provider.text=provider
            lab1_latitude.text= location.latitude.toString()
            lab1_longitude.text=location.longitude.toString()
            lab1_accuracy.text=location.accuracy.toString()+"m"
            val date =Date(location.time)
            val sd =SimpleDateFormat("yyyy-MM-dd HH :mm")
            lab1_time.text=sd.format(date)
            lab1_onOffView.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.ic_on,null))
        }else{
            showToast("location null")
        }
    }
}