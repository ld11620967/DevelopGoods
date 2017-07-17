package com.nilin.developgoods

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kcode.gankotlin.repository.Article
import kotlinx.android.synthetic.main.activity_main.*
import com.nilin.retrofit2_rxjava2_demo.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
    var adapter: ArticleAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = ArticleAdapter(this,R.layout.item_news)
        recyclerview.adapter = adapter

        adapter!!.onItemClickListener = BaseQuickAdapter.OnItemClickListener {
            adapter, view, position ->
            start2Detail(adapter.data[position] as Article)
        }

        loadPublishedDate()
    }

    fun loadPublishedDate() {

        val api = Api.Factory.create()
        api.getData("Android", 10, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    adapter!!.addData(result.results)

                }, {}, { })
    }

    fun start2Detail(article: Article) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("url", article.url)
        startActivity(intent)
    }
}

