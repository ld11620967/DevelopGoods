@file:Suppress("DEPRECATION")

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
    //    var mList = ArrayList<NewsModel>()
//    lateinit var mAdapter: NewsAdapter
//    lateinit var refreshLayout: MaterialRefreshLayout

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val toolbar = findViewById(R.id.toolbar) as Toolbar
            setSupportActionBar(toolbar)


            var url = "http://gank.io/api/data/Android/10/1"

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

            fab.onClick {
                toast("haha")
            }

//            refreshLayout.setLoadMore(true)
//            refreshLayout = findViewById(R.id.refresh) as MaterialRefreshLayout
//            refreshLayout.setMaterialRefreshListener(object : MaterialRefreshListener() {

            refresh.setMaterialRefreshListener(object : MaterialRefreshListener() {

                override fun onRefresh(refreshLayout: MaterialRefreshLayout) {
                    toast("shuaxin")
                    refresh.finishRefresh();
                }
//                refresh.setLoadMore(true);
                override fun onRefreshLoadMore(refreshLayout: MaterialRefreshLayout) {
                    //上拉刷新...
                    toast("jiazai")
                    refresh.finishRefreshLoadMore();
                }
            }
            )





//        mAdapter = NewsAdapter(mList)
//
//        mAdapter.setOnItemClickListener {
//            pos ->
//            val url = URLEncoder.encode(mList[pos].url)
//            GankRouter.router(context, GankClientUri.DETAIL + url)
//        }
//
//        val intent = Intent()
//        intent.data = Uri.parse(uri)
//        intent.action = Intent.ACTION_VIEW
//        context.startActivity(intent)

    }
}








