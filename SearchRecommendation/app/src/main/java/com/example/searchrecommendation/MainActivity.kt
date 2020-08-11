package com.example.searchrecommendation

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),AdapterView.OnItemClickListener,TextWatcher {
    var base =ArrayList<String>()
    var datas = ArrayList<SpannableStringBuilder>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lv_rec.onItemClickListener = this
        et_search.addTextChangedListener(this)


        base.add("우편번호 검색")
        base.add("지도검색")
        base.add("대법원 나의 사건 검색")
        base.add("나의 사진검색")
        base.add("주소검색")
        base.add("다음지도 검색")
        base.add("검색등록")

        val adapter =ArrayAdapter<SpannableStringBuilder>(this, android.R.layout.simple_list_item_1, datas)
        lv_rec.adapter=adapter

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        et_search.text= datas[position]
        datas=ArrayList()
        val adapter = ArrayAdapter<SpannableStringBuilder>(this, android.R.layout.simple_list_item_1, datas)
        lv_rec.adapter=adapter
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        datas=ArrayList()
        for(txt in base){
            if(txt.contains(s!!)){
                val startPos = txt.indexOf(s.toString())
                val endPos: Int = startPos + s.length
                val builder = SpannableStringBuilder(txt)
                builder.setSpan(
                    ForegroundColorSpan(Color.BLUE),
                    startPos,
                    endPos,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                builder.setSpan(
                    StyleSpan(Typeface.BOLD),
                    startPos,
                    endPos,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                datas.add(builder)
            }
        }
        val adapter = ArrayAdapter<SpannableStringBuilder>(this, android.R.layout.simple_list_item_1, datas)
        lv_rec.adapter=adapter
    }
}
