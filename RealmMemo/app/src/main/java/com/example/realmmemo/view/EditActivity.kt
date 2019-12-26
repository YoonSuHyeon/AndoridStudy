package com.example.realmmemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.realmmemo.R
import com.example.realmmemo.model.Todo
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    private  val realm = Realm.getDefaultInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        btn_save.setOnClickListener {//메모저장 버튼 클릭시.
            insertTodo()
        }
        btn_cancel.setOnClickListener {//취소 버튼 클릭시.
            onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
    private fun insertTodo(){
        realm.beginTransaction()
        val newItem = realm.createObject<Todo>(nextId())
        newItem.itemName =et_todo.text.toString()

        realm.commitTransaction()

        AlertDialog.Builder(this)
            .setTitle("내용이 추가되었습니다.")
            .setPositiveButton("확인"){_,_->finish()}
            .show()
    }
    private fun nextId():Int{
        val maxId = realm.where<Todo>().max("id")
        if(maxId !=null){
            return maxId.toInt()+1
        }
        return 0
    }
}
