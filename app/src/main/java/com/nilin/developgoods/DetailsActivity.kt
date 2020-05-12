package com.nilin.developgoods

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_details.*
import android.view.MotionEvent
import android.view.View
import android.graphics.PointF


class DetailsActivity : AppCompatActivity() {

    private val startPoint = PointF()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent:Intent= getIntent()
        val url:String=intent.getStringExtra("url")!!

        webview.settings.javaScriptEnabled
        webview.run {
            setWebViewClient(WebViewClient())
            loadUrl(url)
            setOnTouchListener(object : View.OnTouchListener {
                @SuppressLint("ClickableViewAccessibility")
                override fun onTouch(v: View, event: MotionEvent): Boolean {

                    when (event.action) {
                    MotionEvent.ACTION_DOWN -> startPoint.set(event.x, event.y)
                    MotionEvent.ACTION_UP -> if (Math.abs(startPoint.x - event.x) >= 200 && Math.abs(startPoint.y - event.y) <= 50)
                        finish()
                    }
                    return false
                }
            })
        }
    }
}