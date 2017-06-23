package com.nilin.simplenews

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import com.cjj.MaterialRefreshLayout
import com.cjj.MaterialRefreshListener
import com.google.gson.Gson
import com.nilin.simplenews.model.ApiGank
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        var number = 1
        var url = "http://gank.io/api/data/Android/10/$number"

        updata(url)

        fab.onClick {
            toast("haha")
        }

        refresh.setMaterialRefreshListener(object : MaterialRefreshListener() {

            override fun onRefresh(refreshLayout: MaterialRefreshLayout) {
                toast("shuaxin")
                refresh.finishRefresh();
            }

            override fun onRefreshLoadMore(refreshLayout: MaterialRefreshLayout) {
                //上拉刷新...
                number=number+1
                url = "http://gank.io/api/data/Android/10/$number"
                toast(url)
                updata(url)
                refresh.finishRefreshLoadMore();
            }
        })
    }

    fun updata(url:String) {
        var context: Context = this
        doAsync {
            var returnjsonstr = URL(url).readText()
            var returnjson = Gson().fromJson(returnjsonstr, ApiGank::class.java)
            Log.i("haha", "haha" + returnjson.results)
            uiThread {
                recyclerview.layoutManager = GridLayoutManager(context, 1)
                recyclerview.adapter = NewsAdapter(returnjson.results)
            }
        }
    }
}








