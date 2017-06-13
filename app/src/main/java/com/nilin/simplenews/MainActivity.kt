package com.nilin.simplenews

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.nilin.simplenews.model.ApiGank
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var url = "http://gank.io/api/data/Android/5/1?imageView2/0/w/20 "

        var context: Context = this
        doAsync {
            var returnjsonstr = URL(url).readText()
            Log.i("returnjson", returnjsonstr)

            var returnjson = Gson().fromJson(returnjsonstr, ApiGank::class.java)
            Log.i("returnjson", "desc" + returnjson.results[0].images[0])

            uiThread {
                listview.adapter = NewsAdapter(returnjson.results, context)
            }

        }



//        listview.setOnItemClickListener{
//        }
    }
}

