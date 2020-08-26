package com.example.fusedlocationapiexample


import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener {

    private lateinit var apiClient: GoogleApiClient
    private lateinit var providerApi: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                100
            )
        }

        apiClient =
            GoogleApiClient.Builder(this).addApi(LocationServices.API).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build()
        providerApi = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun setLocationInfo(location: Location?) {
        if (location != null) {
            lab2_latitude.text = location.latitude.toString()
            lab2_longitude.text = location.longitude.toString()
            lab2_accuracy.text = location.accuracy.toString() + " m"
            val date = Date(location.time)
            val sd = SimpleDateFormat("yyyy-MM-dd HH:mm")
            lab2_time.text = sd.format(date)
            lab2_onOffView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_on,
                    null
                )
            )
        } else {
            showToast("location null....")
        }
    }

    override fun onResume() {
        super.onResume()
        apiClient.connect()
    }

    override fun onConnected(bundle: Bundle?) {
        if(ContextCompat.checkSelfPermission(this
            ,Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_GRANTED){
            providerApi.lastLocation.addOnSuccessListener {
                setLocationInfo(it)
            }
        }
        apiClient.disconnect()
    }

    override fun onConnectionSuspended(p0: Int) {
        showToast("onConnectionSuspended")
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        showToast("onConnectionFailed")
    }
}