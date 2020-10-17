package com.example.menuexample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_reservation.*

class ReservationActivity : AppCompatActivity() {
    var year:Int =0
    var month:Int = 0
    var day: Int =0
    var hour:Int =0
    var minute:Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        calendarView.visibility= View.INVISIBLE //xml에서 미리 사용하게 되면 작동이 되지않기 때문에 코드에서 INVISIBLE 등록

        title="예약"

        btn_start.setOnClickListener {

            if(radio.visibility==View.VISIBLE){ //초기화
                Toast.makeText(this,"초기화",Toast.LENGTH_SHORT).show()
                chronometer.base= SystemClock.elapsedRealtime()
                chronometer.setTextColor(Color.BLACK)
                radio.visibility=View.INVISIBLE
                radio.clearCheck()
                linear.visibility=View.INVISIBLE
                calendarView.visibility= View.INVISIBLE
                timepicker.visibility=View.INVISIBLE
                tv_result.text = ""

            }else{
                Toast.makeText(this,"예약 시작",Toast.LENGTH_SHORT).show()
                chronometer.base=SystemClock.elapsedRealtime()
                chronometer.start()
                chronometer.setTextColor(Color.BLUE)
                radio.visibility=View.VISIBLE
                linear.visibility=View.VISIBLE
            }
        }

        rb_cal.setOnClickListener {
            calendarView.visibility= View.VISIBLE
            timepicker.visibility=View.INVISIBLE
        }
        rb_time.setOnClickListener {
            calendarView.visibility= View.INVISIBLE
            timepicker.visibility=View.VISIBLE
        }


        calendarView.setOnDateChangeListener { calendarView, i, i2, i3 ->
            year=i
            month=i2 +1
            day=i3

        }
        timepicker.setOnTimeChangedListener { timePicker, i, i2 ->
            hour=i
            minute=i2
        }
        btn_end.setOnClickListener {
            chronometer.stop()
            Toast.makeText(this,"\"$year 년 $month 월 $day 일 $hour 시 $minute 분 예약됨",Toast.LENGTH_SHORT).show()
            //tv_result.text= year.toString()+"년"+month.toString()+"월"+day.toString()+"일"+hour.toString()+"시"+minute+"분" +"예약됨"
            tv_result.text= "$year 년 $month 월 $day 일 $hour 시 $minute 분 예약됨"

        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
}