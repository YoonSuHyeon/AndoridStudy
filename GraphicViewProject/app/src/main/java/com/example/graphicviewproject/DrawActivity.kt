package com.example.graphicviewproject

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*

class DrawActivity : AppCompatActivity() {
    private lateinit var myTouchView:MyTouchView
    private val line = 1
    private val circle = 2
     private val rect = 3
     private val red = 4
    private val yellow=5
    private var curShape = line
    private var curColor=Color.RED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myTouchView = MyTouchView(this)
        setContentView(myTouchView)

    }
    inner class MyTouchView(context: Context):View(context){

        private var startX = -1.0f
        private var startY = -1.0f
        private var stopX = -1.0f
        private var stopY = -1.0f



        override fun onTouchEvent(event: MotionEvent?): Boolean {
            when(event?.action){
                MotionEvent.ACTION_DOWN ->{
                    startX=event.x
                    startY=event.y
                    return true
                }
                MotionEvent.ACTION_MOVE ->{
                    stopX=event.x
                    stopY=event.y
                    invalidate()
                    return true
                }
                MotionEvent.ACTION_UP -> {
                    stopX=event.x
                    stopY=event.y
                    invalidate()
                    return true
                }
            }
            return true
        }
        override fun onDraw(canvas: Canvas?) {
            val paint=Paint()
            paint.strokeWidth= 5f
            paint.style=Paint.Style.STROKE
            paint.color= curColor
            var radius:Float=Math.sqrt(Math.pow((stopX-startX).toDouble(),2.0) +
                    Math.pow((stopY-startY).toDouble(),2.0)).toFloat()
            when(curShape){
                line -> {
                    canvas?.drawLine(startX,startY,stopX,stopY,paint)
                }
                circle -> {

                    canvas?.drawCircle(startX,startY,radius,paint)
                }
                rect->{
                    canvas?.drawRect(Rect(startX.toInt(),startY.toInt(),stopX.toInt(),stopY.toInt()),paint)
                }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0,1,0,"선그리기")
        menu?.add(0,2,1,"원그리기")
        menu?.add(0,3,2,"사각형 그리기")
        val subMenu=menu?.addSubMenu(0,4,3,"색상변경")
        subMenu?.add(0,4,4,"빨간색")
        subMenu?.add(0,5,5,"노란색")



       return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            1->{
                curShape = line
                return true
            }
            2->{
                curShape = circle
                return true
            }
            3->{
                curShape=rect
                return true
            }
            4->{
                curColor=Color.RED
            }
            5->{
                curColor=Color.YELLOW
            }

        }
        return super.onOptionsItemSelected(item)

    }

}