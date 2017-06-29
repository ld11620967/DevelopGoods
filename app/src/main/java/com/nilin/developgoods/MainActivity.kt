package com.nilin.developgoods

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
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
    var context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        var number = 1
        var url = "http://gank.io/api/data/Android/10/1"
        doAsync {
            var returnjsonstr = URL(url).readText()
            var returnjson = Gson().fromJson(returnjsonstr, ApiGank::class.java)
            var mAdapter = NewsAdapter(returnjson.results)
            uiThread {
                //recyclerview.layoutManager = GridLayoutManager(context, 1)
                recyclerview.setLayoutManager(LinearLayoutManager(context))
                recyclerview.setAdapter(mAdapter)

                mAdapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {

                        val intent = Intent()
                        intent.setClass(context, DetailsActivity::class.java)
                        intent.putExtra("url", returnjson.results.get(position).url)
                        context.startActivity(intent)

                    }
                })


            }
        }
//        updata(url)

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
                number = number + 1
                url = "http://gank.io/api/data/Android/10/$number"
//                updata(url)
                doAsync {
                    var returnjsonstr = URL(url).readText()
                    var returnjson = Gson().fromJson(returnjsonstr, ApiGank::class.java)
                    var mAdapter = NewsAdapter(returnjson.results)
                    uiThread {
                        recyclerview.setLayoutManager(LinearLayoutManager(context))
//                        recyclerview.setAdapter(mAdapter)
                        mAdapter.addData(number + 10, mAdapter)
                        mAdapter.notifyDataSetChanged()
                    }
                }
                recyclerview.loadMoreComplete()
            }
        })
    }

    fun updata(url: String) {
        doAsync {
            var returnjsonstr = URL(url).readText()
            var returnjson = Gson().fromJson(returnjsonstr, ApiGank::class.java)
            var mAdapter = NewsAdapter(returnjson.results)
            uiThread {
                //recyclerview.layoutManager = GridLayoutManager(context, 1)
                recyclerview.setLayoutManager(LinearLayoutManager(context))
                recyclerview.setAdapter(mAdapter)

                mAdapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val intent = Intent()
                        intent.setClass(context, DetailsActivity::class.java)
                        intent.putExtra("url", returnjson.results.get(position).url)
                        context.startActivity(intent)
                    }
                })

            }
        }
    }
}

