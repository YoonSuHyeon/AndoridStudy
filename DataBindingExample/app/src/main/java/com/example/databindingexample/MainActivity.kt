package com.example.databindingexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.databindingexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val nameResult = "kkang"
    val intArray = intArrayOf(10,20,30) //배열 Integer.toString(myData.intArray[0])사용
    var arrayList=ArrayList<String>() //ArrayList =  myData.arrayList.get(0) 사용
    var hashMap=HashMap<String,String>() // android:text="@{myData.hashMap.get(&quot;one&quot;)}"  ,  android:text='@{myData.hashMap.get("one")}'  ,     android:text="@{myData.hashMap.get(`one`)}"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).myData=this
        arrayList.add("hello")
        hashMap["one"] = "map"

    }
    fun onButtonClick(view: View){     //android:onClick="@{myData::onButtonClick}"/> 를 사용해야한다 . 함수를 직접 호출이 아닌 참조로 등록을 해야합니다 .
        Toast.makeText(this,"tt",Toast.LENGTH_SHORT).show()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment,BlankFragment())
            .commit()
    }
    fun onClickListener1(){ //android:onClick="@{()-> myData.onClickListener1()}"/>  리스너 이벤트 바인딩
        Log.d("YY","onClickListener1")
    }
    fun onMyLongClickHandler (view: View): Boolean{
        Log.d("YY","LongClick")
        return true
    }
}