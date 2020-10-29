package com.example.graphicviewproject

import android.content.Context
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(MyGraphicView(this))
    }

    class MyGraphicView(context: Context?): View(context){

        override fun onDraw(canvas: Canvas?){
            val paint = Paint()

            paint.color = Color.GREEN
            paint.strokeWidth = 3f
            canvas?.drawLine(10f,10f,300f,10f,paint)

            paint.color=Color.BLUE
            paint?.strokeWidth = 5f
            canvas?.drawLine(10f,30f,300f,30f,paint)

            paint.color=Color.RED
            paint.style= Paint.Style.FILL
            val rect1 = Rect(10,50,10+100,50+100)
            canvas?.drawRect(rect1,paint)

            paint.style= Paint.Style.STROKE
            val rect2= Rect(130,50,130+100,50+100)
            canvas?.drawRect(rect2,paint)

            val rect3 = RectF(250f,50f,250f+100,50f+100)
            canvas?.drawRoundRect(rect3,20f,20f,paint)

            canvas?.drawCircle(60f,220f,50f,paint)

            val path = Path()
            path.moveTo(10f,290f)
            path.lineTo(10f+50,290f+50)
            path.lineTo(10f+100,290f)

            canvas?.drawPath(path,paint)


            paint.textSize=30f
            canvas?.drawText("안녕하세요",10f,390f,paint)
        }
    }
}