package com.nilin.simplenews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.nilin.simplenews.model.NewsModel
import kotlinx.android.synthetic.main.item_news.view.*


/**
 * Created by liangd on 2017/6/6.
 */
class NewsAdapter(var list:List<NewsModel>, var context: Context): BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v: View = LayoutInflater.from(context).inflate(R.layout.item_news,null)
            var model:NewsModel=list[position];
        v.item_title.text=model.title
        v.item_time.text=model.time


        
        return v
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }


}