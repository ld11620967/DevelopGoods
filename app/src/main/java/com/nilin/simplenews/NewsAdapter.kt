package com.nilin.simplenews

import android.content.ClipData
import android.content.Context
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.nilin.simplenews.R.id.item_title
import com.nilin.simplenews.model.NewsModel
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_news.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL


/**
 * Created by liangd on 2017/6/6.
 */

class NewsAdapter(val Items: List<NewsModel>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, null))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(Items[position],listener)
    }

    override fun getItemCount() = Items.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(item: model, listener: (model) -> Unit) = with(itemView) {
            item_title.text = model.desc
//            item_icon.loadUrl(item.url)
            setOnClickListener { listener(item) }
        }

    }

}


//class NewsAdapter(var list: List<NewsModel>, var context: Context) : BaseAdapter() {
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        var v: View = LayoutInflater.from(context).inflate(R.layout.item_news, null)
//        var model: NewsModel = list[position]
//
//        v.item_title.text = model.desc
//        v.item_time.text = model.publishedAt
//
//        doAsync {
//            val data = URL(model.images[0] + "?imageView2/0/w/1000").readBytes()
//            uiThread {
//                if (data != null) {
//                    val bitma = BitmapFactory.decodeByteArray(data, 0, data.size)
//                    v.item_icon.setImageBitmap(bitma)
//                } else {
//                    v.item_icon.colorFilter
//                }
//            }
//        }
//        return v
//    }
//
//    override fun getItem(position: Int): Any {
//        return list[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return 0
//    }
//
//    override fun getCount(): Int {
//        return list.size
//    }
//
//}

