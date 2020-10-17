package com.example.menuexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_b_m_i.*

class BMIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_m_i)
        title="BMI 계산"
        loadData()
        resultButton.setOnClickListener {

            saveData(heightEditText.text.toString().toInt(),
                weightEditText.text.toString().toInt())

            val intent = Intent(this, ResultActivity::class.java)
             intent.putExtra("height", heightEditText.text.toString())
             intent.putExtra("weight", weightEditText.text.toString())
             startActivity(intent)


        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.menu_logout-> {
                finishAffinity()
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_home->{
               onBackPressed()
            }

        }

        return super.onOptionsItemSelected(item)
    }

    private  fun loadData(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getInt("KEY_HEIGHT",0)
        val weight = pref. getInt("KEY_WEIGHT",0)

        if(height !=0 && weight !=0){
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }
    private  fun saveData(height: Int ,weight:Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT",height)
            .putInt("KEY_WEIGHT",weight)
            .apply()
    }
}