package com.nilin.developgoods

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import com.google.gson.Gson
import com.nilin.developgoods.model.ApiGank
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import java.net.URL
import com.jcodecraeer.xrecyclerview.XRecyclerView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        var number = 10
        var url = "http://gank.io/api/data/Android/10/1"

        updata(url)

        fab.onClick {
            recyclerview.smoothScrollToPosition(0)
            toast("已达顶部")
        }

        recyclerview.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onRefresh() {
                var url = "http://gank.io/api/data/Android/10/1"
                updata(url)
                toast("刷新成功")
                recyclerview.refreshComplete()
            }

            override fun onLoadMore() {
                number = number + 5
                url = "http://gank.io/api/data/Android/$number/1"
                updata(url)
                recyclerview.loadMoreComplete()
            }
        })
    }


fun updata(url: String) {
        var context: Context = this
        doAsync {
            var returnjsonstr = URL(url).readText()
            var returnjson = Gson().fromJson(returnjsonstr, ApiGank::class.java)
            uiThread {
                recyclerview.layoutManager = GridLayoutManager(context, 1)
                recyclerview.adapter = NewsAdapter(returnjson.results)
            }
        }
    }
}
