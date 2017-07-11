package com.nilin.developgoods

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.nilin.developgoods.model.ApiGank
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import java.net.URL
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.nilin.developgoods.model.NewsModel
import com.nilin.retrofit2_rxjava2_demo.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var published:String? = null
    var adapter: NewsAdapter? = null
    var context = this
    private var mList = ArrayList<NewsModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        loadPublishedDate()


//        var url = "http://gank.io/api/data/Android/10/1"
//        doAsync {
//            var returnjsonstr = URL(url).readText()
//            var returnjson = Gson().fromJson(returnjsonstr, ApiGank::class.java)

//            mList.addAll(returnjson.results)


//            mAdapter!!.setNewData(returnjson.results)
//            uiThread {
                //recyclerview.layoutManager = GridLayoutManager(context, 1)
//                recyclerview.setLayoutManager(LinearLayoutManager(context))
//                recyclerview.setAdapter(mAdapter)
//                mAdapter!!.setNewData(returnjson.results)
//                mAdapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
//                    override fun onItemClick(view: View, position: Int) {
//                        val intent = Intent()
//                        intent.setClass(context, DetailsActivity::class.java)
//                        intent.putExtra("url", returnjson.results.get(position).url)
//                        context.startActivity(intent)
//                    }
//                })
//            }
//        }
//        updata(url)

//        fab.onClick {
//            recyclerview.smoothScrollToPosition(0)
//            toast("已达顶部")
//        }

//        recyclerview.setLoadingListener(object : XRecyclerView.LoadingListener {
//            override fun onRefresh() {
////                mList.clear()
//                var url = "http://gank.io/api/data/Android/10/1"
//
//                updata(url)
//                toast("刷新成功")
//                recyclerview.refreshComplete()
//            }
//
//            override fun onLoadMore() {
//                number = number + 10
//                url = "http://gank.io/api/data/Android/$number/1"
//
//                doAsync {
//                    var returnjsonstr = URL(url).readText()
//                    var returnjson = Gson().fromJson(returnjsonstr, ApiGank::class.java)
//                    mList.addAll(returnjson.results)
//                    uiThread {
//                        mAdapter.notifyDataSetChanged()
//                    }
//                }

//                doAsync {
//                    var returnjsonstr = URL(url).readText()
//                    var returnjson = Gson().fromJson(returnjsonstr, ApiGank::class.java)
//                    mList.addAll(returnjson.results)
//
//                    mAdapter.notifyDataSetChanged()
//
//
//                    uiThread {
////                        recyclerview.setLayoutManager(LinearLayoutManager(context))
//
////                        recyclerview.setAdapter(mAdapter)
////
//                    }
//                }
//                recyclerview.loadMoreComplete()
//            }
//        })
    }




//    fun updata(url: String) {
//        doAsync {
//            var returnjsonstr = URL(url).readText()
//            var returnjson = Gson().fromJson(returnjsonstr, ApiGank::class.java)
////            var mAdapter = NewsAdapter(context,returnjson.results)
//            mList.addAll(returnjson.results)
//            var mAdapter = NewsAdapter(mList)
////            val list = ArrayList(asList(returnjson.results))
////            var mAdapter = NewsAdapter(context,list)
//            uiThread {
//                //recyclerview.layoutManager = GridLayoutManager(context, 1)
////                recyclerview.setLayoutManager(LinearLayoutManager(context))
////                recyclerview.setAdapter(mAdapter)
//
//                mAdapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
//                    override fun onItemClick(view: View, position: Int) {
//                        val intent = Intent()
//                        intent.setClass(context, DetailsActivity::class.java)
//                        intent.putExtra("url", returnjson.results.get(position).url)
//                        context.startActivity(intent)
//                    }
//                })
//
//            }
//        }
//    }


    fun loadPublishedDate() {

        val api = Api.Factory.create()
        api.getData("Android",10,1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
//                    published = result.results.toString()
                    var mAdapter = NewsAdapter(result.results)
//                    published = result.results[0].replace("-","/")
                    Log.i("111",published)
                    adapter.


//                        published = result.results[0].replace("-","/")
//                        parseResult(result)
                }, {}, {  })
//                    }, {}, { onComplete() })


    }

}

