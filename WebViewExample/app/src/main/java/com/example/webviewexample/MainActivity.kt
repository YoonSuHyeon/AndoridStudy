package com.example.webviewexample


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var lineBtn: Button
    lateinit var barBtn: Button
    lateinit var webView: WebView
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView = findViewById<View>(R.id.webview) as WebView
        lineBtn = findViewById<View>(R.id.btn_chart_line) as Button
        barBtn = findViewById<View>(R.id.btn_chart_bar) as Button
        lineBtn.setOnClickListener(this)
        barBtn.setOnClickListener(this)
        val settings = webView.settings
        settings.javaScriptEnabled = true
        webView.loadUrl("file:///android_asset/test.html")
        webView.addJavascriptInterface(JavascriptTest(), "android")
        webView.webViewClient = MyWebClient()
        webView.setWebChromeClient(MyWebChrome())
    }

    override fun onClick(v: View) {
        if (v === lineBtn) {
            webView.loadUrl("javascript:lineChart()")
        } else if (v === barBtn) {
            webView.loadUrl("javascript:barChart()")
        }
    }

    internal inner class JavascriptTest {
       @JavascriptInterface
        fun chartData(): String
            {
                val buffer = StringBuffer()
                buffer.append("[")
                for (i in 0..13) {
                    buffer.append("[" + i + "," + Math.sin(i.toDouble()) + "]")
                    if (i < 13) buffer.append(",")
                }
                buffer.append("]")
                return buffer.toString()
            }
    }

    internal inner class MyWebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView,
            url: String
        ): Boolean {
            val t = Toast.makeText(this@MainActivity, url, Toast.LENGTH_SHORT)
            t.show()
            return true
        }
    }

    internal inner class MyWebChrome : WebChromeClient() {
        override fun onJsAlert(
            view: WebView,
            url: String,
            message: String,
            result: JsResult
        ): Boolean {
            val t = Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
            t.show()
            result.confirm()
            return true
        }
    }
}


