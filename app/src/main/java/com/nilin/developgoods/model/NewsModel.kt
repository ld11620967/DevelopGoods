package com.nilin.developgoods.model

import com.nilin.developgoods.NewsAdapter

/**
 * Created by liangd on 2017/6/5.
 */
data class NewsModel(val _id: String, val desc: String,val publishedAt: String,val url:String,val images:Array<String>) {

}