package com.example.usernotificationexample

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.*

class DialogActivity : AppCompatActivity() , View.OnClickListener{

    private var  alertBtn : Button? =null
    private var  listBtn : Button? =null
    private var  dataBtn : Button? =null
    private var  timeBtn : Button? =null
    private var  customDialogBtn : Button? =null

    private var customDialog : AlertDialog? =null
    private var listDialog : AlertDialog? =null
    private var alertDialog : AlertDialog? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        //객체 획득
        alertBtn=findViewById(R.id.btn_alert)
        listBtn=findViewById(R.id.btn_list)
        dataBtn=findViewById(R.id.btn_data)
        timeBtn=findViewById(R.id.btn_time)
        customDialogBtn=findViewById(R.id.btn_custom)

        //버튼 이벤트 등록
        alertBtn!!.setOnClickListener(this)
        listBtn!!.setOnClickListener(this)
        dataBtn!!.setOnClickListener(this)
        timeBtn!!.setOnClickListener(this)
        customDialogBtn!!.setOnClickListener(this)

    }

    private fun showToast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    var dialogListener :DialogInterface.OnClickListener=DialogInterface.OnClickListener { dialogInterface, i ->
        if(dialogInterface==customDialog&& i == DialogInterface.BUTTON_POSITIVE){
            showToast("custom dialog 확인 click ...")
        }else if(dialogInterface==listDialog) {
            //목록 dialog 의 항목이 선택 되었을때 항목 문자열을 획득
            val datas = resources.getStringArray(R.array.dialog_array)
            showToast(datas[i] + "선택 하셨습니다.")
        }else if(dialogInterface==alertDialog&&i==DialogInterface.BUTTON_POSITIVE){
            showToast("alert dialog ok click ....")
        }
    }
    override fun onClick(v: View?) {
        if(v==alertBtn){
            val builder =AlertDialog.Builder(this)
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setTitle("알림")
            builder.setMessage("정말 종료 하시겠습니까?")
            builder.setPositiveButton("Ok",dialogListener)
            builder.setNegativeButton("NO",null)

            alertDialog=builder.create()
            alertDialog!!.show()
        }else if(v==listBtn){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("알람 벨소리")
            builder.setSingleChoiceItems(R.array.dialog_array,0,dialogListener)
            builder.setPositiveButton("확인",null)
            builder.setNegativeButton("취소",null)
            listDialog=builder.create()
            listDialog!!.show()
        }else if(v == dataBtn){
            //현재 날짜로 dialog 를  띄우기 위해 날짜를 구함
            val c =Calendar.getInstance()
            var year =c.get(Calendar.YEAR)
            var month = c.get(Calendar.MONTH)
            var day = c.get(Calendar.DAY_OF_MONTH)



            val dataDialog=DatePickerDialog(this,DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
                showToast("$year : ${monthOfYear+1} : $dayOfMonth")
            },year,month,day)

            dataDialog.show()
        }else if(v==timeBtn){

            val c = Calendar.getInstance()
            val hour =c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            val timeDialog=TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
                showToast("$hourOfDay : $minute")
            },hour,minute,false)
            timeDialog.show()

        }else if(v==customDialogBtn){
            val builder = AlertDialog.Builder(this)
            //Custom dialog 를 위한 layout.xml 초기화
            val inflater =getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.dialog_layout,null)
            builder.setView(view)
            builder.setPositiveButton("확인",dialogListener)
            builder.setNegativeButton("취소",null)

            customDialog=builder.create()
            customDialog!!.show()
        }
    }
}
