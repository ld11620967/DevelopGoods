package com.nilin.simplenews

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.google.gson.Gson
import com.nilin.simplenews.model.ApiGank
import com.nilin.simplenews.model.NewsModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL
import java.net.URLEncoder


class MainActivity : AppCompatActivity() {
    var mList=ArrayList<NewsModel>()
    lateinit var mAdapter:NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var url = "http://gank.io/api/data/Android/10/1"

        var context: Context = this
        doAsync {
            var returnjsonstr = URL(url).readText()
            var returnjson = Gson().fromJson(returnjsonstr, ApiGank::class.java)
            Log.i("haha","haha"+returnjson.results)
            uiThread {
                recyclerview.layoutManager = GridLayoutManager(context, 1)
                recyclerview.adapter = NewsAdapter(returnjson.results)
            }
        }

        mAdapter = NewsAdapter(mList)

//        mAdapter.setOnItemClickListener {
//            pos ->
//            val url = URLEncoder.encode(mList[pos].url)
//            GankRouter.router(context, GankClientUri.DETAIL + url)
//        }

//        val intent = Intent()
//        intent.data = Uri.parse(uri)
//        intent.action = Intent.ACTION_VIEW
//        context.startActivity(intent)



    }
}

