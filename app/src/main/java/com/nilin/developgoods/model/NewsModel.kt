package com.nilin.developgoods.model

import com.nilin.developgoods.NewsAdapter

/**
 * Created by liangd on 2017/6/5.
 */
class NewsModel(val _id: String, val desc: String,val publishedAt: String,val url:String,val images:Array<String>) {

    fun addData(position: Int, mAdapter: NewsAdapter) {

    }
}