package com.example.spannableexample


import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Html
import android.text.Html.ImageGetter
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.ScrollingMovementMethod
import android.text.style.ImageSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spanView = findViewById<TextView>(R.id.spanView)
        spanView.movementMethod = ScrollingMovementMethod()
        val data = "복수초 \n img \n 이른봄 설산에서 만나는 복수초는 모든 야생화 찍사들의 로망이 아닐까 싶다."
        val builder = SpannableStringBuilder(data)
        var start = data.indexOf("img")
        if (start > -1) {
            val end = start + "img".length
            val dr = ResourcesCompat.getDrawable(resources, R.drawable.img1, null)
            dr!!.setBounds(0, 0, dr.intrinsicWidth, dr.intrinsicHeight)
            val span = ImageSpan(dr)
            builder.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        start = data.indexOf("복수초")
        if (start > -1) {
            val end = start + "복수초".length
            val styleSpan = StyleSpan(Typeface.BOLD)
            val sizeSpan = RelativeSizeSpan(2.0f)
            builder.setSpan(styleSpan, start, end + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            builder.setSpan(sizeSpan, start, end + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        spanView.text = builder
        val htmlView = findViewById<TextView>(R.id.htmlView)
        val html =
            "<font color='RED'>얼레지</font><br/><img src='img1'/><br/>곰배령에서 만난 봄꽃"
        htmlView.text = Html.fromHtml(html, MyImageGetter(resources), null)
    }

    internal class MyImageGetter(var resource:Resources) : ImageGetter {
        override fun getDrawable(source: String): Drawable? {
            if (source == "img1") {
                val dr =ResourcesCompat.getDrawable(resource,R.drawable.img2,null)
                dr!!.setBounds(0, 0, dr.intrinsicWidth, dr.intrinsicHeight)
                return dr
            }
            return null
        }
    }
}
