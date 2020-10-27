package com.example.levelmeasuringinstrument

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View
import android.widget.TextView

class TiltView(context:Context?): View(context) {

    private val greenPaint = Paint()
    private val blackPaint = Paint()
    private var cx = 0f
    private var cy = 0f

    private var xCoord =0f
    private var yCoord =0f

    init{
        greenPaint.color = Color.GREEN
        blackPaint.style = Paint.Style.STROKE


    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(cx,cy,100f,blackPaint)
        canvas?.drawCircle(cx+xCoord,cy+yCoord,100f,greenPaint)
        canvas?.drawLine(cx-20,cy,cx+20,cy,blackPaint)
        canvas?.drawLine(cx,cy-20,cx,cy+20,blackPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cx=w/2f
        cy=h/2f
    }
    fun onSensorEvent(event:SensorEvent?){
        xCoord=event!!.values[0] *20
        yCoord = event!!.values[1] *20

        invalidate()
    }
}