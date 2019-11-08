package com.nilin.developgoods

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.Toolbar
import com.chad.library.adapter.base.BaseQuickAdapter
import com.nilin.developgoods.model.Article
import com.nilin.developgoods.model.Result
import kotlinx.android.synthetic.main.activity_main.*
import com.nilin.retrofit2_rxjava2_demo.Api
import kotlinx.coroutines.*
import org.jetbrains.anko.sdk27.coroutines.onClick

import org.jetbrains.anko.toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(), CoroutineScope {
    var adapter: ArticleAdapter? = null
    val pageSize = 20
    var pageNumber = 1
    var isRefresh = false

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = ArticleAdapter(this, R.layout.item_news)
        recyclerview.adapter = adapter

        adapter!!.setOnLoadMoreListener({ loadMore() }, recyclerview)
        adapter!!.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, _, position ->
            start2Detail(adapter.data[position] as Article)
        }

        adapter!!.onItemLongClickListener = BaseQuickAdapter.OnItemLongClickListener listener@{ adapter, _, position ->
            start2Browser(adapter.data[position] as Article)
            return@listener true
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

    val retrofit = Retrofit.Builder()
            .baseUrl("http://gank.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

    @SuppressLint("CheckResult")
    protected fun loadData(pageSize: Int, pageNumber: Int) {
        launch {
            val result = withContext(Dispatchers.IO) {
                retrofit.getData("Android", pageSize, pageNumber).execute()
            }
            if (result.isSuccessful) {
                parseResult(result.body()!!)
            }
        }
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