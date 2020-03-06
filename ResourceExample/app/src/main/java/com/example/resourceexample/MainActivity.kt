package com.example.resourceexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var anim=AnimationUtils.loadAnimation(this,R.anim.`in`)
        anim.setAnimationListener(object:AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                val anim =AnimationUtils.loadAnimation(this@MainActivity,R.anim.move)
                //마지막 상황에서 멈춤 안그러면 원상복귀
                anim.fillAfter=true
                img.startAnimation(anim)
            }

            override fun onAnimationStart(p0: Animation?) {

            }

        })
        img.startAnimation(anim)

    }
}
