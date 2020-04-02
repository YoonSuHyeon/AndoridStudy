package com.example.fitnessactivitylogexample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View


class DonutView : View {
    private var value = 0
    var size = 0
    var strokeSize = 0
    var textSize = 0
    var widthd = 0
    var heightd = 0

    constructor(context: Context) : super(context)
    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    init {
        size = resources.getDimensionPixelSize(R.dimen.donut_size)
        strokeSize = resources.getDimensionPixelSize(R.dimen.donut_stroke_size)
        textSize = resources.getDimensionPixelSize(R.dimen.donut_textSize)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        widthd = MeasureSpec.getSize(widthMeasureSpec)
        heightd = MeasureSpec.getSize(heightMeasureSpec)
    }

    fun setValue(value: Int) {
        this.value = value
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.alpha(Color.CYAN))
        val rectF = RectF(20F, 20F, (size - 20).toFloat(), (size - 20).toFloat())
        val paint = Paint()
        paint.color = Color.LTGRAY
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeSize.toFloat()
        canvas.drawArc(rectF, 0F, 360F, false, paint)
        paint.color = Color.RED
        paint.strokeJoin = Paint.Join.ROUND
        canvas.drawArc(rectF, -90F, value.toFloat(), false, paint)
        paint.textSize = textSize.toFloat()
        paint.strokeWidth = 5F
        val txt = value.toString()
        val xPos = width / 2 - (paint.measureText(txt) / 2).toInt()
        val yPos = (height / 2 - (paint.descent() + paint.ascent()) / 2).toInt()
        canvas.drawText(txt, xPos.toFloat(), yPos.toFloat(), paint)
    }
}