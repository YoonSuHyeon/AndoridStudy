package com.example.delegationexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity(), View.OnClickListener,CompoundButton.OnCheckedChangeListener {

    private lateinit var bellTextView:TextView
    private lateinit var labelTextVeiw:TextView
    private lateinit var repeatCheckView:CheckBox
    private lateinit var vibrateCheckView:CheckBox
    private lateinit var switchView:Switch
    private var initX:Float = 0F
    private var initTime:Long=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bellTextView = findViewById(R.id.bell_name)
        labelTextVeiw=findViewById(R.id.label)
        repeatCheckView=findViewById(R.id.repeatCheck)
        vibrateCheckView=findViewById(R.id.vibrate)
        switchView=findViewById(R.id.onOff)


        bellTextView.setOnClickListener(this)
        labelTextVeiw.setOnClickListener(this)
        repeatCheckView.setOnCheckedChangeListener(this)
        vibrateCheckView.setOnCheckedChangeListener(this)
        switchView.setOnCheckedChangeListener(this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event!!.action==MotionEvent.ACTION_DOWN){
            initX=event.rawX
        }else if(event!!.action==MotionEvent.ACTION_UP){
            val diffx = initX-event.rawX
            if(diffx>30){
                showToast("왼쪽으로 화면을 밀었습니다.")
            }else if(diffx<-30){
                showToast("오른쪽으로 화면을 밀었습니다.")
            }
        }
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis()-initTime>3000){
                //back button 을 누른지 3초가 지난거라면
                showToast("종료하려면 한번 더 누르세요.")
                initTime=System.currentTimeMillis()
            }else{
                //3초 이내에 Back button 이 두번 눌린경우 Activity 종료
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
    override fun onClick(v: View?) {
        if(v==bellTextView){
            showToast("bell text click event...")
        }else if(v==labelTextVeiw){
            showToast("label text click event...")
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (buttonView) {
            repeatCheckView -> showToast("repeat checkbox is $isChecked")
            vibrateCheckView -> showToast("vibrate checkbox is $isChecked")
            switchView -> showToast("switch  is $isChecked")
        }
    }


    private fun showToast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}
