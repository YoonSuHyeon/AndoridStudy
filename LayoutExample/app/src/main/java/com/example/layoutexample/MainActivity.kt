package com.example.layoutexample


import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.table2.*

/**
 * TableLayout 예제 테이블 레이아웃 계산기
 * **/
class MainActivity : AppCompatActivity() {

    var num1: String? = null
    var num2: String? = null
    var result: Int? = null

    // 10개 숫자 버튼 배열
    var numButtons: Array<Button?> = arrayOfNulls<Button>(10)


    // 10개 숫자 버튼의 id 값 배열
    var numBtnIDs = arrayOf<Int>(
        R.id.BtnNum0, R.id.BtnNum1, R.id.BtnNum2,
        R.id.BtnNum3, R.id.BtnNum4, R.id.BtnNum5, R.id.BtnNum6,
        R.id.BtnNum7, R.id.BtnNum8, R.id.BtnNum9
    )
    var i // 증가값 용도
            = 0

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table2)
        title = "테이블레이아웃 계산기"

        BtnAdd.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(arg0: View?, arg1: MotionEvent?): Boolean {
                num1 = Edit1.text.toString()
                num2 = Edit2!!.text.toString()
                result = num1!!.toInt() + num2!!.toInt()
                TextResult!!.text = "계산 결과 : " + result.toString()
                return false
            }
        })
        BtnSub.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(arg0: View?, arg1: MotionEvent?): Boolean {
                num1 = Edit1!!.text.toString()
                num2 = Edit2!!.text.toString()
                result = num1!!.toInt() - num2!!.toInt()
                TextResult!!.text = "계산 결과 : " + result.toString()
                return false
            }
        })
        BtnMul.setOnTouchListener(object : View.OnTouchListener {
           override fun onTouch(arg0: View?, arg1: MotionEvent?): Boolean {
                num1 = Edit1!!.text.toString()
                num2 = Edit2!!.text.toString()
                result = num1!!.toInt() * num2!!.toInt()
                TextResult!!.text = "계산 결과 : " + result.toString()
                return false
            }
        })
        BtnDiv.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(arg0: View?, arg1: MotionEvent?): Boolean {
                num1 = Edit1!!.text.toString()
                num2 = Edit2!!.text.toString()
                result = num1!!.toInt() / num2!!.toInt()
                TextResult!!.text = "계산 결과 : " + result.toString()
                return false
            }
        })

        // 숫자 버튼 10개를 대입
        i = 0
        while (i < numBtnIDs.size) {
            numButtons[i] = findViewById<View>(numBtnIDs[i]) as Button
            i++
        }
        // 숫자 버튼 10개에 대해서 클릭이벤트 처리
        i = 0
        while (i < numBtnIDs.size) {
            val index: Int // 주의! 꼭 필요함..
            index = i
            numButtons[index]?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    // 포커스가 되어 있는 에디트텍스트에 숫자 추가
                    if (Edit1!!.isFocused == true) {
                        num1 = (Edit1!!.text.toString()
                                + numButtons[index]?.getText().toString())
                        Edit1!!.setText(num1)
                    } else if (Edit2!!.isFocused == true) {
                        num2 = (Edit2!!.text.toString()
                                + numButtons[index]?.getText().toString())
                        Edit2!!.setText(num2)
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "먼저 에디트텍스트를 선택하세요", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
            i++
        }
    }
}