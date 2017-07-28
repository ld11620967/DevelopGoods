package com.nilin.developgoods

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kcode.gankotlin.repository.Article
import com.nilin.developgoods.model.Result
import kotlinx.android.synthetic.main.activity_main.*
import com.nilin.retrofit2_rxjava2_demo.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {
    var adapter: ArticleAdapter? = null
    val pageSize = 10
    var pageNumber = 1
    var isRefresh = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = ArticleAdapter(this, R.layout.item_news)
        recyclerview.adapter = adapter

        adapter!!.setOnLoadMoreListener({ loadMore() }, recyclerview)
        adapter!!.onItemClickListener = BaseQuickAdapter.OnItemClickListener {
            adapter, _, position ->
            start2Detail(adapter.data[position] as Article)
        }

        adapter!!.onItemLongClickListener= BaseQuickAdapter.OnItemLongClickListener listener@{
            adapter, _, position ->
            start2Browser(adapter.data[position] as Article)
            return@listener true
//            return@OnItemLongClickListener true
        }

        fab.onClick {
            recyclerview.smoothScrollToPosition(0)
        }

        swipeLayout.setOnRefreshListener({
            pageNumber = 1
            isRefresh = true
            loadData(pageSize, pageNumber)
        })

        loadData(pageSize, pageNumber)
    }

    fun start2Detail(article: Article) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("url", article.url)
        startActivity(intent)

    }

    fun start2Browser(article: Article) {
        val uri = Uri.parse(article.url)
        val it = Intent(Intent.ACTION_VIEW, uri)
        startActivity(it)
    }

    private fun loadMore() {
        pageNumber++
        loadData(pageSize, pageNumber)
    }

    protected fun loadData(pageSize: Int, pageNumber: Int) {
        val api = Api.Factory.create()
        api.getData("Android", pageSize, pageNumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    parseResult(result)
                }, {})
    }

    fun parseResult(result: Result) {
        if (result.error) {
            loadError()
        } else {
            loadSuccess(result.results)
        }
        loadFinish()
    }

    fun loadError() {
        toast("读取失败！")
    }

    fun loadSuccess(data: List<Article>) {
        setUp(data)
    }

    private fun setUp(data: List<Article>) {
        if (isRefresh) {
            adapter!!.setNewData(data)
            isRefresh = false
        } else {
            adapter!!.addData(data)
        }
    }

    fun loadFinish() {
        if (swipeLayout!!.isRefreshing) {
            swipeLayout!!.isRefreshing = false
        }
        adapter!!.loadMoreComplete()
    }

}
