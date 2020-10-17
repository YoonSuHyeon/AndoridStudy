package com.example.menuexample

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title="메인"
        btn_BMI.setOnClickListener { // BMI 계산 클릭
            val intent = Intent (this,BMIActivity::class.java)
            startActivity(intent)
        }
        btn_Reservation.setOnClickListener {//예약 클릭
            val intent = Intent (this,ReservationActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.menu_logout-> {
                finishAffinity()
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_contact->{
                val intent =Intent(Intent.ACTION_DIAL)
                intent.data= Uri.parse("tel:010-7469-4047")
                startActivity(intent)
            }

        }

        return super.onOptionsItemSelected(item)
    }
}