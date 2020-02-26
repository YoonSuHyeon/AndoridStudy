package com.example.alarmexample

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val picker = findViewById<View>(R.id.timePicker) as TimePicker
        picker.setIs24HourView(true)
        // 앞서 설정한 값으로 보여주기
// 없으면 디폴트 값은 현재시간
        val sharedPreferences =
            getSharedPreferences("daily alarm", Context.MODE_PRIVATE)
        val millis =
            sharedPreferences.getLong("nextNotifyTime", Calendar.getInstance().getTimeInMillis())
        val nextNotifyTime: Calendar = GregorianCalendar()
        nextNotifyTime.setTimeInMillis(millis)
        val nextDate: Date = nextNotifyTime.getTime()
        val date_text: String =
            SimpleDateFormat("yyyy년 MM월 dd일 EE요일 a hh시 mm분 ", Locale.getDefault()).format(nextDate)
        Toast.makeText(
            applicationContext,
            "[처음 실행시] 다음 알람은 " + date_text + "으로 알람이 설정되었습니다!",
            Toast.LENGTH_SHORT
        ).show()
        // 이전 설정값으로 TimePicker 초기화
        val currentTime: Date = nextNotifyTime.getTime()
        val HourFormat = SimpleDateFormat("kk", Locale.getDefault())
        val MinuteFormat = SimpleDateFormat("mm", Locale.getDefault())
        val pre_hour: Int = HourFormat.format(currentTime).toInt()
        val pre_minute: Int = MinuteFormat.format(currentTime).toInt()
        if (Build.VERSION.SDK_INT >= 23) {
            picker.hour = pre_hour
            picker.minute = pre_minute
        } else {
            picker.currentHour = pre_hour
            picker.currentMinute = pre_minute
        }

        button.setOnClickListener {
            val hour: Int
            val hour_24: Int
            val minute: Int
            val am_pm: String
            if (Build.VERSION.SDK_INT >= 23) {
                hour_24 = picker.hour
                minute = picker.minute
            } else {
                hour_24 = picker.currentHour
                minute = picker.currentMinute
            }
            if (hour_24 > 12) {
                am_pm = "PM"
                hour = hour_24 - 12
            } else {
                hour = hour_24
                am_pm = "AM"
            }
            // 현재 지정된 시간으로 알람 시간 설정
            val calendar: Calendar = Calendar.getInstance()
            calendar.setTimeInMillis(System.currentTimeMillis())
            calendar.set(Calendar.HOUR_OF_DAY, hour_24)
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, 0)
            // 이미 지난 시간을 지정했다면 다음날 같은 시간으로 설정
            if (calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DATE, 1)
            }
            val currentDateTime: Date = calendar.getTime()
            val date_text: String =
                SimpleDateFormat("yyyy년 MM월 dd일 EE요일 a hh시 mm분 ", Locale.getDefault()).format(
                    currentDateTime
                )
            Toast.makeText(
                applicationContext,
                date_text + "으로 알람이 설정되었습니다!",
                Toast.LENGTH_SHORT
            ).show()
            //  Preference에 설정한 값 저장
            val editor =
                getSharedPreferences("daily alarm", Context.MODE_PRIVATE).edit()
            editor.putLong("nextNotifyTime", calendar.getTimeInMillis() as Long)
            editor.apply()
            diaryNotification(calendar)
        }
    }

    fun diaryNotification(calendar: Calendar) { //        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//        Boolean dailyNotify = sharedPref.getBoolean(SettingsActivity.KEY_PREF_DAILY_NOTIFICATION, true);
        val dailyNotify = true // 무조건 알람을 사용
        val pm = this.packageManager
        val receiver = ComponentName(this, DeviceBootReceiver::class.java)
        val alarmIntent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // 사용자가 매일 알람을 허용했다면
        if (dailyNotify) {
            if (alarmManager != null) {
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        pendingIntent
                    )
                }
            }
            // 부팅 후 실행되는 리시버 사용가능하게 설정
            pm.setComponentEnabledSetting(
                receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
        }
        //        else { //Disable Daily Notifications
//            if (PendingIntent.getBroadcast(this, 0, alarmIntent, 0) != null && alarmManager != null) {
//                alarmManager.cancel(pendingIntent);
//                //Toast.makeText(this,"Notifications were disabled",Toast.LENGTH_SHORT).show();
//            }
//            pm.setComponentEnabledSetting(receiver,
//                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
//                    PackageManager.DONT_KILL_APP);
//        }
    }
}
