package com.nilin.simplenews

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var intent:Intent= getIntent()
        var url:String=intent.getStringExtra("url")

        webview.settings.javaScriptEnabled
        webview.setWebViewClient(WebViewClient())
        webview.loadUrl(url)
    }
}
