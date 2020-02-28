package com.example.layoutclassexample

import android.os.Bundle
import android.widget.TabHost
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.extabhost.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //------LinearLayout
        //setContentView(R.layout.exlinear)

        //------RelativeLayout
        //setContentView(R.layout.exrelative)

        //------TabHost
        /*setContentView(R.layout.extabhost)
        val host =findViewById<TabHost>(R.id.host)
        host.setup()


        //tab1
        var spec = host.newTabSpec("tab 1")
        spec.setIndicator(null, ResourcesCompat.getDrawable(
            resources,R.drawable.ic_launcher_background,null))
        spec.setContent(R.id.tab_content1)
        host.addTab(spec)

        //tab2
        spec = host.newTabSpec("tab 2")
        spec.setIndicator(null,ResourcesCompat.getDrawable(
            resources,R.drawable.ic_launcher_background,null))
        spec.setContent(R.id.tab_content2)
        host.addTab(spec)

        //tab3
        spec = host.newTabSpec("tab 3")
        spec.setIndicator(null,ResourcesCompat.getDrawable(
            resources,R.drawable.ic_launcher_background,null))
        spec.setContent(R.id.tab_content3)
        host.addTab(spec)*/  //


        //
    }
}
